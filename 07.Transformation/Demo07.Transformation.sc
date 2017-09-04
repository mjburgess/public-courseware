// CHAPTER 7:  TRANSFORMING DATA: MATCHING, COMBINATORS & LAZYNESS
// PROBLEM:    Use collections to analyse information about animals and ports.
// PROCESS:    Demonstrations & Discussion
// EXP?        Map/Reduce/Filter
// USE?        Data transformations.


//PART 1 -- LAZY DATA

{
  // STREAMS

  // streams are (immutable) lazy lists
  // values calculated on demand + cached
  // stream = current value + next operation = cons cell + next
  // streams can be infinite

  import Stream._

  val numbers = cons(0, cons(1, Stream.empty))
  val zeroOne = 0 #:: 1 #:: empty

  def squares(i: Int): Stream[Int] = (i * i) #:: squares(i + 1)

  val s = squares(1) take 3
  val monotonic = Stream.from(3)
  val repeated = Stream.continually(1)
  val range = Stream.range(10,20)


  numbers foreach { println _ }
  squares(1) take 3 foreach (println _)
    
  
  /*
  0
  1
  1
  4
  9
*/
}




// PART 2 -- COMBINATORS (VS. RECURSION, PATTERN MATCHING)


{
  // .MAP
  val names  = List("Michael", "Sherlock", "Watson")
  val prepMs = (str: String) => "Ms. " + str

  println(names.map(prepMs))

  def prepMr(str: String) = "Mr. " + str

  println(names.map(prepMr))

  names.map( (e: String) => "Mr. " + e )
  names map( (e: String) => "Mr. " + e )
  names map( e => "Mr. " + e )
  names map(  "Mr. " + _ )
  names map {  "Mr. " + _ }

  for( e <- names ) yield "Mr. " + e

  val ages = List(18, 19, 20, 21)
  ages map { _ > 18 }

  /*
    List(Ms. Michael, Ms. Sherlock, Ms. Watson)
    List(Mr. Michael, Mr. Sherlock, Mr. Watson)

    names: List[String] = List(Michael, Sherlock, Watson)
    ages: List[Int] = List(18, 19, 20, 21)
    res2: List[Boolean] = List(false, true, true, true)
  */

}

{
  // FLAT MAP
  val names = List("Fluffy Jefferson", "Fido Holmes", "Spot Sinatra")
  names map { _.split(" ") }

  //peeling away the type
  names flatMap { _.split(" ") }
  names flatMap { _.split(" ") } map { _.toUpperCase }

  /*
    names: List[String] = List(Fluffy Jefferson, Fido Holmes, Spot Sinatra)
    res0: List[String] = List(FLUFFY, JEFFERSON, FIDO, HOLMES, SPOT, SINATRA)
  */
}

{
  // FOLDING & REDUCING
  var total = 0
  val ages = List(10, 20, 30)

  for(a <- ages) total += a

  ages.foldLeft(0)({ _ + _ })

  ages.foldLeft(0) { _ + _ }

  println(ages.foldLeft(true) { _ && _ > 18})

  println( ages reduce { _ + _ } )

  println( ages map { _ > 18 } )
  println( ages map { _ > 18 } reduce { _ && _ } )

  /*
    total: Int = 60
    ages: List[Int] = List(10, 20, 30)
    allAdults: Boolean = false
    res3: Boolean = false
  */
}



{
  // PREDICATES
  val ages = List(10, 20, 30)
  val isAdult = (age: Int) => age > 18


  // EXISTS AND FORALL
  println(ages forall { _ > 18 })
  println(ages exists { _ < 18 })

  println(ages forall isAdult)
  println(ages exists isAdult)

  /*
    false
    true
    false
    true
  */

}


{
  //OPTION
  val sname: Option[String] = Some("Michael")
  val nname: Option[String] = None

  println(sname map { "Mr." + _ })
  println(nname map { "Mr." + _ })
  
  def loc(name: String): Option[String] = Map(
      "Michael" -> "London",
      "Andy"    -> "Cardiff"
    ).get(name)


  val names = List("Michael", "Andy")
  val locations = (names map loc)

  println((names map loc ) map { 
    opt => opt map { _.toUpperCase } 
  })

  /*
    Some(Mr.Michael)
    None
    List(Some(LONDON), Some(CARDIFF))
    sname: Option[String] = Some(Michael)
    nname: Option[String] = None
    loc: (name: String)Option[String]
    names: List[String] = List(Michael, Andy)
    locations: List[Option[String]] = List(Some(London), Some(Cardiff))
  */
}




// PART 2 -- FOR COMPREHENSIONS

{
  // foreach
  for(pet <- List("Fluffy", "Spot")) println(pet)

  List("Fluffy", "Spot") foreach { pet => println(pet) }


  // yield and map
  for(id <- List(1, 2, 3)) yield id + 1000

  List(1, 2, 3) map { id => id + 1000 }

  /*
    Fluffy
    Spot
    Fluffy
    Spot
    res6: List[Int] = List(1001, 1002, 1003)
  */
}

{
  // flatMap
  val ids  = List(1, 2, 3)
  val pets = List("Fluffy", "Spot")

  for {
    id <- ids
    pet <- pets
   } yield s"${id + 1000}: $pet"

  val petids = ids.flatMap { id =>
    pets.map { pet => s"${id + 1000}: $pet" }
  }

  println(petids)


  /*
    List(1001: Fluffy, 1001: Spot, 1002: Fluffy, 1002: Spot, 1003: Fluffy, 1003: Spot)
  */
}

// PART 3 -- PATTERN MATCHING

{
  // REVIEW: EXTRACTING ASSIGNMENT
  val (y, (_, z)) = ("File System", ("NTFS", 1024))

  case class OperatingSystem(val name: (String, String), val addressSize: Int)

  val OperatingSystem((publisher, title), _) =
    OperatingSystem(("Microsoft", "Windows 10"), 64)

  /*
    y: String = File System
    z: Int = 1024
    publisher: String = Microsoft
    title: String = Windows 10
  */
}

{
  // REVIEW: CASE
  def day(name: String) = name slice (0, 2) match {
    case "Mo" => 1
    case "Tu" => 2
    case "We" => 3
    case "Th" => 4
    case "Fr" => 5
    case "Sa" => 6
    case "Su" => 7
    case _ => 0
  }

  println(day("Monday"))

  /*
      1
  */
}

{
  // REVIEW: PATTERN EQUALITY & EXTRACTION
  val os = (("Microsoft", "Windows 10"), 32)

  val isMicrosoft = os match {
    case (("Microsoft", _), _) => true
    case _ => false
  }

  val whichMicrosoft = os match {
    case (("Microsoft", os), bit) => s"MS $os - $bit bit"
    case _ => "?"
  }

  /*
    os: ((String, String), Int) = ((Microsoft,Windows 10),32)
    isMicrosoft: Boolean = true
    whichMicrosoft: String = MS Windows 10 - 32 bit
  */
}


{
  //CASTING

  val objs = List(1, false, "Hello")

  for(o <- objs) println(o match {
    case i : Int => s"An int: $i!"
    case b : Boolean => s"A bool: $b!"
    case s : String => s"A string: $s"
  })

  /*
    An int: 1!
    A bool: false!
    A string: Hello
  */
}

{
  // SEQUENCES
  val names = List("michael", "sherlock", "watson")

  println(
    names match {
      case head :: tail => s"Mr. $head"
      case Nil => "GUEST"
    }
  ) 

  def breakUp[T](seq: Seq[T]): String = seq match {
    case head +: tail => s"$head +: " + breakUp(tail)
    case Nil => "END"
  }

  println(breakUp(Map("type"-> "human", "height" -> 2, "weight" -> 100).toSeq))

  println(breakUp(IndexedSeq("Michael", "John", "Burgess")))

  /*
    Mr. michael
    (type,human) +: (height,2) +: (weight,100) +: END
    Michael +: John +: Burgess +: END
  */
}

{
  // FOR-YIELD-MATCH
  val tupleList = List(("A", "B", "C"), (1, 2, 3), (false, 1, "B"))

  val eachStartsWith = for (triple <- tupleList)
    yield triple match {
      case ("A", _, _) =>  "Starts with A"
      case (1, _, _)   =>  "Starts with 1"
      case (_, _, _)   =>  "Unknown!"
    }

  /*

  tupleList: List[(Any, Any, Any)] = List((A,B,C), (1,2,3), (false,1,B))
  eachStartsWith: List[String] = List(Starts with A, Starts with 1, Unknown!)

  */
}

{
  // REGEX
  val people = List("Richard Dawkins", "Richard Feynman",
                    "Lewis Carol", "David Lewis", "Carol Richards")

  val Richards = raw"Richard (\w+)".r
  val Lewises = raw"(?:\w+ )?Lewis(?: \w+)?".r

  println(
    for (person <- people) yield person match {
      case Richards(name) => "a scientist named: " + name
      case Lewises() => "a logician"
      case _ => "something else"
    }
  )

  /*
    List(a scientist named: Dawkins, a scientist named: Feynman, a logician, a logician, something else)
  */
}

{
  // GUARDS
  type Person = (String, Int)

  val people: Seq[Person] = Seq(("Michael", 26), ("Sarah", 15), ("John", 18))

  for(person <- people) println(
    person match {
      case (name, age) if age >= 18 => s"$name is eligible for full-time work "
      case (name, _) => s"$name is not eligible for full-time work"
    }
  )

  /*
    Michael is eligible for full-time work 
    Sarah is not eligible for full-time work
    John is eligible for full-time work 
  */
}


{
  // OPTION

  def getName(): Option[String] = Some("Michael")

  val mrName = getName() match {
    case Some(n) => s"Mr. $n"
    case None => "GUEST"
  }

  println(mrName)

  //better to use for-comprehensions

  /*
    Mr. Michael
  */
}


{
  //FILTER

}

{
  //PARTIAL FUNCTIONS
}