// CHAPTER 3: FLOW
// PROBLEM:   Use selection expressions to select different greetings.
// PROBLEM:   Use for-comprehensions to analyse a list of people.
// PROCESS:   Demonstration & Discussion.
// EXP?       Branching vs. Selection; Pattern Matching
// USE?       Select and create new values from old given conditions.

// PART 1 -- BRANCHING, SELECTING AND MATCHING
{
  // CONDITIONALS
  val age = 27
  val name = "Michael John Burgess"

  if(age >= 18 && name.contains("John")) {
    println("You're an adult, John!")
  } else {
    println("You're a child, John!")
  }

  val message = if(age < 21 && name.startsWith("Michael")) {
    "Go home, Michael!"
  } else {
    "Come in, Michael!"
  }

  // simple example
  val anotherMessage = if(true) { "25 "} else { 25 }

  println(message)

  /*
scala> age: Int = 27
scala> name: String = Michael John Burgess
scala> You're an adult, John!
scala> message: String = Come in, Michael!
scala> anotherMessage: Any = "25 "
scala> Come in, Michael!

   */
}


{

  // INTRO TO PATTERN MATCHING !
  val name = "Sherlock"
  val location = "London"

  val message = name match {
    case "Sherlock" => "Good Evening, Holmes!"
    case "Watson"   => "Hello, Dr. Watson"
    case _ => "Bonjour!"
  }

  println(message)

  println(location match {
    case "UK" => "Welcome to the UK"
    case _    => "I'm afraid I do not speak that language!"
  })

  /*
  name: String = Sherlock
scala> location: String = London
scala> message: String = Good Evening, Holmes!
scala> Good Evening, Holmes!
scala> I'm afraid I do not speak that language!

   */
}

{
  // destructuring match
  val List(wname, wlocation) = List("Winston", "London")
  val (jname, jlocation) = ("Jefferson", "Virginia")

  val deets = ("JFK", ("New York", 1970))
  val (n, (l, y)) = deets


  //preview:
  case class Person(name: String, age: Int, location: (String, String))

  val me = Person("Michael", 27, ("I st", "Bentonville"))

  val Person(name, age, loc) = me

  /*
  wname: String = Winston
wlocation: String = London
scala> jname: String = Jefferson
jlocation: String = Virginia
scala> deets: (String, (String, Int)) = (JFK,(New York,1970))
scala> n: String = JFK
l: String = New York
y: Int = 1970
scala> defined class Person
scala> me: Person = Person(Michael,27,(I st,Bentonville))
scala> name: String = Michael
age: Int = 27
loc: (String, String) = (I st,Bentonville)

   */
}

// BRANCHING vs. SELECTING
{

}

//PART 2 -- FOR COMPREHENSIONS
{

  for(c <- "Hello") println(c)

  // unitary
  for( letter <- "Michael") {
    println(letter)
  }

  // expression
  val letters = for ( letter <- "Michael") yield letter  + 1

  /*
  H
e
l
l
o
scala> M
i
c
h
a
e
l
scala> letters = Vector(78, 106, 100, 105, 98, 102, 109)

   */
}


{
  // COMPREHENSIONS OVER LISTS
  val names = List("Sherlock", "Jefferson", "Washington")

  for (n <- names) {
    println(n)
  }

  val uNames = for (n <- names ) yield n.toUpperCase

  println(uNames)

  /*
scala> names: List[String] = List(Sherlock, Jefferson, Washington)
scala> Sherlock
Jefferson
Washington
scala> uNames: List[String] = List(SHERLOCK, JEFFERSON, WASHINGTON)
scala> List(SHERLOCK, JEFFERSON, WASHINGTON)

   */
}

{
  // COMPREHENSIONS OVER MAPS
  val people_locations = Map(
    "Sherlock" -> "London",
    "Jefferson" -> "Virginia"
  )

  for( (name, location) <- people_locations) {
    println(s"${name} was born in ${location}")
  }

  val locations = for( (name, location) <- people_locations) yield location

  println(locations)

  /*
people_locations: Map[String,String] = Map(Sherlock -> London,
                                           Jefferson -> Virginia)
scala> Sherlock was born in London
Jefferson was born in Virginia
scala> locations: Iterable[String] = List(London, Virginia)
scala> List(London, Virginia)
   */
}

{

  // COMPREHENSIONS OVER RANGES
  val range = 1 to 10

  val numbers = for(i <- 1 to 5) yield i

  for(i <- 1 to 5) println(i)
  for(j <- 10 until 50 by 10) println(j)
  for(z <- 100.to(200).by(50)) println(z)


  val ages = for(
    age <- 18 to 19
    if age % 2 == 0
  ) yield age

  /*
range: Range.Inclusive = Range 1 to 10
scala> numbers: IndexedSeq[Int] = Vector(1, 2, 3, 4, 5)
scala> 1
2
3
4
5
scala> 10
20
30
40
scala> 100
150
200
scala> ages: IndexedSeq[Int] = Vector(18)
*/
}



{
  // OPTION

  val x: Any = 5
  val y: Any = "X"         // Any = String | Int | ...

  val name: Option[String] = None           // Option[String] = None | Some
  val location: Option[String] = Some("UK")
  
  for {
    l <- location
    n <- name 
  } println(l + n)
  
  /*
  x: Any = 5
scala> y: Any = X
scala> name: Option[String] = None
scala> location: Option[String] = Some(UK)
   */
}



{

  // MULTIPLE EXTRACTION
  val names = List(
    "Michael Burgess".split(" "),
    "Michael Holmes".split(" "),
    "Sherlock Holmes".split(" ")
  )

  for(
    parts <- names;
    part <- parts
  ) println(part)

  println(
    for {                       //NB: brances & no semicolons
      parts <- names            //this is an alternative, and preffered style 
      part <- parts             //for multiple generators  (ie., multiple <- )
     } yield part
  )

  for(x <- "ABCDEF"; y <- 0 until 6) yield x + y.toString

  /*
scala> names: List[Array[String]] = List(Array(Michael, Burgess),
                                         Array(Michael, Holmes),
                                         Array(Sherlock, Holmes))
scala> Michael
Burgess
Michael
Holmes
Sherlock
Holmes
scala> List(Michael, Burgess, Michael, Holmes, Sherlock, Holmes)
scala> res15: IndexedSeq[String] = Vector(A0, A1, A2, A3, A4, A5,
                                          B0, B1, B2, B3, B4, B5,
                                           C0, C1, C2, C3, C4, C5,
                                           D0, D1, D2, D3, D4, D5,
                                           E0, E1, E2, E3, E4, E5,
                                           F0, F1, F2, F3, F4, F5)

*/
}
{
   // GUARDS

  val names = List(
    "Michael Burgess".split(" "),
    "Michael Holmes".split(" "),
    "Sherlock Holmes".split(" ")
 
  println(
    for {
      parts <- names
      part <- parts
      if parts(1) == "Holmes"
     } yield part
  )

  val myInts = for(
    x <- 1 to 100

    if x % 2 == 0
    if x < 50
  ) yield x

  /*
 names: List[Array[String]] = List(Array(Michael, Burgess),
                                   Array(Michael, Holmes),
                                   Array(Sherlock, Holmes))
scala> List(Michael, Holmes, Sherlock, Holmes)
scala> myInts: IndexedSeq[Int] = Vector(2, 4, 6, 8, 10, 12, 14, 16, 18,
                                        20, 22, 24, 26, 28, 30, 32, 34,
                                        36, 38, 40, 42, 44, 46, 48)

*/
}

{
  // RANGES
  1 to 5 by 2
  1.to(5).by(2)

  for(x <- 1 to 10 by 2) println(x * 2)

  val numbers = 1 to 10 toArray

  println(numbers)

  /*
res17: scala.collection.immutable.Range = Range 1 to 5 by 2
scala> res18: scala.collection.immutable.Range = Range 1 to 5 by 2
scala> 2
6
10
14
18
scala> numbers: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
*/

}
{
  // LET EXPRESSIONS
  val people = List("Michael Burgess", "John Doe", "Jane Doe")

  val doeNames = for(
    person <- people;                         //semi-colon required...
    lastName = person.split(' ')(1);

    if lastName == "Doe"
  ) yield person

  /*
scala> people: List[String] = List(Michael Burgess, John Doe, Jane Doe)
scala> doeNames: List[String] = List(John Doe, Jane Doe)
   */
}



{
  //  LOOPS (vs. comprehensions)
  var population = 0
  while(population < 10) {
    population += 1
  }
  println(population)

  var line = ""
  while(line != "q") {
    line = scala.io.StdIn.readLine("Message? ")

    if(line == "damn") {
      line = "q"                  // there are no break/continue keywords
    } else {
      println(line)
    }
  }

  /*
scala> population: Int = 0
scala> 10
scala> line: String = ""
scala>      | Message?
   */
}

{

  // THE TYPE OF A FOR COMPREHENSION

  val str  = for(c <- "Hello World") yield c
  val chrs = for(c <- "Hello") yield c + ""

  /*
  * the first case defines an immutable ordered sequence of characters (ie. a `String`)
  * the second case defines an immutable ordered sequence of strings (ie. a `Vector` of `String`s)
  */
  
  /*
str: String = Hello World
scala> chrs: IndexedSeq[String] = Vector(H, e, l, l, o)
scala> str: String = Hello World
scala> chrs: IndexedSeq[String] = Vector(H, e, l, l, o)
   */
}