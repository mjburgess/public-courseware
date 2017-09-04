package functional

object anonymous {
  println("Anonymous functions")                  //> Anonymous functions
  
  val square = (x: Int) => x*x                    //> square  : Int => Int = <function1>
  square(3)                                       //> res0: Int = 9
  
  val f = () => println("Hello!")                 //> f  : () => Unit = <function0>
  f()                                             //> Hello!
  
  def func(x: Int, f: Int => Int) = f(x)          //> func: (x: Int, f: Int => Int)Int
  
  val v = func(2, square)                         //> v  : Int = 4
  
  val v1 = func(3, x => x*x)                      //> v1  : Int = 9
  
  def timesTwo() = {
  		val tt = (n: Int) => n * 2
  		tt
	}                                         //> timesTwo: ()Int => Int
  
  val t2 = timesTwo()                             //> t2  : Int => Int = <function1>
  t2(2)                                           //> res1: Int = 4

  
}