package functional

import scala.annotation._

object Test {
	//@tailrec final def fact2(n: Int): Int = if (n==0) 1 else n * fact2(n-1)
	
	def factorial(x: Int) = {
	  @tailrec def fact(n:Int, acc: Int): Int = 
	    if (n == 0) acc
	    else fact(n-1, n*acc)
	    
	  fact(x, 1)
	}
}