package functional

object lab {
  println("Lab code")                             //> Lab code
  
  def values(fn: Int => Int, min: Int, max: Int) = {
  	for (x <- min to max) yield(x, fn(x))
  }                                               //> values: (fn: Int => Int, min: Int, max: Int)scala.collection.immutable.Index
                                                  //| edSeq[(Int, Int)]
  
  val v = values(x => x*x, -2, 2)                 //> v  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((-2,4), (-1,
                                                  //| 1), (0,0), (1,1), (2,4))
}