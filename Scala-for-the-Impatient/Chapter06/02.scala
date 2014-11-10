class UnitConversion(val factor : Double){
	def convert(value : Double) : Double = factor * value
}

object InchesToCentimeters extends UnitConversion(2.53){}
object GallonsToLiters extends UnitConversion(3.785){}
object MilesToKilometers extends UnitConversion(1.699){}

println(InchesToCentimeters.convert(10))
println(GallonsToLiters.convert(10))
println(MilesToKilometers.convert(10))
