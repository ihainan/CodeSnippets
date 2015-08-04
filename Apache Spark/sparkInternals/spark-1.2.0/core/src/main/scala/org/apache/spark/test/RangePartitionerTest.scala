package org.apache.spark.test

import org.apache.spark.{RangePartitioner, HashPartitioner, SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * Range Partitioner 测试
 */
object RangePartitionerTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("GroupBy Test").setMaster("local");
    val sc = new SparkContext(conf)

    val rdd = sc.parallelize(List(2, 3, 4, 2, 1), 1);
    val rddWithKey = rdd.map(x => (x, 1))
    // val finalRDD = rddWithKey.reduceByKey(_ + _)  /* I: mapSideCombine = true */
    val finalRDD = rddWithKey.groupByKey(new RangePartitioner(2, rddWithKey)) /* I: mapSideCombine = false */
    val partitioner = new RangePartitioner(2, rddWithKey)
    print(partitioner.rangeBounds)
    println(finalRDD.collect)
  }
}
