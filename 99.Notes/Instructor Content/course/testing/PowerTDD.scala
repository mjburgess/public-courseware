package testing

import collection.mutable.Stack
import org.scalatest._
import org.scalatest.Assertions._

class PowerTDD extends FlatSpec with Matchers {
  def power(x: Int, n: Int): Int = 1
  
	"The power function" should "return 1 if the power is zero" in {
		val x = power(2, 0)
		x should be (1)
	}
}