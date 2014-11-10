val arr = java.util.TimeZone.getAvailableIDs()
val result = for(str <- arr if str.startsWith("America")) yield {str.drop("America/".length)}
println(result.sortWith(_ > _).mkString("(", ",", ")"))
