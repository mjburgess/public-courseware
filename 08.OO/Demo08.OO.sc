// CHAPTER 7:  Object Orientation
// PROBLEM:   Manage item discounts at a store.
// PROCESS:   Demonstration & Discussion
// EXP?       OOP: classes, interfaces, abstracts, mixins
// USE?       Tools for design: describing objects.


// PART 1 -- "CLASS"s
{

  // RECALL: ALL VALUES ARE OBJECTS
  // Q. what does name refer to? A. an object
  val name = "Jefferson"

  //objects have:
  println(name)                           // state
  println(name.getClass.getSimpleName)    // class

  println((name.getClass.getMethods map { _.getName }).toSet) //  methods
  println(name.hashCode)                  // uniqueness


  /*
  Jefferson
  String
  Set(getChars, equalsIgnoreCase, notify, format, 
  regionMatches, wait, replace, valueOf, join, 
  codePointAt, replaceFirst, ..., charAt)
  -1624405174
  */
}

{

  // CLASSES
  // classes define a type and a mechanism to create objects
  class Person

  val me: Person = new Person     // LHS = Type;  RHS = Constructor

  me.isInstanceOf[Person]
  me.getClass.getName
  me.getClass.getMethods

  /*
  true
  defined class Person
  me: Person = Person@3605ab16
  res11: Array[java.lang.reflect.Method] = Array(... public final native void java.lang.Object.notifyAll())
  */
}

{
  // READING CLASS DEFINITIONS

  //each object may have state
  class Human(val name: String)

  val queen = new Human("Queen Elizabeth II")
  val king = new Human("Henry VIII")

  println(king.name)
  println(queen.name)

  // every object can...
  class Employee(val name: String, val id: Int) {
    def describe() = s"${name} (ID: ${id})"
  }

  val you = new Employee("John Doe", 101)

  println(you.describe)
  println(you.describe())

  /*

  Henry VIII
  Queen Elizabeth II
  John Doe (ID: 101)
  John Doe (ID: 101)
  */
}


{
  // DEFINING CLASSES

  // all these definitions may be placed within the body
  class Counter {
    private var count = 0
    def increment() = { count += 1 }
    def value() = count
  }

  var c = 0

  (c += 1)

  // error // (c += 1) *2

  /*
  defined class Counter
  c: Int = 1
  */
}

{

  // JAVA INSPECTION OF SCALA CLASSES
  class Person {
    val name : String = "Bob"
    var age : Int = 21

    def birthday = age += 1
  }

  // $ scalac person.scala
  // $ javap Person.class

  /*
    Compiled from "test.sc"
    public class Person {
      public java.lang.String name();
      public int age();
      public void age_$eq(int);
      public void birthday();
      public Person();
    }
  */

}

// SCALA VS JAVA
/*
* each scala file may declare a package 
* that package may define as many types (classes, traits, ..) as it wishes

* there are no access modifiers on classes
* nor any enforced *file* naming convention
* scalac will generate several .class files per .scala file

* for java, type definitions are usually comprised of 100+-lines
* and so several per file are forbidden
* however many class definitions scala will be one line,
* and most under 50

* compared to java, interfaces, statics
* and constructors are all vary in syntax and semantics
* but are close-enough in simple cases to map to java's implementation
*/



{
  // SIMPLE CONSTRUCTORS
  class Human(val name: String, var age: Int)

  class Person(val name: String, var age: Int) {
    println("Hello World")

    def x(y: Int) = y
  }

  new Person("Me", 100)

  /*
    Hello World
  */
}

{

  // AUXILIARY CONSTRUCTORS
  class Database(val host: String, val user: String, val pass: String) {
    def this(user: String, pass: String) = this("localhost", user, pass)
  }

  val localDb = new Database("mjb", "pwd")
  val remoteDb = new Database("192.0.0.2", "mjb", "pwd")

  /*

  */


}

{
  //CONSTRUCTOR ARGUMENTS vs. PROPERTIES

  class User(username: String) {
    val first: String = username.split(" ")(0)

    def name: String = username
  }


  val me = new User("michael")
  println(me.name)
  println(me.first)

  //but not --  println(me.username)

  /*
    michael
    michael
  */
}

{

  // METHOD OVERLOADING (AD HOC POLYM.)
  class Room {
    val myKey = 12

    def open() = println("The Door Rattles!")

    def open(theirKey: Int) = println(
      if (theirKey == myKey)
        "The Door Opens!"
      else "The Key Breaks!"
    )
  }

  val shed = new Room

  shed.open()
  shed.open(12)
  shed.open(14)

  /*
    The Door Rattles!
    The Door Opens!
    The Key Breaks!
  */

}

{
  // METHOD SUGAR: GETTERS AND SETTERS
  class Person {
    private val name : String = "Bob"
    private var myage : Int = 21

    def birthday = myage += 1

    def age = myage

    def age_=(a: Int) {
      myage = if(a < 18) 18 else a
    }
  }

  val me = new Person

  me.age            //getter

  me.age = 18       //setter
  me.age_=(18)      //setter, eqv. to above

  /*
    defined class Person
    me: Person = Person@628bd77e
    me.age: Int = 18
  */

}


// ASIDE: SETTERS & GETTERS
// class definition
// var = both getters and setters 
// val = getters 
// none = neither getters nor setters 
// private var or val = neither

// case class = default getters -- all fields public 
// class = default none -- all fields private

{
  //ASIDE: OPERATOR METHODS
  class Fraction(t: Int, b: Int) {
    val top: Int    = t / gcd(t, b)
    val bottom: Int = b / gcd(t, b)

    private def gcd(x: Int, y: Int): Int =
      if (x == 0)
        y
      else if (x < 0)
        gcd(-x, y)
      else if (y < 0)
        -gcd(x, -y)
      else
        gcd(y % x, x)

    def +(that: Fraction) = new Fraction(
      this.top * that.bottom + that.top * this.bottom,
      this.bottom * that.bottom
    )
  }

  var x = new Fraction(0, 1)

  for (_ <- 1 to 3) x += new Fraction(3, 8)

  println(s"${x.top}/${x.bottom}")

  /*
    9/8
  */
}


//PART 2 -- "OBJECT"S
{
  // THE OBJECT KEYWORD
  object UnitedKingdom {
    val capital = "London"
    val population = 6E7

    var immigration = 5E5
    var emmigration = 1E5

    def netImmigration = UnitedKingdom.immigration - UnitedKingdom.emmigration
  }

  println(UnitedKingdom.netImmigration)


  /*
    400000.0
  */
}


{
  // COMPANIONS
  // callable companions
  class Car(val kind: String)

  object Car {
    def apply() = new Car("AUDI")
  }

  val audi = Car()  // calling *the object*

  println(audi.kind)

  /*
    AUDI
  */
}

{
  // companions as factories
  object Car {
    def apply(make: String) = {
      new Car(make, true)
    }
  }

  class Car private (val make: String, val isSportsCar: Boolean)

  val c = Car("Audi")
  println(c.isSportsCar)

  /*
    true
  */
}

{
  // CASE CLASSES
  case class Item(val name: String, val rrp: Double)

  val myitem = Item("A", 10)

  val message = myitem match {
    case Item("B", _) => "My ITEM IS B!"
    case Item(_, _) => "My ITEM IS SOMETHING!"
  }

  println(message)

  /*
    My ITEM IS SOMETHING!
  */
}

{
  // PATTERN MATCHING WITH CASE CLASSES
  case class Person(val name: String, val age: Int)
  case class Pet(val name: String, val age: Int)

  val me = Person("Sherlock Holmes", 27)
  val dog = Pet("Winston", 5)

  def greeting(thing: Any) = thing match {
    case Person(name, age) => s"Hello, $name!"
    case Pet(name, age) => s"Good $name!"
  }

  println(greeting(me))
  println(greeting(dog))

  /*
  Hello, Sherlock Holmes!
  Good Winston!
  */
}



{
  // THE MECHANISM OF PATTERN MATCHING
  class Person(val name: String, val age: Int)

  object Person {
    def apply(n: String, a: Int) = new Person(n, a + 5)
    def unapply(p: Person) = Some((p.name, p.age))
  }

  val aliceSm = Person("Alice Smith", 8)
  val bobSm   = Person("Bob Smith", 35)
  val aliceDo = Person("Alice Doe", 21)

  val message = aliceDo match {
    case Person("Alice Doe", _)
    => "hello alice doe!"

    case Person(name, _)
      if name.contains("Alice")
    => "hello alice!"

    case Person(_, age)
      if age > 18
    => "hello adult!"

    case Person(_, _)
    => "hello child"
  }

  /*
    message: String = hello alice doe!
  */
}


{
  //ASIDE: CASE OBJECTS
  //`sealed` restricts inheritance to within the file alone
  sealed trait Colour
  case object Red extends Colour
  case object Green extends Colour
  case object Amber extends Colour


  def action(signal: Colour) = signal match {
    case Red => "Stop!"
    case Green => "Go!"
    case _ => "Wait!"
  }

  val theSignal = Amber
  println(action(theSignal))

  /*
    Wait!
  */
}