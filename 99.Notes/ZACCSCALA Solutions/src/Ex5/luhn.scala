package Ex5

/**
 * Created by kat on 28/01/15.
 */
class luhn {

  def removeLastDigit(n: String) = n.substring(0, n.length-1)

  def reverse(n : String) = n.reverse

  def doSums(n: String) = {
    var runningTotal = 0

    for (i <- 0 until n.length){
      //get the character as a digit for use
      val num = n.charAt(i).asDigit

      //if we're in an even position, indexing from 0
      if (i % 2 == 0) {
        //double it
        val n = num * 2
        //if the sum is higher than 9, subtract 9
        if (n > 9){
          //add to the total
          runningTotal += (n-9)
        } else {
          //sum is less than 9, just add it
          runningTotal += n
        }
      } else {
        //odd position, just add it
        runningTotal += num
      }
    }

    runningTotal
  }

  def mod10(n : Int) = n % 10

  def checkValid(n: Int, finalNumber: Int) {
    if (n == finalNumber)  println("Valid Number")
    else println("Invalid Number")
  }
}
