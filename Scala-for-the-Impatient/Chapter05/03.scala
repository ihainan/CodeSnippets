class Timer(val hours:Int, val minutes:Int){
	private var mins = h * 60 + m
	def before(other:Timer) : Boolean = {
		val otherHours = other.hours
		val otherMinutes = other.minutes
		hours < otherHours || (hours == otherHours && minutes < otherMinutes)
	}
}

val timer = new Timer(2, 30)
println(timer.hours + " : " + timer.minutes)
println(timer.before(new Timer(2, 31)))
println(timer.before(new Timer(2, 29)))
