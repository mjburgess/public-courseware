package functional

object lazyEval {
  println("Lazy Evaluation")                      //> Lazy Evaluation
  
  lazy val a = b + 1                              //> a: => Int
  lazy val b = 1                                  //> b: => Int
  
  a                                               //> res0: Int = 2
  
  def succ(n: Int): Stream[Int] = Stream.cons(n, succ(n+1))
                                                  //> succ: (n: Int)Stream[Int]
  
  val positiveIntegers = succ(10)                 //> positiveIntegers  : Stream[Int] = Stream(10, ?)
  
  positiveIntegers take 25 foreach println        //> 10
                                                  //| 11
                                                  //| 12
                                                  //| 13
                                                  //| 14
                                                  //| 15
                                                  //| 16
                                                  //| 17
                                                  //| 18
                                                  //| 19
                                                  //| 20
                                                  //| 21
                                                  //| 22
                                                  //| 23
                                                  //| 24
                                                  //| 25
                                                  //| 26
                                                  //| 27
                                                  //| 28
                                                  //| 29
                                                  //| 30
                                                  //| 31
                                                  //| 32
                                                  //| 33
                                                  //| 34
}