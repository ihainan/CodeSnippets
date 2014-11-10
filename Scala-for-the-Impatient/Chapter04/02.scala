import scala.collection.JavaConversions.mapAsScalaMap

def counterUsingMutableMap() :Unit = {
	var in = new java.util.Scanner(new java.io.File("data.txt"))
	val counter = scala.collection.mutable.Map[String, Int]()
	while(in.hasNext()){
		val s = in.next()
		counter(s) = counter.getOrElse(s, 0) + 1
	}
	println(counter)
}

def counterUsingImmutableMap() : Unit = {
	var in = new java.util.Scanner(new java.io.File("data.txt"))
	var counter = scala.collection.immutable.Map[String, Int]()
	while(in.hasNext()){
		val s = in.next()
		counter = counter + (s -> (counter.getOrElse(s, 0) + 1))
	}
	println(counter)
}

def counterUsingSortedMap() : Unit = {
	var in = new java.util.Scanner(new java.io.File("data.txt"))
	var counter = scala.collection.immutable.SortedMap[String, Int]()
	while(in.hasNext()){
		val s = in.next()
		counter = counter + (s -> (counter.getOrElse(s, 0) + 1))
	}
	println(counter)
}

def counterUsingJavaTreeMap() : Unit = {
	var in = new java.util.Scanner(new java.io.File("data.txt"))
	val counter:scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]
	while(in.hasNext()){
		val s = in.next()
		counter(s) = counter.getOrElse(s, 0) + 1
	}
	println(counter)
}


counterUsingMutableMap()
counterUsingImmutableMap()
counterUsingSortedMap()
counterUsingJavaTreeMap()
