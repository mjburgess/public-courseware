package functional

object closure {
  println("Closures")                             //> Closures
  
  val n = 10                                      //> n  : Int = 10
  
  def f(x: Int) = x + n                           //> f: (x: Int)Int
  
  f(3)                                            //> res0: Int = 13
  
  def func(x: Int): Int => Int = {
  	val z = 4
  	(y: Int) => x + y + z
  }                                               //> func: (x: Int)Int => Int
  
  val z = func(3)                                 //> z  : Int => Int = <function1>
  z(4)                                            //> res1: Int = 11
  
  // Calculate Fibonacci numbers
  def fib() = {
    var t = (1,-1)
    () => {
      t = (t._1 + t._2, t._1)
      t._1
    }
  }                                               //> fib: ()() => Int
  
  val fi = fib()                                  //> fi  : () => Int = <function0>
  for(i <- 0 to 8) println(fi())                  //> 0
                                                  //| 1
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 5
                                                  //| 8
                                                  //| 13
                                                  //| 21
}