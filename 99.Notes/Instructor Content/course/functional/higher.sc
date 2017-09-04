package functional

object higher {
  println("Higher-order functions")               //> Higher-order functions
  
  val isEven = (x: Int) => (x%2 == 0)             //> isEven  : Int => Boolean = <function1>
  isEven(3)                                       //> res0: Boolean = false
  isEven(4)                                       //> res1: Boolean = true
  
  val negate = (f: (Int => Boolean)) => (x: Int) => if (f(x)) false else true
                                                  //> negate  : (Int => Boolean) => (Int => Boolean) = <function1>
  
  val isOdd = negate(isEven)                      //> isOdd  : Int => Boolean = <function1>
  isOdd(3)                                        //> res2: Boolean = true
  negate(isEven)(4)                               //> res3: Boolean = false
  
  val flip = (f: ((Char, Char) => String)) => (x: Char, y: Char) => f(y,x)
                                                  //> flip  : ((Char, Char) => String) => ((Char, Char) => String) = <function1>
  val cat = (a: Char, b: Char) => "" + a + b      //> cat  : (Char, Char) => String = <function2>
  cat('a', 'b')                                   //> res4: String = ab

	val flipcat = flip(cat)                   //> flipcat  : (Char, Char) => String = <function2>
  flipcat('a', 'b')                               //> res5: String = ba
  
  def negate2(f: (Int => Boolean)) = {
  		val intNeg = (x: Int) => if (f(x)) false else true
  		intNeg
  }                                               //> negate2: (f: Int => Boolean)Int => Boolean
  
  def timesTwo() = {
  		val tt = (n: Int) => n * 2
  		tt
  }                                               //> timesTwo: ()Int => Int
}