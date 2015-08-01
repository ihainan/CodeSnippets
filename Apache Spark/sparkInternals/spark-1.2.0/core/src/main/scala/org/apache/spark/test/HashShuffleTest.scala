package org.apache.spark.test

import org.apache.spark.{HashPartitioner, SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * 验证 Hash Sort Shuffle
 */
object HashShuffleTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("GroupBy Test").setMaster("local").set("spark.shuffle.manager", "hash").set("spark.shuffle.spill", "false");
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(null.asInstanceOf[Integer], 2, 3, 4, 2, 1), 2);
    val rddWithKey = rdd.map(x => (x, 1))
    val finalRDD = rddWithKey.reduceByKey(_ + _)
    /*
    * I:
    * externalSorting = false
    * dep.aggregator.isDefined = true
    * mapSideCombine = true
    *   => AppendOnlyMap
    *     => changeValue
    *       => 目的：更新 nullValue，更新 data 表，合适时候 resize
    *     => iterator
    *       => Key = null 会被放在 iterator 的 -1 位置
    *       => 获取 iterator 时候，遍历整个哈希表，找出所有 !data(2 * pos).eq(null) 的值
    *       => 显然，最后结果是无序的
    *     => destructiveSortedIterator
    *       => 排序，先把所有数据放在最前面
    *       => Sort 之
    *       => iterator
    *         => 别忘了 null value
    * */

    /*
     * I: externalSorting = true
     * mapSideCombine = true
     *  => ExternalAppendOnlyMap
     *    => 维护一个 SizeTrackingAppendOnlyMap
     *      => extends AppendOnlyMap，用于存储内存中的数据
     *      => with SizeTracker
     *      => SizeTrackingPairCollection
     *    => insertAll
     *      => 用于插入一个 iterator
     *      => 监控插入个数和内存使用量
     *        => 符合某条件，先尝试获取更多内存，扩大内存容忍量为当前使用量的两倍
     *        => 若分配得到的内存仍然无法满足存储，溢存
     *          => 溢存前先进行排序
     *            => 排序器 keyComparator = new HashComparator[K]
     *      => iterator
     *        => 对内存中的数据进行排序
     *        => 构建优先级队列
     *        => 按照 key.hashCode 从小到大的顺序，取出所有 Key 相同的数据，merge，返回
     *        => 最后结果同样是无序的（Key.hashCode 有序）
     */
    finalRDD.collect()
  }
}
