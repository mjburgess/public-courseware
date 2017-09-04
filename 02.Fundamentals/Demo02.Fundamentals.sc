// CHAPTER 2:  FUNDAMENTALS
// PROBLEM:   Use var and val to describe yourself.
// PROBLEM:   Use boolean operators to describe a bar.
// PROBLEM:   Parse complex data types to describe your hobbies.
// PROCESS:   Demonstrations & Discussion
// EXP?       Variables, Objects, Numeric types, Strings.
// USE? 

// PART 1 -- VARIABLES, OBJECTS, TYPES, EXPRESSIONS
// THE LANGUAGE

{ //LANGUAGE -- expressions vs statements
  val age = 27

  if (age > 18) {
    println("Allowed")
  } else {
    println("Not Allowed")
  }

  //vs.
  val message = if (age > 18) {
    "Allowed"
  } else {
    "Not Allowed"
  }

  // the action is delayed to the last moment
  println(message)

  /*
  scala> age: Int = 27
  scala> Allowed
  scala> message: String = Allowed
  scala> Allowed
   */
}


{

  // OBJECTS
  // every value is an object
  // functions are values

  val n = "Jefferson"

  // Q. what does name refer to?
  // A. an object

  //objects have:
  println(n)                         //state
  println(n.getClass.getSimpleName)  //class

  println((
    n.getClass.getMethods map {
      _.getName
    }
    ).toSet)                            //methods


  println(n.hashCode) // a uniquely identifer
  // eg. a memory address

  /*
  n: String = Jefferson
  scala> Jefferson
  scala> String

  scala>  Set(getChars, equalsIgnoreCase, notify,
  format, regionMatches, wait, replace, valueOf,
  join, codePointAt, replaceFirst, equals, startsWith,
  lastIndexOf, toLowerCase, concat, toCharArray,
  codePoints, compareToIgnoreCase, notifyAll, chars,
  )

  scala> -1624405174
   */
}

{

  // CALLING METHODS
  val name = "michael"
  
  print(3 + 2)
  3.+(2)
  println(name.toUpperCase())
  println(name.toUpperCase)

  // parens group arguments,
  // the . "sends the message",
  // i.e., calls the method

  /*
  5
  scala> res40: Int = 5
  scala> MICHAEL
  scala> MICHAEL
   */
}


{/* // METHODS FROM THE REPL
  name.
*/}

{

  // OPERATORS AS METHODS
  // left assoc
  2 + 3
  2.+(3)

  //right assoc
  5 +: List(1, 2, 3)
  List(1, 2, 3).+:(5)

  // error // 5 + List(3,4)

  /*
  scala> res43: Int = 5
  scala> res44: Int = 5

  scala> res45: List[Int] = List(5, 1, 2, 3)
  scala> res46: List[Int] = List(5, 1, 2, 3)
   */

}

{
  // VALUES AND VARIABLES
  val name = "Michael"
  val height = 1.8
  var age = 26

  // error // height = 2
  // error // height += 1

  age += 1

  // container vs contents
  val builder = new StringBuilder("Hello ")
  builder.append("World")


  // changing a var
  age += 1

  var newBuilder = new StringBuilder("Goodbye ")
  newBuilder.append("World")

  println(newBuilder)


  //change which object newBuilder refers to
  newBuilder = builder
  println(newBuilder)

  /*
  name: String = Michael
  scala> height: Double = 1.8
  scala> age: Int = 26
  scala> builder: StringBuilder = Hello
  scala> res48: StringBuilder = Hello World
  scala> age: Int = 18
  scala> newBuilder: StringBuilder = Goodbye
  scala> res50: StringBuilder = Goodbye World
  scala> Goodbye World
  scala> newBuilder: StringBuilder = Hello World
  scala> Hello World
   */
}


{
  // TYPES
  val location = "United States"
  val name = "Michael"

  // Q. what does name refer to? A. an object

  // every object has a class
  println(name.getClass.getSimpleName)

  // every container (ie., variable) has a type
  // the string fits into this container
  // because all strings are also Anys
  val aLocation: Any = location


  // name belongs to multiple types
  println(name.isInstanceOf[String])
  println(name.isInstanceOf[Any])

  /*
  location: String = United States
  scala> String
  scala> aLocation: Any = United States
  scala> true
  scala> true
   */
}

{
  // THE TYPE OF EXCEPTIONS
  val isChild = if (age < 18) 1 else "Nope"

  // Q. what is the type of isChild ?
  // A. supertype


  val x = if (true) 1 else throw new Exception("Hello")

  // Q. what is the type of x?
  // A.  'throw ...' is whatever type it is used as
}





// PART 2 -- BASIC TYPES
{

  // BOOLEAN
  val age = 21
  val name = "Fido"
  val colour = "Blue"

  // comparison operators
  val isAdult = age > 21
  val isFido = name == "Fido"
  val isRed = colour == "Red"


  // logical operators
  println(isAdult && isRed)
  println(isAdult || isRed)
  println(!isAdult || isFido)

  println(true && true)
  println(true && false)
  println(false && true)
  println(false && false)

  println(true || true)
  println(true || false)
  println(false || true)
  println(false || false)

  /*
  
scala> age: Int = 21
scala> name: String = Fido
scala> colour: String = Blue
scala> isAdult: Boolean = false
scala> isFido: Boolean = true
scala> isRed: Boolean = false
scala> false
scala> false
scala> true
scala> true
scala> false
scala> false
scala> false
scala> true
scala> true
scala> true
scala> false
   */
}


{

  // NUMERIC TYPES
  // ints are circular
  var population = 2147483647

  population += 2

  population


  // bytes and chars
  val nlByte: Byte = 10

  val nl: Char = '\n'
  nl == nlByte
  
  /*
scala> population: Int = 2147483647
scala> res12: Int = -2147483647
scala> nlByte: Byte = 10
scala> nl: Char =
scala> res13: Boolean = true
   */
}

{
  // UNIT
  def say(message: String): Unit = println(message)

  // error // say("hello")  * 2

  val empty = ()
  say("hello") == empty
  
  /*
  
scala> say: (message: String)Unit
scala> empty: Unit = ()

hello
res14: Boolean = true
*/
}


{
  // STRINGS
  println("\tHello\n\tWorld")

  // STRING INTERPOLATION
  val myAge = s"I am ${18 + 8} years old!"

  val name = "Michael"
  val location = "The United Kingdom"
  val message = s"$name is in $location"

  // STRING FORMATTING
  val height = 1.8
  val message = f"Height: $height%.2f"


  // RAW STRINGS
  val path = raw"C:\Windows\Users\Public\Documents"
  val regex = raw"\b[\w|£]+\b"
  val eg = raw"a\nb"

  val anotherPath = """C:\Windows\system32\Drivers\etc"""

  /*

scala> myAge: String = I am 26 years old!
scala> name: String = Michael
scala> location: String = The United Kingdom
scala> message: String = Michael is in The United Kingdom
scala> height: Double = 1.8
scala> message: String = Height: 1.80
scala> path: String = C:\Windows\Users\Public\Documents
scala> regex: String = \b[\w|£]+\b
scala> eg: String = a\nb
scala> anotherPath: String = C:\Windows\system32\Drivers\etc
   */

}

// ERROR // val stdpath  = "C:\Windows\Users\Public\Documents"


{

  // STRING METHODS
  println("-" * 30)
  println("HELLO" + " WORLD")

  // split and join
  val parts = "Michael John Burgess".split(' ')
  parts.mkString(",")

  // slice
  "Michael John Burgess" split (' ') slice(1, 2)

  "Michael John Burgess" split (' ') slice(-1, 2)

  "be the change you want to see".split(" ").drop(2).takeRight(4)
  
  /*
  
scala> ------------------------------
scala> HELLO WORLD
scala> parts: Array[String] = Array(Michael, John, Burgess)
scala> res24: String = Michael,John,Burgess
scala> res25: Array[String] = Array(John)
scala> res26: Array[String] = Array(Michael, John)
scala> res27: Array[String] = Array(you, want, to, see)

   */
}


{
  // others
  val quote = "be the change you wish to see in the world"

  quote.contains("world")

  quote.indexOf("change")

  quote.indexOf("leader")

  quote.toLowerCase

  quote.toUpperCase

  quote.substring(7)

  quote.substring(7, 7 + 6)

  quote.reverse
  
  /*
scala> quote: String = be the change you wish to see in the world
scala> res28: Boolean = true
scala> res29: Int = 7
scala> res30: Int = -1
scala> res31: String = be the change you wish to see in the world
scala> res32: String = BE THE CHANGE YOU WISH TO SEE IN THE WORLD
scala> res33: String = change you wish to see in the world
scala> res34: String = change
scala> res35: String = dlrow eht ni ees ot hsiw uoy egnahc eht eb
   */
}


//PART 3 -- INTRO TO COLLECTIONS
{
  // type arguments
  val names: List[String] = List("Sherlock", "Watson")
  val ages: List[Int] = List(30, 40)

  val as: List[Int] = List[Int](30, 40)

  //.... in full...
  val ns: List[String] = List[String]("Michael" : String, "Watson": String)


  //List[String]  !=  List[Int]
  
  /*
scala> names: List[String] = List(Sherlock, Watson)
scala> ages: List[Int] = List(30, 40)
scala> as: List[Int] = List(30, 40)
scala> ns: List[String] = List(Michael, Watson)
   */
}


{
  // tuples -- not quite collections

  val point = (10, 20)
  println(point)
  println(point._1)
  println(point._2)

  /*
scala> point: (Int, Int) = (10,20)
scala> (10,20)
scala> 10
scala> 20
   */
}

{

  val names = List[String]("Sherlock Holmes", "Mycroft Holmes")

  println(names(0))
  println(names(1))

  println(names)
  val ages = List(10, 20, 30)

  /*
names: List[String] = List(Sherlock Holmes, Mycroft Holmes)
scala> Sherlock Holmes
scala> Mycroft Holmes
scala> List(Sherlock Holmes, Mycroft Holmes)
scala> ages: List[Int] = List(10, 20, 30)
   */
}// lists

{
  // maps
  val people_address = Map(
    "Sherlock" -> "London, UK",
    "Jefferson" -> "Virginia, US"
  )

  println(people_address)
  println(people_address("Sherlock"))
  /*
scala>  people_address: Map[String,String] = Map(Sherlock -> London, UK,
                                                 Jefferson -> Virginia, US)

scala> Map(Sherlock -> London, UK, Jefferson -> Virginia, US)
scala> London, UK
   */
}
