import scala.collection.mutable.ArrayBuffer

val arr = Array[Int](1, 2, 3, 4, 5)
val arrBuf = ArrayBuffer[Int](1, 2, 3, 4, 5)

println(arr.reverse.mkString("(", ",", ")"))
println(arrBuf.reverse.mkString("(", ",", ")"))
