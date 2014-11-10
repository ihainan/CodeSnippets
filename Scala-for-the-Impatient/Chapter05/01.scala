class Counter{
	private var value = 0
	def increment() : Unit = {
		value = (if(value == Int.MaxValue) Int.MaxValue else value + 1)
	}
	def current = value
}

val counter = new Counter
counter.increment()
println(counter.current)
