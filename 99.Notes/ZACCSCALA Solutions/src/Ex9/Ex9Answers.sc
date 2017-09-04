//1
def nthItem[A](n: Int, list : List[A]): A = {
  n match {
    case 0 => list.head
    case _ => nthItem(n-1, list.tail)
  }
}

val list = List(1,2,3,4,5,6)
nthItem(2, list)


//2
def removeItems[A](n: A, list: List[A]): List[A] = {
  def removeItemsInt[A](n: A, list: List[A], newList: List[A]): List[A] = {
    list match {
      case Nil => newList.reverse
      case head :: tail if (head != n) => removeItemsInt(n, tail, head :: newList)
      case head :: tail /* if (head == n) */ => removeItemsInt(n, tail, newList)
    }
  }

  removeItemsInt(n, list, List[A]())
}

removeItems(3, List(1,2,3,3,3,3,5,6))

//3
class Person(val name: String, val address: Address)
class Address(val city: String, val country: String)

object Person {
  def apply(name: String, address: Address) = new Person(name, address)
  def unapply(person: Person):Option[(String, Address)] = Some((person.name, person.address))
}

object Address {
  def apply(city: String, country: String) = new Address(city, country)
  def unapply(address: Address): Option[(String, String)] = Some((address.city, address.country))
}

//3c
def livesIn(person : Person, city : String) = {
  person match {
    case Person(_, Address(city2, _)) if (city2 == city) => true
    case _ => false
  }
}

val p1 = Person("alice", Address("London", "UK"))
val p2 = Person("bob", Address("Salford", "UK"))
val p3 = Person("eve", Address("Paris", "France"))

livesIn(p1, "London")
livesIn(p2, "London")
livesIn(p3, "Paris")

//4
trait Expression
//5
case class Const(v: Int) extends Expression
case class Neg(e: Expression) extends Expression
case class Add(l: Expression, r: Expression) extends Expression
//6
def eval(e: Expression): Int = e match {
  case Const(c) => c
  case Neg(ex) => - eval(ex)
  case Add(l, r) => eval(l) + eval(r)
}

val e1 = Add(Const(10), Neg(Add(Const(3), Const(4))))
val v = eval(e1)

