import scala.collection.mutable.ArrayBuffer
val arr = new ArrayBuffer[Int]()
arr += (1, -2, 3, -4, 5, -6)

var indexes = for(i <- 0 until arr.length if arr(i) < 0) yield i
println(indexes.dropRight(1).reverse.mkString("(", ",", ")"))
