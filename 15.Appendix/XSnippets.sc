//DAY 2 REVIEW 


val pets = List("fido", "fluffy", "spot")

val messages = for(p <- pets) yield p match {
  case "fluffy" => "hi girl"
  case _ => "hi boy"
}

println(messages)




{

// typeclasses with parameters (vs. conversions)
trait Parser[T] {
  def parse(input: String): Option[T]
}

def parse[T](input: String)(implicit parser: Parser[T]): Option[T] =
parser.parse(input)

import util.Try
implicit object IntParser extends Parser[Int] {
  def parse(input: String) = Try(input.toInt).toOption
}
implicit object BooleanParser extends Parser[Boolean] {
  def parse(input: String) = Try(input.toBoolean).toOption
}

}

// TYPE ALIASES



//ASIDE: TYPE ALIASES
type Repeatable =  (String, String) => String

def repeat(str: String, N: Int, fn: Repeatable = (l,r) => l + r)  = {
  var result = "";

  for(i <- 0 to N) result = fn(result, str)

  result
}



// IMMUTABILITY 


class Person(var weight: Double) {
    def eat() = this.weight += 1
    def run() = this.weight *= 0.99
    def wearBelt(): Boolean = this.weight < 100  
}

val me = new Person(100) 
me.eat()
me.eat()
me.run()
me.eat()

println(if(me.wearBelt) { "It fits!" } else { "It hurts!" })

me.run()
me.run()

println(if(me.wearBelt) { "It fits!" } else { "It hurts!" })        // why on earth does this change?



// all data flow is made explicit
class Human(val weight: Double)  {
    def eat() = new Human(this.weight + 1)
    def run() = new Human(this.weight * 0.99)
    def wearBelt(): Boolean = this.weight < 100  
}


val me = new Human(100)

println(if(me.eat().eat().run().eat().wearBelt) { "It fits!" } else { "It hurts!" })        // I see that me is transformed differently
println(if(me.run().run().eat().wearBelt) { "It fits!" } else { "It hurts!" }) 



// IMMUTABILITY
//Q. define an immutable BankAccount class with withdraw() and deposit() methods

//Q. define a gift method, it should accept a list of BankAccounts and give them all 100

//Q. define a Person call with an  ArrayBuffer account history



{


  trait Payment {
      def amount: Double
  }

  class UkDoctorSalary extends Payment {
      def amount = 40000.0
  }

  class UsSpeedingFine extends Payment {
      def amount = 80.0
  }

  class UkConverter[T](val ratio: Double)

  implicit val ukcDR = new UkConverter[UkDoctorSalary](1)
  implicit val ukcSF = new UkConverter[UsSpeedingFine](1.3)

  def pay[T <: Payment](p: T)(implicit c: Currency[T]) = p.amount * c.ratio



  def _pay[T <: Payment : Currency](p: T) = p.amount * implicitly[Currency[T]].ratio


  pay(new UsSpeedingFine)


  // academic exmaple 
  import math.Ordering

  case class MyList[A](list: List[A]) {
    //named
    def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
      list.sortBy(f)(ord)

    //annoymous
    def sortBy2[B : Ordering](f: A => B): List[A] =
      list.sortBy(f)(implicitly[Ordering[B]])
  }

  val list = MyList(List(1,3,5,2,4))

  list sortBy1 (i => -i)
  list sortBy2 (i => -i)
}


{
  //from OO?


  //aprox. with private constructor
  class UK(val capital: String = "London" /*, etc. */)
  val UK = new UK
  println(UK.capital)
}