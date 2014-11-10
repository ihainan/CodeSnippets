import scala.collection.JavaConversions.propertiesAsScalaMap
val props:scala.collection.mutable.Map[String, String] = System.getProperties()
val maxlen = props.keySet.map(k => k.length).max
for((k, v) <- props){
	printf("%-" + maxlen + "s| %s\n", k, v)
}
