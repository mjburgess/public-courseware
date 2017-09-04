package collections

import scala.collection.mutable._

object WordCount extends App {
  // Create a map using the helper
  var mp = Map[String, Int]()
    
  val in = new java.util.Scanner(new java.io.File("myfile.txt"))
    
  while (in.hasNext) {
    val word = in.next
    
    if (mp.contains(word))
      mp(word) += 1
    else
      mp(word) = 1
  }
  
  for ((k,v) <- mp)
    println(k + ": " + v)
}