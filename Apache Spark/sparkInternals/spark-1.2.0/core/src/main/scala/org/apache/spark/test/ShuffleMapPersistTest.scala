package org.apache.spark.test

import org.apache.spark.{HashPartitioner, SparkContext, SparkConf}
import org.apache.spark.SparkContext._

/**
 * 验证 ShuffleMapTask 生成的数据是否会被持久化存储，从而避免数据被重复计算 <br>
 * 以 Hash Shuffle 为例。
 */
object ShuffleMapPersistTest {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Shuffle Map Persist Test").setMaster("local").set("spark.shuffle.manager", "hash")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(Array("A", "B", "C", "B", "C", "D", "C", "A"), 2)
    val rddWithNum = rdd.map(x => (x, 1))
    print(rddWithNum.reduceByKey(new HashPartitioner(2), _ + _).collect.mkString(" "))

    /*
    * T:
    *   => 会触发两次 ShuffleMapTask.run，用于计算两个 mapper 的输出数据
    *   => 接着触发两次 ShuffledRDD.compute，用于拉取 map 端的数据并计算 reducer 端的数据
    *   => 说明：一个 Job 中，上一 Stage 计算的末 RDD 数据会持久化
    * */

    print(rddWithNum.reduceByKey(new HashPartitioner(2), _ + _).collect.mkString(" "))
    /*
    * T:
    *   => Job 运行完毕之后，原来的中间数据会被删除
    * */
  }
}
