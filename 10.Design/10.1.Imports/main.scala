// IMPORTS

import com.qa._

object Application {

  def main(args: Array[String]) = {
    All.describe()

    println(com.qa.people.Sherlock)   //can use FQN without an import
  }
}