class Point(var x:Int, var y:Int){
}

object Point{
	def apply(x:Int, y:Int) = {
		new Point(x, y)
	}
}

val p = Point(2, 3)
println(p.x + ", " + p.y)
