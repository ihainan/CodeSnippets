def getSigNum(num:Int):Int = {
	if(num > 0) 1
	else if(num == 0) 0
	else -1
}

println(getSigNum(-10))
println(getSigNum(0))
println(getSigNum(10))
