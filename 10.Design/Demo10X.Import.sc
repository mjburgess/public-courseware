/*

import com.qa._			// main.scala
object Application { def main() = { 
  All.describe()
  println(com.qa.people.Sherlock) //fqn
}}

package com.qa				//library.scala
object All {  	
  import com.qa.people._
  import com.qa.people.workers._
  def describe() = pp("doctor", Watson)
}
			    		//people.scala
package com.qa.people { 
  object Sherlock { override def toString() = "S" }
}
package com.qa.people.workers {
  object Watson   { override def toString() = "W" } 
}

package com

//all available within com.qa

package object qa {
  val AUTHOR = "Michael"
  type Book[P] = Map[String, P]

  def pp(title: String, o: Any) = 
	println(title + ": " + o.toString)

}

*/