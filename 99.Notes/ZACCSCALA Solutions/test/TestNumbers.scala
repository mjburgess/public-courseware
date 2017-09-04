package Ex4

import Ex5.Numbers
import org.scalatest.{FlatSpec, Matchers}

/**
 * 5
 */
class TestNumbers extends FlatSpec with Matchers{

  val n = new Numbers(1,2,3,5,5)
  val m = new Numbers(1,2,3,4,5)

  "The numbers class" should "return the maximum value of the list of numbers" in {
    m.max should be (5)
  }

  it should "return the minimum value in the list of numbers" in {
    m.min should be (1)
  }

  it should "return an average of 3" in {
    m.average should be (3)
  }

  it should "return true or false based on repeating numbers" in {
    n.repeating should be (true)
    m.repeating should be (false)
  }

}
