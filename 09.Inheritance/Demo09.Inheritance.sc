// CHAPTER 8:  TRAITS AND INHERITANCE
// PROBLEM:   Use inheritance to define relationships between kinds of people.
// PROCESS:   Demonstration & Discussion
// EXP?       Inheritance, Interfaces, Mixins
// USE?       Tools for design: describing relationships between types.

// PART 1 -- INHERITANCE

{
  // INHERITANCE  -- A TYPE RELATION
  class Animal
  class Mammal extends Animal

  val pig = new Mammal

  println(pig.isInstanceOf[Animal])
  println(pig.isInstanceOf[Mammal])


  /*
    true
    true
  */
}

{
  //INHERITANCE -- CLASS RELATIONSHIP

  class ElectricItem {
    def switchOn() = println("turning on...")
  }

  class iPad extends ElectricItem {
    def changeBrightness() = println("Changing brightness")
  }

  val myipad = new iPad

  myipad.switchOn()
  myipad.changeBrightness()

  /*
    turning on...
    Changing brightness
  */

}


{
  // OVERRIDING METHODS
  class American {
    def speak() = println("I'd like some lemonade!")
  }

  class NorthAmerican extends American {
    override def speak() = println("I'd like some soda")
  }

  class NewYorker extends NorthAmerican {
    override def speak() = println("I'd like some pop")
  }

  class SouthernAmerica extends American {
    override def speak() = println("I'd like some coke")
  }

  val john = new American()
  val sue = new NewYorker()

  john.speak()
  sue.speak()

  /*
    I'd like some lemonade!
    I'd like some pop
  */
}

{
  //OVERRIDING toString

  class Person(val name: String, var age: Int) {
    override def toString = s"Person(name: $name, age: $age)"
  }

  println(new Person("Bertrand Russell", 97))

  /*
    Person(name: Bertrand Russell, age: 97)
  */
}

{
  // CONSTRUCTING PARENTS
  class Point(val x: Int, val y: Int) {
    override def toString = s"<$x, $y>"
  }

  class WeightedPoint(xw: Int, yw:Int, ww: Int) extends Point(xw * ww, yw * ww)

  val start = new Point(0,0)
  val next  = new WeightedPoint(10, 10, 5)

  println(start)
  println(next)

  /*
    <0, 0>
    <50, 50>
  */
}


{
  //CALLING THE PARENT METHOD
  class American {
    def speak() = print("I'd like some ")
  }

  class NorthAmerican extends American {
    override def speak() = { super.speak(); print(" lemonade") }
  }

  class NewYorker extends NorthAmerican {
    override def speak() = { super.speak(); print(" soda") }
  }


  val john = new NorthAmerican()
  val sue = new NewYorker()

  john.speak()
  println()
  sue.speak()

  /*
    I'd like some  lemonade
    I'd like some  lemonade soda
  */
}



{
  // ABSTRACTS
  abstract class BankAccount(var balance: Double) {
    def convert(incoming: Double): Double

    def withdraw(some: Double) = {
      balance -= convert(some)
    }
  }

  class UkBankAccount(b: Double) extends BankAccount(b) {
    def convert(incoming: Double) = incoming
  }


  class UsBankAccount(b: Double) extends BankAccount(b) {
    def convert(incoming: Double) = incoming * 1.4
  }

  abstract class Animal(val name: String, val age: Int) {
    override def toString = s"Animal(name: $name, age: $age")
    def move: String
  }

  class Cat(n: String, a: Int) extends Animal(n, a) {
    override def toString = s"Cat(name: $name, age: $age)"
    def move = "pounce"
  }

  /*

  */
}

{ // ABSTRACTS as TYPES

  abstract class Animal
  class Dog extends Animal
  class JackRussel extends Dog

  val a: Animal = new Dog
  val wz = new JackRussel

  println(a.isInstanceOf[Dog])
  println(wz.isInstanceOf[Animal])

  /*
    true
    true
  */
}




// PART 2 -- TRAITS 

{
  // TRAITS
  trait Walkable {
    def walks: String
  }

  class Alice extends Walkable {
    def walks = "Alice Walks"
  }

  println((new Alice).walks)

  /*
    Alice Walks
  */
}


{
  // TRAIT INHERITANCE
  trait Moveable {
    def move: String = "Moves..."
  }

  trait Walkable extends Moveable {
    override def move: String = "Walks..."
  }

  trait Talkable {
    def talks: String
  }

  class Bob extends Walkable with Talkable {
    def talks = "Bob Talks"
  }

  /*

  */
}

{
  // COMPOSING OBJECTS WITH TRAITS
  trait Mover {
    def move(distance: Int): Unit

    def convert(miles: Int) = miles * 1.6
  }

  trait Eater {
    def eat(amount: Int): Unit

    def goToStore() = println("buying groceries")
  }

  trait Talker {
    def speak() =  println("HELLO!")
  }

  class Person extends Mover with Eater {
    def move(distance: Int) = println(s"moving $distance km")
    def eat(amount: Int) = println(s"eating $amount kg")
  }


  val me = new Person with Talker

  me.speak()

  /*
    HELLO!
  */
}

{
  // THE MEANING OF SUPER
  trait Mover {
    def move = println("Moving")
  }
  trait Walker extends Mover {
    override def move = { println("Walk"); super.move }
  }
  trait Flyer extends Mover {
    override def move = { println("Flying"); super.move }
  }
  trait Runner extends Mover {
    override def move = { println("Runs"); super.move }
  }
  trait Sprinter extends Runner {
    override def move = { println("Sprint"); super.move }
  }

  class FastBird extends Walker with Flyer with Sprinter

  (new FastBird).move

  /*
    Sprint
    Runs
    Flying
    Walk
    Moving
  */
}


{
  // TRAITS AS MIXINS
  // slice of functionality *without* state
  trait Speak {
    def name(): String                                  //abstract
    def speak() = println("Hello! I am " + name)        // can depend on state provided by mixer
  }


  class Person {
    def walk() = println("Walking around...")
    def name() = "Unknown"
  }

  class American extends Person with Speak
  class Australian extends Person with Speak


  val john = new American
  val kevin = new Australian

  john.walk
  kevin.speak

  /*
    Walking around...
    Hello! I am Unknown
  */
}



{
  //SELF TYPES 

  //suppose we have a trait Engine:
  //and another which requires Engine's functionality:

  trait Engine {
    def start() = ??? 
  }

  //we could inherit, but this would fix the implementation

  trait UsesEngine extends Engine

  //more generically, use a self-type:

  trait Car {
    self: Engine =>         // self-type
    def drive() =  start()  // method on Engine 
  }

  // a self-type means "will be an " Engine when created
  // its a kind of pseudo-inheritance

  trait DieselEngine extends Engine { }

  //we give MyCar, Car's functionality
  //Car requires *an* Engine, here we give a specialization:

  object MyCar extends Car with DieselEngine


  //self-types are not part of the heirachy
  sealed trait Person
  trait Student extends Person
  trait Teacher extends Person
  trait Adult { this : Person => } 

  val p : Person = new Student {}
  p match {
    case s : Student => println("a student")
    case t : Teacher => println("a teacher")
  } // that's it we're exhaustive

  /*
      a student
  */
} 

{
  // CAKE PATTERN

  //consider one class reusing functionality defined elsewhere:
  trait ConnectionService {
    def connect() = "...CONNECTION..."
  }

  class UserTable extends ConnectionService  {
    def getUsers() = println(connect())
  }

  //if UT extends the service then the implementation is fixed


  //if we use a more general self-type
  //we can vary the specific implementation at new-time

  trait MySqlConnect extends ConnectionService { 
    override def connect() = "...MYSQL CONNECTIONS.."
  }

  trait MongoConnect extends ConnectionService {
    override def connect() = "...Mongo CONNECTIONS.."
  }

  class User { self : ConnectionService =>
    def getUsers() = println(connect())
  }

  //here we chose which we want
  //so that User can be written generally 

  val u1 = new User with MongoConnect
  val u2 = new User with MySqlConnect

  u1.getUsers()
  u2.getUsers()

  /*
  ...Mongo CONNECTIONS..
  ...MYSQL CONNECTIONS..
  */
}


// THE MEANING OF SEALED
// sealed == final except for the file

{
  // ASIDE: TYPE MEMBERS
  abstract class MyContainer {
    type Index = Int
    type Element

    def get(position: Index): Element
  }

  class MyCollection(val arr: Array[String]) extends MyContainer {
    type Element = String

    def get(position: Index): Element = arr(position)
  }

  /*

  */
}






// bottom type hierachies
/*
* scala has a triadic "bottom type" structure:

* `scala.AnyVal`
* base of any typically unbox'd value in java
* child type is `scala.Nothing`
* includes `scala.Unit` type whose single value is   `()`
* `AnyVal`s of a single data property may be optimized away to just that property

* `scala.AnyRef`
* everything else (incl. java.lang.Object)
* child type is `scala.Null`

* `scala.Any` is parent (supertype) of both

* unrelated to this structure there is also `Nil`
* it means "empty sequence"
* it is `List[Nothing]`
*/

