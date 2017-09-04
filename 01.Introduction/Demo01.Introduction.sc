// CHAPTER 1:  INTRODUCTION
// PROBLEM:
// PROCESS: Demonstrations & Discussion
// EXP? Scala?
// USE? High-level understanding of scala, scalac, repl.

// this introduction will illustrate difficult concepts up front
// then each of these will be considered in detail
// through the rest of the course

{
  //WHY SCALA?
  //a powerful language with "killer libraries"
  //especially relevant in BigData applications
  //modernizing replacement for java 
}

{ // WHY SCALA? -- libraries
  //eg., futures
  import scala.concurrent._
  import scala.concurrent.ExecutionContext.Implicits.global

  Future {
    Thread.sleep(500)
    List("Sherlock Holmes", "Thomas Jefferson")
  } map {
    _ map {
      _.toUpperCase
    }
  } map {
    println(_)
  }

  /*
  List(SHERLOCK HOLMES, THOMAS JEFFERSON)
   */
}

{ //WHY SCALA -- static typing

  //scala is a statically typed
  //object-functional language 

  val name = "Michael" // statically inferred
  var age = 27

  //in full
  val fname: String = "Michael" : String
  var fage: Int = 27 : Int

  def describe(name: String, age: Int) =
    s"${name} (${age})" // functions as in->out

  println(describe(name, age))



  // Q. what is the advantage of static typing?
  // A. compiler checks correctness of program

  /*
  scala> name: String = Michael
  scala> age: Int = 27
  scala> Michael (27)
   */
}

{
  //WHY SCALA -- compositional syntax
  def describe(name: String, age: Int) = s"${name} (${age})"

  val name = "Michael"
  val age = 27

  println(
    describe(name, age) + " is " + (
      if (age >= 18) {
        "allowed"
      } else {
        "not allowed"
      }
    ))

  /*
  scala> Michael (27) is allowed
  */
}


{ //WHY SCALA -- simplify oo design

  class Person(val name: String, val age: Int) {
    def describe() = s"${name} (${age})"
  }

  println(
    new Person("Sherlock", 27).describe()
  )

  /*
  scala> defined class Person
  scala> Sherlock (27)
   */
}

{
  // WHY SCALA? -- functional
  val people = List(
    Map("id" -> 1, "name" -> "Sherlock",
      "address" -> "London, United Kingdom"),

    Map("id" -> 2, "name" -> "Jefferson",
      "address" -> "Virginia, United States")
  )

  println(
    people map { _("name") } mkString ", "
  )

  /*
  scala> people: List[...] = List(Map(id -> 1, name -> Sherlock, ...))
  scala> Sherlock, Jefferson
   */
}

{
  //WHY SCALA? -- powerful
  val svc = io.Source.fromFile("/etc/services")
  // """C:\Windows\System32\drivers\etc\services"""

  val inUsePorts = (for (
    line <- svc.getLines;
    port <- """\d+""".r.findFirstIn(line)
  ) yield port.toInt).toSet

  val freePorts = Set(1 to 200: _*) &~ inUsePorts

  /*
scala>
freePorts: scala.collection.immutable.Set[Int] = Set(110, 196, 74,
109, 149, 71, 172, 81, 72, 146, 195)
*/
}

// OBJECT ORIENTED PROGRAMMING

{ //OO -- imperative programming
  var age = 26 //state

  age += 1 //state change

  println(age) //action


  /*
  scala> age: Int = 26
  scala> 27
   */
}


{
  // OO -- procedural
  def show_next_age() {
    var my_age = 27
    my_age += 1
    println(my_age)
  }

  show_next_age() //entire app is an action

  /*
scala> show_next_age: ()Unit
scala> 27
 */
}

{
  //OO -- objects: state + behaviour
  class Human(var age: Int) {
    def grow_older() {
      age += 1
    }

    def describe() {
      println(age)
    }
  }

  val me = new Human(27)

  me.grow_older()       // actions
  me.describe()         // ugly!

  /*
  scala> defined class Human
  scala> me: Human = Human@65cc5252
  scala> 28
   */
}

// FUNCTIONAL PROGRAMMING

{ //FP -- functions vs. procedures
  val age = 27

  // expression
  def grow_older(age: Int) = age + 1
  val new_age = grow_older(age) // beautiful!


  println(new_age)  // an action *eventually*

  /*
  scala> grow_older: (age: Int)Int
  scala> new_age: Int = 28
  scala> 28
   */
}

{
  //FP -- referential transparency
  val age = 27
  val new_age = age + 1 // beautiful!
  println(new_age)

  // vs.
  class Human(var age: Int) {
    def grow_older() {
      age += 1
    }

    def describe() {
      println(age)
    }
  }

  val you = new Human(27)

  you.grow_older() //  CANNOT BE REPLACED!
  you.describe()

  /*
  scala> you: Human = Human@2dac2e1b
  scala> 29
   */
}


// OO & FUNCTIONAL
{
  //OOFP -- basic flaw in imperative programming is state
  var population = 1000

  println(population)

  population *= 2

  println(population)

  /*
  scala> population: Int = 1000
  scala> 1000
  scala> 2000
   */

}

{
  //OOFP --reuse object metaphors without state
  class Country(val population: Int)

  val now_country = new Country(1000)
  val then_country = new Country(now_country.population * 2)

  println(now_country.population)
  println(then_country.population)

  /*
  scala> defined class Country
  scala> now_country: Country = Country@7848321e
  scala> then_country: Country = Country@756c67cd
  scala> 1000
  scala> 2000
   */

}

{ // cont...
  //or,
  class Country(val population: Int)
  val cntrs = List(new Country(10), new Country(20))

  //transform
  cntrs map {
    _.population * 2
  } foreach { println(_) }


  // old value still around
  cntrs foreach { c => println(c.population) }

  /*
  scala> cntrs: List[Country] = List(/**/)
  scala>
  20
  40

  scala>
  10
  20
   */
}


//ANATOMY OF PROGRAMS
{/*
  //Anatomy of Scala Programs (GENERAL)
  //$ scalac myapp.scala && scala MyApp
  object MyApp extends App {
    println("Hello World")
  }

  /*
  Hello World
  */
*/}

{/*
  // Anantomy of Scala Programs (JAVA)
  //$ scalac main.scala
  //$ javap HelloWorld.class
  object HelloWorld {
    def main(a: Array[String]) {
      println("Hello World!")
    }
  }

  /*
  Hello World!
  */
*/}



// REPL
// :help
// :paste

{

  // TRY THE REPL
  val name = "Michael"
  var age = 27

  println(name + " is " + age)

  if (age >= 18) {
    println("You're allowed in!")
  } else {
    println("You're not allowed in!")
  }

  def describe(name: String, age: Int) =
    s"${name} (${age})"

  println(describe(name, age))

  println(
    describe(name, age) + " is " + (
      if (age >= 18) {
        "allowed in"
      } else {
        "not allowed in"
      }
    )
  )


  /*
  name: String = Michael
  scala> age: Int = 27
  scala> Michael is 27
  scala> You're allowed in!
  scala> describe: (name: String, age: Int)String
  scala> Michael (27)
   */
}


// SCALAC !
// programs are typically compiled for jvm (jvm intermediate byte code)
// jvm installed on target system

/*
$ scalac -help
Usage: scalac <options> <source files>
where possible standard options include:

$ scala -help
Usage: scala <options> [<script|class|object|jar> <arguments>]
   or  scala -help
All options to scalac (see scalac -help) are also allowed.

$ scala
Welcome to Scala 2.11.8 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_111).
Type in expressions for evaluation. Or try :help.

scala> 
 */

// next compiler: dotty


// SCALA INTERPRETER
// scala linear.sc


// IDE
/*
    scala worksheets demo
*/

// SBT
/* build file demo */
/* console demo */
//$ sbt
//> console

//build file
{/*
  val json4sNative = "org.json4s" %% "json4s-native" % "latest.integration"

  lazy val root = (
    project in file(".")
  ).settings(
    // default project, current folder
    name := "hello",
    version := "1.0",
    libraryDependencies += json4sNative
  )

  scalaVersion := "2.11.8"
*/}


{/*
  //application
  import org.json4s._
  import org.json4s.native.JsonMethods._

  case class EmailAccount(
                           accountName: String,
                           url: String,
                           username: String,
                           contacts: List[String]
                         )

  object JsonEg extends App {
    implicit val df = DefaultFormats

    val json = parse("""{
    "accounts": [ { "email": {
        "accountName": "YMail",
        "username": "USERNAME",
        "url": "imap.yahoo.com",
        "contacts": ["barney", "betty"]
    }}, { "email": {
        "accountName": "Gmail",
        "username": "USER",
        "url": "imap.gmail.com",
        "contacts": ["pebbles", "bam-bam"]
    }}
  ]}""")


    var els = (json \\ "email").children map {
      _.extract[EmailAccount]
    }

    println(els)
  }
*/}

// Documentation
// scala-lang.org
// books: Scala 3E

