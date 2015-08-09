package org.apache.spark.test

import org.apache.spark.{HashPartitioner, SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * ReduceByKey 测试程序 - 探索最后 ShuffledRDD 分区内的数据如何做 aggregation
 */
object ReduceByKeyTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("ReduceByKey Test").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(Array("A", "B", "C", "B", "C", "D", "C", "A"), 2)
    val rddWithNum = rdd.map(x => (x, 1))
    print(rddWithNum.reduceByKey(new HashPartitioner(2), _ + _).collect.mkString(" "))
  }
}
