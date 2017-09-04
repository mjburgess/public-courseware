
sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](item: A, tail: List[A]) extends List[A]

// Companion object for List trait
object List {
  // apply() is a factory method
  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}

// Create an empty list
val l = Nil
// Create some lists using Cons
val l1 = new Cons(1, new Cons(2, Nil))
val l2 = new Cons(1, new Cons(2, new Cons(3, Nil)))

// Create lists using apply()
val l3 = List(1, 2)
val l4 = List(1, 2, 3)
