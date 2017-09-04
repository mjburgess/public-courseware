sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](item: A, tail: List[A]) extends List[A]

// Companion object for List trait
object List {
  // apply() is a factory method
  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  // tail is a useful function, and pretty simple
  def tail[A](l: List[A]) =
    l match {
      case Nil => sys.error("tail of empty list")
      case Cons(_,t) => t
    }

  // As is head
  def head[A](l: List[A]) =
    l match {
      case Nil => sys.error("head of empty list")
      case Cons(h,_) => h
    }

  //7
  // Drop n items from a list
  def drop[A](l: List[A], n: Int): List[A] =
    if (n <= 0) l
    else l match {
      case Nil => Nil
      case Cons(_,t) => drop(t, n-1)
    }

  //8
  // Append two lists
  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  //9
  // Reverse a list
  def reverse[A](s: List[A]): List[A] = {
    def rv[A](s: List[A], d: List[A]): List[A] = s match {
      case Nil => d
      case _ => rv(List.tail(s), new Cons[A](List.head(s), d))
    }
    rv(s, List[A]())
  }

  //10
  //map
  def map[A](list: List[A], f: A => A): List[A] = {
    def m(list: List[A], f: A => A, newList: List[A]): List[A] = {
      list match {
        case Nil => reverse(newList)
        case Cons(h, t) => m(t, f, new Cons[A](f(h), newList))
      }
    }

    m(list, f, List())
  }

  //11
  //filter
  def filter[A](list:List[A], f: A => Boolean): List[A] = {
    def filt[A](list: List[A], f: A => Boolean, newList: List[A]): List[A] = {

      list match {
        case Nil => reverse(newList)
        case Cons(h, t) if f(h) => filt(t, f, new Cons[A](h, newList))
        case Cons(h, t) /*if !f(h)*/ => filt(t, f, newList)
      }
    }

    filt(list, f, List())
  }

  //12
  //take
  def take[A](list:List[A], n: Int) = {
    def tk[A](list:List[A], n: Int, newList:List[A]): List[A] =
      n match {
        case 0 => reverse(newList)
        case _ => tk(tail(list), n-1, new Cons[A](head(list), newList))
      }

    tk(list, n, List())
  }
}


//test them all

// Create an empty list
val l = Nil
// Create some lists using Cons
val l1 = new Cons(1, new Cons(2, Nil))
val l2 = new Cons(1, new Cons(2, new Cons(3, Nil)))

// Create lists using apply()
val l3 = List(1, 2)
val l4 = List(1, 2, 3)

val l5 = List('a', 'b', 'c', 'd', 'e', 'f')
// Drop 2 items from the list
List.drop(l5, 2)
// Dropping everything should return nil
List.drop(l5, 7)

// Append two lists
List.append(l3, l4)
List.append(l3, Nil)
List.append(Nil, l4)
// Reverse a list
List.reverse(l4)
List.reverse(l5)
//take three from a list
List.take(l5, 3)
//filter the odd numbers
List.filter(List(1, 2, 3,4), (x:Int) => x%2 == 0)
//map the list
List.map(List(1,2,3), (x: Int) => x * 2)
