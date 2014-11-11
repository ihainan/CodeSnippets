import java.util.{HashMap => JavaHashMap, Map => JavaMap}
import scala.collection.mutable.HashMap
import scala.collection.JavaConversions.mapAsScalaMap

val m1 = new JavaHashMap[String, Int]
m1.put("Mike", 100)
m1.put("Jack", 50)

var s = new HashMap[String, Int]
for((k, v) <- m1) s += (k -> v)
println(s.mkString("(", " ", ")"))
