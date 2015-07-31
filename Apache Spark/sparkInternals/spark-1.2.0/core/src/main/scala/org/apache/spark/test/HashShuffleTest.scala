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
    *     => destructiveSortedIterator
    *       => 排序，先把所有数据放在最前面
    *       => iterator
    *         => 别忘了 null value
    * */
    finalRDD.collect()
  }
}
