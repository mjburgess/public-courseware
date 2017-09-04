package Ex5

/**
 * 5
 */
class Numbers(one : Int, two : Int, three: Int, four : Int, five : Int) {
  def max : Int = math.max(math.max(math.max(math.max(one, two), three), four), five)
  def min : Int = math.min(math.min(math.min(math.min(one, two), three), four), five)
  def average : Double = (one + two + three + four + five) / 5

  def repeating : Boolean = {
    if (one == two || one == three || one == four || one == five) true
    else if (two == three || two == four || two == five) true
    else if (three == four || three == five) true
    else if (four == five) true
    else false
  }
}
