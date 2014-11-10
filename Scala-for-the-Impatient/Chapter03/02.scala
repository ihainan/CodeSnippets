def swapArray(arr:Array[Int]) = {
	for(i <- (0 until arr.length - 1 by 2)) {var tmp = arr(i); arr(i) = arr(i + 1); arr(i + 1) = tmp}
	println(arr.mkString("(", ",", ")"))
}


def swapArrayUsingYield(arr:Array[Int]) = {
	for(i <- 0 until arr.length) yield {
		if(i == arr.length - 1) arr(i)
		else if(i % 2 != 0) arr(i - 1)
		else arr(i + 1)
			
	}
}


val arr = Array(1, 2, 3, 4, 5)
// swapArray(arr)
val newArr = swapArrayUsingYield(arr)
println(newArr.mkString("(", ",", ")"))
