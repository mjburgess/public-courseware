package basics

object Test {
  println("Hi")                                   //> Hi
  
  val v = for (i <- 1 to 10 if i%2 == 0) yield i*i//> v  : scala.collection.immutable.IndexedSeq[Int] = Vector(4, 16, 36, 64, 100)
}