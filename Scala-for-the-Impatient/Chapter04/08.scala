def minmax(values:Array[Int]):(Int, Int) = {
	(values.max, values.min)
}

def lteqgt(values:Array[Int], v:Int):(Int, Int, Int) = {
	(values.filter(_ > v).size, values.filter(_ == v).size, values.filter(_ < v).size)
}

val arr = Array[Int](2, 10, -1, 5, 9)
println(minmax(arr))
println(lteqgt(arr, 5))
