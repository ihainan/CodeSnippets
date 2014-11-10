object TrafficeLightColor extends Enumeration {
	val Red, Yello, Green = Value
}

val color:TrafficeLightColor.Value = TrafficeLightColor.Red
if(color == TrafficeLightColor.Red) println("Stop")
