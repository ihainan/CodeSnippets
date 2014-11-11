package object random{
	var seed:Int = 0
	val a:Int = 1664525
	val b:Int = 1013904223
	val n:Int = 32
	def nextInt():Int = (seed * a + b) % (2 ^ n)
	def nextDouble():Double = nextInt().toDouble / Int.MaxValue.toDouble
	def setSeed(s:Int) {seed = s}
}

import random._

object Q3 extends App{
	setSeed((System.currentTimeMillis() / 1000).toInt)
	(1 to 5).foreach(x => println(nextInt()))
	(1 to 5).foreach(x => println(nextDouble()))
}

