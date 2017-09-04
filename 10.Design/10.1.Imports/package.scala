package com

package object qa {   //therefore all available within com.qa

  val AUTHOR = "Michael"

  type Book[P] = Map[String, P]

  def pp(title: String, o: Any) = {
    println(title + ": ")
    println(o)
    println()
  }
}