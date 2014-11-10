class Person(var age:Int = 0){
	age = if(age > 0) age else 0
}

val p = new Person(30)
println(p.age)

val p2 = new Person(-30)
println(p2.age)
