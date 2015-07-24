package org.apache.spark.test

import org.apache.spark.{HashPartitioner, SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * GroupByKey 测试程序
 */
object GroupByKeyTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Hello Spark").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List("Doge", "Cat", "Panda"), 3)
    /* R:
       parallelize 每个分区中记录的个数由方法 ParallelCollectionRDD.slice 方法决定
     */
    val rddWithKey = rdd.keyBy(_.length)
    /* R:
      实际上是 map 转换，分区个数不变
     */
    val finalRDD = rddWithKey.groupByKey(new HashPartitioner(2))
    /* R:
      指定分区个数为 2
      groupByKey（指定 aggregator 的三个函数） -> combineByKey（生成 aggregator，得到 ShuffledRDD，mapSideCombine = false）
    */
    finalRDD.collect()
    /* R:
      ShuffleMapTask.runTask -> SortShuffleWrite.write（mapSideCombine == false）
      -> new ExternalSorter[K, V, V](None, Some(dep.partitioner), None, dep.serializer)（不关心分区是否有序）
        -> shouldCombine = false
        -> bypassMergeSort = true （子 RDD 分区少，产生的文件数目也少，就不用 merge 了）
        -> spillToPartitionFiles
      -> shuffleBlockManager.getDataFile （Shuffle Map 端输出到一个文件中，文件格式：数据，数据，数据）
      -> shuffleBlockManager.consolidateId
      -> sorter.writePartitionedFile
        -> bypassMergeSort && partitionWriters != null
        -> spillToPartitionFiles(if (aggregator.isDefined) map else buffer)
        -> partitionWriters.foreach(_.commitAndClose())
        -> 读取文件，写入到最终文件中
      ->  shuffleBlockManager.writeIndexFile(dep.shuffleId, mapId, partitionLengths) 索引文件

      HashShuffleReader.read() -> dep.aggregator.isDefined = true
        -> dep.mapSideCombine = false
        -> new InterruptibleIterator(context, dep.aggregator.get.combineValuesByKey(iter, context))
        -> externalSorting = true
        -> val combiners = new ExternalAppendOnlyMap[K, V, C](createCombiner, mergeValue, mergeCombiners)（跟）
        -> combiners.insertAll(iter)
          -> 类似于只是用 Map 进行排序和 Combine 操作的 ExternalSorter.insertAll
        -> combiners.iterator
          -> 没有溢存，返回 currentMap.iterator
          -> 有溢存，返回 new ExternalIterator
     */
  }

}
