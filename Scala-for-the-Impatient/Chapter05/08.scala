class Car(val manufacturer:String, val model:String){
	private var privateModelYear = -1
	def modelYear = privateModelYear
	var plateNum = ""
	def this(manufacturer:String, model:String, modelYear:Int){
		this(manufacturer, model)
		this.privateModelYear = modelYear
	}

	def this(manufacturer:String, model:String, modelYear:Int, plateNum:String){
		this(manufacturer, model)
		this.privateModelYear = modelYear
		this.plateNum = plateNum
	}

	def this(manufacturer:String, model:String, plateNum:String){
		this(manufacturer, model)
		this.plateNum = plateNum
	}

	def printCar(){
		println(manufacturer + " " + model + " " + modelYear + " " + plateNum)
	}
}



val c1 = new Car("BMW", "X6")
val c2 = new Car("BMW", "X6", 1992)
val c3 = new Car("BMW", "X6", "88888")
val c4 = new Car("BMW", "X6", 1992, "88888")

c1.printCar()
c2.printCar()
c3.printCar()
c4.printCar()
