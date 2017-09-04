package basics

object basics {
  println("Scala basics")
 
  // Solution to recursion lab exercise
  def intPow(x: Double, n: Int): Double = {
  		// n is >0 and even
    if (n > 0 & n % 2 == 0)  { val y = intPow(x, n/2); y*y }
    // n is odd...
    else if (n > 0) x * intPow(x, n - 1)
    else if (n == 0) 1
    else /*(n < 0)*/ 1 / intPow(x, -n)
  }
     
  val v2 = intPow(3, 2)
  val v3 = intPow(0, 1)
  
  // Solution to for comprehension lab exercise
  for(year <- 1960 to 2010
    if ((year%4 == 0 && year%100 != 0) || year%400 == 0
  )) yield year
}