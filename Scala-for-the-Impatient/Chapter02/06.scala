def product(s:String) : Long = {
	var result:Long = 1
	for(ch <- s) result *= ch.toLong
	result
}

def productWithRecursion(level:Int, s:String) : Long = {
	if(level == s.length - 1) s.last.toLong
	else s(level).toLong * productWithRecursion(level + 1, s)
}

println("Hello".foldLeft(1L)(_ * _.toLong))		// exp06
println(product("Hello"))						// exp07
println(productWithRecursion(0, "Hello"))		// exp08
