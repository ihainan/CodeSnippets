object Conversions{
	def inchesToCentimeers(inches : Double) = 2.54 * inches
	def gallonsToLiters(gallons : Double) = 3.785 * gallons
	def milesToKilometers(miles : Double) = 1.609 * miles
}

println(Conversions.inchesToCentimeers(10))
println(Conversions.gallonsToLiters(10))
println(Conversions.milesToKilometers(10))
