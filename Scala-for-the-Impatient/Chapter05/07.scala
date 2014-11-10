class Person(name:String){
	val firstName = name.split(" ")(0)
	val lastName = name.split(" ")(1)
}

val p = new Person("Mike Jack")
println(p.firstName + " " + p.lastName)
