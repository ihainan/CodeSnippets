val n = 10
val arr = new Array[Int](n)
for(i <- 0 until arr.length) arr(i) = scala.util.Random.nextInt(n)
println(arr.mkString(" "))
