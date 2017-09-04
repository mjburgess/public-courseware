package com.qa

object All {
  import com.qa.people._
  import com.qa.people.workers._

  def describe() = {
    pp("detective", Sherlock)
    pp("doctor", Watson)
  }
}
