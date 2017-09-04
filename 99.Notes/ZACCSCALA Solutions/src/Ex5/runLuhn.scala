package Ex5

/**
 * Created by kat on 28/01/15.
 */
object runLuhn extends App{
  val number = "4556737586899855"

  val l = new luhn

  val step1 = l.removeLastDigit(number)
  println(step1)
  val step2 = l.reverse(step1)
  println(step2)
  val step3 = l.doSums(step2)
  println(step3)
  val step4 = l.mod10(step3)
  println(step4)
  l.checkValid(step4, number.charAt(number.length - 1).asDigit)
}
