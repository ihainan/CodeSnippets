package me.ihainan.impatient
class Utils{
	def percenOf(value:Double, rate:Double) = value * rate / 100
}

package object people{
	val defaultName = "Mike Jack"
}

package people{
	class People{
		val name = defaultName
	}
}
