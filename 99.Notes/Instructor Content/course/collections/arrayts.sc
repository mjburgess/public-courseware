package collections

import scala.collection.mutable.ArrayBuffer

object arrayts {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  

	val ab = ArrayBuffer[Int]()               //> ab  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()

	ab += 1                                   //> res0: collections.arrayts.ab.type = ArrayBuffer(1)
	ab += (2, 3, 4)                           //> res1: collections.arrayts.ab.type = ArrayBuffer(1, 2, 3, 4)
	
	val lst = List(0, 1)                      //> lst  : List[Int] = List(0, 1)
	ab ++= lst                                //> res2: collections.arrayts.ab.type = ArrayBuffer(1, 2, 3, 4, 0, 1)
	
	val ad = -1 +: ab                         //> ad  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(-1, 1, 2, 3, 4
                                                  //| , 0, 1)
	
	ab.trimEnd(3)
  ab.toArray                                      //> res3: Array[Int] = Array(1, 2, 3)
  
  val ac = ArrayBuffer("a", "b", "c")             //> ac  : scala.collection.mutable.ArrayBuffer[String] = ArrayBuffer(a, b, c)
  val z = (ac :\ "")(_ + _)                       //> z  : String = abc
  val z1 = ("" /: ac)(_ + _)                      //> z1  : String = abc
}