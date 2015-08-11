package org.apache.spark.test

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * CheckPoint 机制测试 <br>
 * 测试计算链上的两个 RDD，是否都能被 Checkpoint
 */
object CheckPointTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Hello Spark").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setCheckpointDir("/tmp/")
    val rdd1 = sc.parallelize(Array(1, 2, 3, 4))
    val rdd2 = rdd1.map(x => x)
    val rdd3 = rdd2.map(x => x)
    rdd2.checkpoint()
    println(rdd2.isCheckpointed)
    println(rdd3.isCheckpointed)
    println(rdd3.sum)
    println(rdd2.isCheckpointed)
    println(rdd3.isCheckpointed)
    rdd3.checkpoint()
    println(rdd3.sum)
    println(rdd3.isCheckpointed)  /* T: 一条链中，只会 checkpoint 一个 RDD（为什么如此设计？） */
  }
  /* output:
  false
  false
  10.0
  true
  false
  10.0
  false
   */
}
