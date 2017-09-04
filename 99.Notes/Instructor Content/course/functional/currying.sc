package functional

object currying {
  println("Currying and partial functions")       //> Currying and partial functions
  
  // Returning a function object
  def func(x: Int): Int => Int = {
  		(y: Int) => x + y
  }                                               //> func: (x: Int)Int => Int
  
  val v = func(3)                                 //> v  : Int => Int = <function1>
  
  v(4)                                            //> res0: Int = 7
  
  // Partial functions
  
  val doIt = (a: Any) => a.toString               //> doIt  : Any => String = <function1>
  
  val x1 = doIt(4)                                //> x1  : String = 4
  val x2 = doIt("hello")                          //> x2  : String = hello
  
  // Partial that will only accept a string
  val doItS = doIt(_: String)                     //> doItS  : String => String = <function1>
  // Partial that will only accept an Int
  val doItI = doIt(_: Int)                        //> doItI  : Int => String = <function1>
  
  doItS("Hi")                                     //> res1: String = Hi
  //doItS(4)		// type mismatch
  
  val adder = (a: Int, b: Int) => a+b             //> adder  : (Int, Int) => Int = <function2>
  
  val addTwo = adder(_: Int, 2)                   //> addTwo  : Int => Int = <function1>
  
  addTwo(3)                                       //> res2: Int = 5
  
  // Currying
  val sum = (x: Int, y: Int, z: Int) => x+y+z     //> sum  : (Int, Int, Int) => Int = <function3>
  
  
  val sum2 = sum.curried                          //> sum2  : Int => (Int => (Int => Int)) = <function1>
  val a = sum2(3)                                 //> a  : Int => (Int => Int) = <function1>
  val b = a(2)                                    //> b  : Int => Int = <function1>
  val c = b(4)                                    //> c  : Int = 9
  
  def func2(x: Int)(y: Int) = x + y               //> func2: (x: Int)(y: Int)Int
  
  func2(3)(5)                                     //> res3: Int = 8
  //func2(3, 5)		// syntax error
  
  val v2 = func2(3) _                             //> v2  : Int => Int = <function1>
  
  v2(4)                                           //> res4: Int = 7

  // Define a curried function
  def sc(a: Int)(b: Int)(c: Int) = a+b+c          //> sc: (a: Int)(b: Int)(c: Int)Int
  // Obtain a function value
  def sc1 = sc _                                  //> sc1: => Int => (Int => (Int => Int))
  
  val sc2 = sc1(2)                                //> sc2  : Int => (Int => Int) = <function1>
}