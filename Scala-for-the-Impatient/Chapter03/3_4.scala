/* 移除数组缓存中除了第一个负数之外的所有负数 */
import scala.collection.mutable.ArrayBuffer
val arr = new ArrayBuffer[Int]()
arr += (1, -2, 3, -4, 5, -6)

// 获取所有需要保留的数据
var first = true
var indexes = for(i <- 0 until arr.length if first || arr(i) >= 0) yield {if(arr(i) < 0) first = false; i}


// 将保留的数据放在开头
for(j <- 0 until indexes.length) arr(j) = arr(indexes(j))

// 移除不需要的数据
arr.trimEnd(arr.length - indexes.length)

// 输出
println(arr)
