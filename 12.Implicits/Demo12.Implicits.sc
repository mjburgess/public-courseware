// CHAPTER 12:  IMPLICITS
// PROBLEM: 
// PROCESS:
// EXP?
// USE? 



{
  // IMPLICIT CONVERSIONS
  implicit def stringToInt(s: String): Int = s.length         //capability?

  val x: Int = "Michael"          // crazy!

  // let's add methods to string objects...

  // String can be converted to ImprovedString   (read R2L)
  implicit class ImprovedString(s: String) {
      def describe() = println(s"${s} ${s.length}")
  }

  "22B Baker St".describe

  //... this is actually what scala does to add StringOps to strings...

  // let's add methods to every object... 
  // A can be converted to SpaceShip[A]       -- the generic here says forall types
  implicit final class SpaceShip[A](val self: A) {
      def <-*-> [B](y: B) = self -> y
  }

  // converted to a SpaceShip because <-*-> exists on SpaceShips
  // then <-*-> is called

  println(Map("one" <-*-> 1, "two" <-*-> 2))

  /*
    22B Baker St 12
    Map(one -> 1, two -> 2)
  */
}



{
  // IMPLICIT PARAMTERS
  implicit val prefix = "Logging: "

  def myPrinter(s: String)(implicit prefix: String) = 
    println(prefix + s)

  myPrinter("Hello")



  // as "contexts"
  class Configuration(val host: String, val user: String)

  implicit val ukContext = new Configuration("UK", "Sherlock")

  def connect(pass: String)(implicit c: Configuration) = 
    println(s"connecting to ${c.host} (u: ${c.user}, p: ${pass})")


  // explicit is possible:

  connect("test")(new Configuration("US", "Washington"))

  /*
  Logging: Hello
  connecting to US (u: Washington, p: test)
  */

}

{
  // IMPLICIT PARAMTERS
  // adding implicit down-scope

  def sayHi(f: String => String) = 
    println(f("Hi"))

  def lowerGreeting(implicit greet: String) = 
    greet.toLowerCase

  sayHi( 
    implicit greeting => { 
      lowerGreeting + greeting.toUpperCase 
    } 
  )

  //NB. lowerGreeting gets its implicit from scope
  // as incoming arg was tagged implicit

  /*
    hiHI
  */
}


{
  // TYPE CLASSES with implicit classes

  // some classes
  case class Address(street: String, city: String)
  case class Person(name: String, address: Address)

  // we want to be able to add methods to these classes after defintion 
  // *and* type check on their existence 

  // the typeclass 
  trait Jsonable {
    def toJSON(): String
  }

  implicit class AddressToJSON(address: Address) extends Jsonable {
    def toJSON(): String = s"""{"street": "${address.street}", "city": "${address.city}" }"""
  }

  implicit class PersonToJSON(person: Person) extends Jsonable {
    def toJSON() = s"""{
        "name":    "${person.name}",
        "address": ${person.address.toJSON}
      }"""
  }

  val ldn22B = Address("22B Baker Street", "London")
  val sherlock = Person("Sherlock Holmes", ldn22B)

  println(sherlock.toJSON())


  def send(o: Jsonable) = println(o.toJSON())     // wow! ad hoc polymorphism!

  send(ldn22B)

  /*
    {
      "name":    "Sherlock Holmes",
      "address": {"street": "22B Baker Street", "city": "London" }
    }
    {"street": "22B Baker Street", "city": "London" }
  */
}

{
  //TYPECLASSES with implicit objects

  //without convertions the simplest way to have extra methods 
  //is to make an object that has them:
  case class Address(street: String, city: String)

  trait Jsonable[A] {         
    def toJSON(a: A): String  //we want to toJSON add to any A
  }

  implicit val addrjo = new Jsonable[Address] {
    def toJSON(address: Address): String = 
      s"""{"street": "${address.street}", "city": "${address.city}" }"""
  }

  //and so:
  def send(a: Address, o: Jsonable[Address]) = 
    println(o.toJSON(a))

  send(Address("22B Baker Street", "London"), addrjo)

  //there's a couple problems here: 
  //1. we'd always have to pass the extra object
  //.. solve with an implicit parameter:

  def isend(a: Address)(implicit o: Jsonable[Address]) = 
    println(o.toJSON(a))

  isend(Address("22B Baker Street", "London"))

  //2. this is too tightly coupled to Address
  //.. we would like to use it with anything:

  def send[A](a: A)(implicit o: Jsonable[A])  =
    println(o.toJSON(a))  

  //using implicit objects you can define many implementations of the trait
  //with implicit classes there is only one way Address can be toJson()'d 
  //with implicit objects we can define several

  //so send becomes:
  def send[A](a: A)(implicit o: Jsonable[A])  =
    println(o.toJSON(a))  

  //this can also be written:
  def send[A : Jsonable](a: A) =
    println(implicitly[Jsonable[A]].toJson(a))
  

  //[A : J] is a context bound, it means
  //there must be an implicit value of type J[A] available
  //the type on the RHS of a context bound must be generic.

  /*
    {"street": "22B Baker Street", "city": "London" }
    {"street": "22B Baker Street", "city": "London" }
  */
}

//NB:

/*  via Odersky:

* implicit parameters are execution contexts (eg. types)
* implicit functions are capabilities
* implicit classes are abstractions over types (type classes) 

*/


{
  //IMPLICIT RULES
}