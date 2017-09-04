package Ex4;

import Ex5.luhn
import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by kat on 28/01/15.
 */
class TestLuhn extends FlatSpec with Matchers{

  val l = new luhn;

  val number = "4556737586899855"

/*
455673758689985
589986857376554
85
5
Valid Number
 */

  "removeLastDigit" should "take the final number off the string of numbers" in {
    l.removeLastDigit(number) should be ("455673758689985")
  }

  "reverse" should "reverse the list of numbers" in {
    l.reverse(number) should be ("5589986857376554")
  }

  "doSums" should "give the summed value of the current string with all the maths done" in {
    l.doSums("589986857376554") should be (85)
  }

  "mod" should "return the the remainder when mod 10 is applied" in {
    l.mod10(85) should be (5)
  }

}
