val m1 = Map("iPad" -> 3100, "Moto 360" -> 1700)
val m2 = for((k, v) <- m1) yield (k, v * 0.9)
println(m2)
