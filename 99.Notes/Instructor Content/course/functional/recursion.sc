package functional


object recursion {
  println("Recursion")                            //> Recursion
  
  def fact(n: Int): Int = if (n==0) 1 else n * fact(n-1)
                                                  //> fact: (n: Int)Int
  fact(4)                                         //> res0: Int = 24
  
  Test.factorial(4)                               //> res1: Int = 24
  
  val lst = List(1, 2, 3, 4)                      //> lst  : List[Int] = List(1, 2, 3, 4)
  
  def nth(n: Int, xs: List[Int]): Int = n match {
  	case 0 => xs.head
  	case _ => if (xs.isEmpty) throw new IndexOutOfBoundsException
  				    else nth(n-1, xs.tail)
  }                                               //> nth: (n: Int, xs: List[Int])Int
  
  val v = nth(2, lst)                             //> v  : Int = 3
}