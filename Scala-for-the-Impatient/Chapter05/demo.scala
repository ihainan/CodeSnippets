class Counter{
	private var value = 0;
	def increment() {value += 1}
	def current = value
	def isLess(other : Counter) = value < other.value
}

class Person(var name : String = "", private var privateAge : Int = 0){
	def this(name : String){
		this();	// main constructor
		this.name = name
	}

	def age = privateAge

	def age_=(newValue : Int){
		if(newValue > privateAge) privateAge = newValue
	}
}


val tom = new Person("tom", 10)
println(tom.name + " " + tom.age)

val couter, otherCounter = new Counter
otherCounter.increment()
println(couter.isLess(otherCounter))
