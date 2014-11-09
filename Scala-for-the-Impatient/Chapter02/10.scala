def func(n:Int, x:Int, y:Int):Double = {
	if(n == 0) 1
	else if(n > 0 && n % 2 == 0) math.pow(y, 2)
	else if(n > 0 && n % 2 != 0) x * func(n - 1, x, y)
	else 1.0 / x * func(-n, x, y)
}

println(func(3, 2, 2))
