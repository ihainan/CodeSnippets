package org.apache.spark.test

import org.apache.spark.{HashPartitioner, SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * Sort Shuffle 测试
 */
object SortShuffleTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("GroupBy Test").setMaster("local").set("spark.shuffle.manager", "sort").set("spark.shuffle.sort.bypassMergeThreshold", "1");
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(2, 3, 4, 2, 1), 1);
    val rddWithKey = rdd.map(x => (x, 1))
    // val finalRDD = rddWithKey.reduceByKey(_ + _)  /* I: mapSideCombine = true */
    val finalRDD = rddWithKey.groupByKey(new HashPartitioner(2)) /* I: mapSideCombine = false */
    println(finalRDD.collect)

    /*
    * T:
    *   => SortShuffleWriter
    *     => insertAll
    *       => mapSide.combine = true && aggregation != null
    *         => 使用 SizeTrackingAppendOnlyMap
    *           => 对于一个元素
    *             => SizeTrackingAppendOnlyMap.changeValue
    *               => 监控数目和文件大小，必要时候 size x 2
    *               => 执行 Combine 操作
    *           => maybeSpillCollection(usingMap = true)
    *             => maybeSpill(map, map.estimateSize())
    *               => 检测是否超出阈值，尝试申请更多内存
    *                 => 仍然超出阈值，ExternalSorter.spill(collection: SizeTrackingPairCollection[(Int, K), C])
    *                   => spillToMergeableFile(collection)
    *                     => 每次 Spill，一个文件，对应一个 writer
    *                     => 按照分区 - Key 排序！！！！！
    *                       => val it = collection.destructiveSortedIterator(partitionKeyComparator)
    *                     => 写入 Key, Value，不需要写入 Partition ID
    *                       => elementsPerPartition(partitionId) += 1，用于记录每个分区的数据个数
    *                     => spills.append(SpilledFile(file, blockId, batchSizes.toArray, elementsPerPartition))
    *       => bypassMerge（numPartitions <= bypassMergeThreshold（默认 200） && aggregator.isEmpty && ordering.isEmpty）
    *         => 不需要 combine
    *         => 不需要 sort
    *         => 一个分区一个文件
    *         => spillToPartitionFiles(Iterator((Int, K), C))
    *           => 如果 partitionWriters 不存在，生成 partitionWriters，每个分区一个 partitionWriter
    *           => 写入数据
    *             => 数据无序！
    *       => Else (aggregator.isEmpty && (numPartitions > bypassMergeThreshold || !ordering.isEmpty))
    *         => 不需要 merge
    *         => 只要给定 ordering 或者分区数比较多，就会进入此
    *         => 使用 SizeTrackingPairBuffer
    *           => SizeTrackingPairBuffer.changeValue
    *             => 监控数目和文件大小，必要时候 size x 2
    *             => 不执行 combine 操作
    *           => maybeSpillCollection(usingMap = false)
    *             => maybeSpill(buffer, buffer.estimateSize())
    *               => 检测是否超出阈值，尝试申请更多内存
    *                 => 仍然超出阈值，ExternalSorter.spill(collection: SizeTrackingPairCollection[(Int, K), C])
    *                   => spillToMergeableFile(collection)
    *
    *     => writePartitionedFile
    *       => bypassMergeSort && partitionWriters != null (数据被存储到多个分区文件中)
    *         => 把内存中的数据也写入到文件中（实际上这步不会执行）
    *         => 一个一个读文件，输出到最后文件中
    *         => 返回 lengths，记录每个分区的长度
    *       => Else (数据被溢存到多个 mergeable 文件中，或则都在内存中)
    *         => partitionedIterator: Iterator[(Int, Iterator[Product2[K, C]])]
    *           => spills.isEmpty && partitionWriters == null（数据全部在内存当中）
    *             => !ordering.isDefined -- ordering 用于指定一个分区内数据如何按照 Key 排序
    *               => groupByPartition(collection.destructiveSortedIterator(partitionComparator)) -- 输入（内存中的）的数据已经按照分区排好序
    *                 => (0 until numPartitions).iterator.map(p => (p, new IteratorForPartition(p, buffered)))
     *                  => 分区编号，所有数据。
     *                  => assume this partition is the next one to be read
     *                  => 注意，buffered 对于所有的 new IteratorForPartition(p, buffered) 来说，都是相同的！！！！！
     *                    => 赞呀！！！
    *             => ordering.isDefined
    *               => groupByPartition(collection.destructiveSortedIterator(partitionKeyComparator))
    *           => bypassMergeSort == true（不可能进入此块）
    *           => ELSE
    *             => merge(spills, collection.destructiveSortedIterator(partitionKeyComparator))  -- Spills + 内存中排好序的数据
    *               => 获取所有 spilled 文件对应的 reader，val readers = spills.map(new SpillReader(_))
    *               => val inMemBuffered = inMemory.buffered
    *               => 对于每个分区
    *                 => val inMemIterator = new IteratorForPartition(p, inMemBuffered)
    *                 => readers.map(_.readNextPartition()) ++ Seq(inMemIterator)
    *                   => aggregator.isDefined（需要进行 combine 操作）
    *                     => mergeWithAggregation(iterators, aggregator.get.mergeCombiners, keyComparator, ordering.isDefined)
    *                       => 归并 + Aggregation
    *                         => 具体实现待研究
    *                   => ordering.isDefined（需要进行排序操作，对应需要排序的 Buffer）
    *                     => (p, mergeSort(iterators, ordering.get))
    *                      => mergeSort(iterators, ordering.get)
    *                       => 归并排序
    *                         => 每个 iterator 内部都是已经按照 Partiton ID 排好序的数据
    *                         => 归并的结果是按照 分区 - Key 排序的数据
    *                           => 具体实现待研究
    *                   => ELSE（不需要排序的 Buffer）
    *                     => return (partitionID, iterators.iterator.flatten)
    *                       => 此时数据已经按照分区排好序，但内部没排序
    *         => (id, elements) <- this.partitionedIterator
    *           => elem <- elements
    *             => writer.write(elem)
    *             => lengths(id) = segment.length
    * */
  }
}
