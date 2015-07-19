import org.apache.spark.{SparkConf, SparkContext}

/**
 * Page Rank 算法在 Spark 上的实现 <br>
 * Java 和 Scala 混着写我觉得自己很快就会死翘翘
 */
object PageRank {
  val ITERATIONS = 1

  def main(args: Array[String]): Unit = {
    // 初始化 Spark
    val conf = new SparkConf().setAppName("PageRank").setMaster("local[2]")
    val sc = new SparkContext(conf)

    // 初始化测试数据
    val links = sc.parallelize(Array(
      ('A', Array('D')),
      ('B', Array('A')),
      ('C', Array('A', 'B')),
      ('D', Array('A', 'C'))
    ), 2).cache()
    var ranks = sc.parallelize(Array(('A', 1.0f), ('B', 1.0f), ('C', 1.0f), ('D', 1.0f)), 2).map(x => (x._1, x._2)).cache

    // 迭代计算
    for (i <- 1 to ITERATIONS) {
      val contribs = links.join(ranks, 2).flatMap {
        case (url, (links, rank)) => links.map(dest => (dest, rank / links.size)) // 计算每一个的权重贡献
      }

      ranks = contribs.reduceByKey(_ + _, 2).mapValues((0.15f + 0.85f * _))
    }

    // 输出
    ranks.foreach(println)
  }
}
