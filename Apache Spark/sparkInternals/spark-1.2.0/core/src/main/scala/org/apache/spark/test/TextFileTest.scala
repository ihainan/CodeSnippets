package org.apache.spark.test

import org.apache.spark.{SparkContext, SparkConf}

/**
 * TextFile 测试
 */
object TextFileTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("GroupBy Test").setMaster("local");
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("/tmp/input")
    println(rdd.partitions.size)
  }
}
