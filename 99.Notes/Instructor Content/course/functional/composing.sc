package functional
  
object composing {
  println("Composing functions")                  //> Composing functions
 
  def f(n: Int) = {
  		println("This is f()")
  		n * 2
  }                                               //> f: (n: Int)Int
    
  def g(n: Int) = {
  		println("This is g()")
  		n + 2
  }                                               //> g: (n: Int)Int
  
  // To compose methods, you need to turn them into functions
  val f1 = f _ compose g                          //> f1  : Int => Int = <function1>
  val g1 = g _ compose f                          //> g1  : Int => Int = <function1>
  
  f1(2)                                           //> This is g()
                                                  //| This is f()
                                                  //| res0: Int = 8
  g1(2)                                           //> This is f()
                                                  //| This is g()
                                                  //| res1: Int = 6
  val f2 = f _ andThen g                          //> f2  : Int => Int = <function1>
  f2(3)                                           //> This is f()
                                                  //| This is g()
                                                  //| res2: Int = 8
  // Define functions, and you can compose them directly
  val a = (n: Int) => n + 2                       //> a  : Int => Int = <function1>
  val b = (n: Int) => n * 2                       //> b  : Int => Int = <function1>
  
  val aFirst = a compose b                        //> aFirst  : Int => Int = <function1>
  val bFirst = b compose a                        //> bFirst  : Int => Int = <function1>
  
  aFirst(2)                                       //> res3: Int = 6
  bFirst(2)                                       //> res4: Int = 8
}