
import scalaz._
import std.option._, std.list._ // functions and type class instances for Option and List

object ScalazExample extends App {
  println(Traverse[List].traverse(List(1, 2, 3))(i => some(i)))
}