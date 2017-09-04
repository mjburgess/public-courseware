// CHAPTER 10:  DESIGN: IMPORTING, ALGEBRA & SPECIAL TYPES
// PROBLEM:   Use generic types to handle pin-protected data.
// PROCESS:   Demonstration & Discussion.
// EXP?       Polymorphism: ad-hoc & generics (parametric).
// USE?       Tools for describing relationships between objects and their behaviour.


//PART 1 -- TECHNIQUES FOR ORGANIZING CODE


{/* 
  //IMPORTING

  //scalac *.scala && scala Application 
*/}


// PART 2 -- TECHNIQUES FOR STRUCTURING CODE: ALGEBRA

{
  // TUPLES TYPES
  val mytuple = (1, "Hello", false)

  val tperson = ("Michael", 26)
  val cperson = new Person("Michael", 26)

  type WebSite = (String, String, String)

  val eg : WebSite = ("Example Site", "A website used for examples", "http://www.example.co.uk")

  /*
    mytuple: (Int, String, Boolean) = (1,Hello,false)
    tperson: (String, Int) = (Michael,26)
    cperson: Person = Person@3400f5aa
    eg: WebSite = (Example Site,A website used for examples,http://www.example.co.uk)
  */
}


{
  //ALGEBRAIC DATA TYPES
  //REVIEW: WHY PATTERN MATCHING

  class Animal
  case class Dog() extends Animal
  case class Cat() extends Animal

  val what = new Animal
  val fido = new Dog
  val fluffy = new Cat

  // Animal = Dog | Cat | Animal
  def speak(a: Animal) = println(a match {
    case Dog() => "woof"
    case Cat() => "meow"
    case _ => "grunt!"
  })

  speak(fido)
  speak(fluffy)
  speak(what)

  //NO:
  //def speak(a: Animal) =
  //  if(a.isInstanceOf[Dog]) { "Woof!" }
  //  else if (a.isInstanceOf[Cat]) { "Meow!" } else { "..." }

  /*
    woof
    meow
    grunt!
  */
}



{
  // DEFINING AND USING ALGEBRAS

  abstract class Animal
  class Dog extends Animal
  class Cat extends Animal

  //pseudo-code equivalent
  // type Animal = Cat OR Dog

  // thus we may only have
  val animalA : Animal = new Cat
  val animalB : Animal = new Dog

  /*

  */
}



{
  //EXAMPLE: Document Store

  // Document = Video | Image | Text
  abstract class Document(val name: String, val ext: String)
  case class Video(n: String) extends Document(n, ".mp4")
  case class Image(n: String) extends Document(n, ".jpg")
  case class Text(n: String)  extends Document(n, ".txt")
  
  def process(d: Document) = d match {
    case Video(n) => s"I like $n video"
    case Image(n) => s"I like $n img"
    case Text(n) => s"I like $n text"
  }

  println(process(Video("educational youtube short")))

  /*
    I like educational youtube short video
  */
}


// PART 2 -- GENERICS: PARAMETRIC POLYMORPHISM
{
  // TYPE ARGUMENTS
  val drinks = List("Coke", "Pepsi")
  val ages: List[Double] = List(1, 2, 3)

  // DEFINING GENERIC TYPES
  class Person[A](id: A)
  class Add[A, B](val x: A, val y: B)

  val mike = new Person[String]("Michael")
  val dbuser = new Person[Int](1001)

  val a = new Add[Int, Double](1, 2.0)

  println(a.x + a.y)

  /*
    3.0
  */
}

{
  // VARIANCE
  val myList = List(1, 2, 3) ++ List(1.0, 2.0, 3.0) //  List[AnyVal]

  class Animal(val name: String)
  class Cat(n: String) extends Animal(n)
  class Dog(n: String) extends Animal(n)

  class Person[+A](val pet: A) {
    override def toString = s"Person()"
  }

  val catOwners = List(new Person(new Cat("Fluffy")), new Person(new Cat("Sparkles")))
  val dogOwners = List(new Person(new Dog("Spot")),  new Person(new Dog("Fido")))

  val animalOwners = catOwners ++ dogOwners   // List[Person[Animal]]

  println(animalOwners)

  /*
    myList: List[AnyVal] = List(1, 2, 3, 1.0, 2.0, 3.0)
    catOwners: List[Person[Cat]] = List(Person(), Person())
    dogOwners: List[Person[Dog]] = List(Person(), Person())
    animalOwners: List[Person[Animal]] = List(Person(), Person(), Person(), Person())
  */
}




// PART 2 -- SPECIAL TYPES & TECHNIQUES FOR ERROR HANDLING

{
  // WHY OPTION

  def div(x: Int, y: Int): Int = x/y

  // div: (Int, Int) => Int means fa. (Int, Int), ex. Int
  // not true!

  div(10, 0)    //div is a partial function

  /*
  java.lang.ArithmeticException: / by zero
  at .div$1(<pastie>:16)
  ... 37 elided
  */

}

{ // DEFINING OPTION
  abstract class Maybe
  case class Just(val maybe: Int) extends Maybe
  case object Empty extends Maybe

  val maybeAge: Maybe = Just(26)
  val maybeDay: Maybe = Empty

  def division(dd: Int, divr: Int): Maybe =
    if(divr == 0)
      Empty
    else
      Just(dd/divr)

  /*

  */
}


{ //USING OPTION
  def dv(dd: Int, divr: Int): Option[Int] =
    if(divr == 0)
      None
    else
      Some(dd/divr)

  dv(10, 0)
  dv(100, 10)


  val (someX, someY, someZ) = (dv(100, 10), dv(20, 5), dv(1,0))

  val xy  = for {
    x <- someX
    y <- someY
   } yield x + y

  val xyz = for {
    x <- someX
    z <- someZ
    y <- someY
   } yield x + y - z

  println(xy)
  println(xyz)

  /*
    Some(14)
    None
  */
}


{ // EXAMPLE: USER DATA
  def getName(n: String): Option[String] = if(true) {
    Some("Michael")
  } else {
    None
  }

  def getAge(): Option[String] = None

  for(name <- getName("X");
      age <- getAge()
  ) println(name + age)

  /*

  */
}

{
  // BAD: EXCEPTIONS !
  def broken() = throw new Exception("Some Error!")

  import java.io.IOException
  try {
    broken()
  } catch {
    case io: IOException => println("bad io")
    case e:    Exception => println("general error")
  }

  /*
    general error
  */
}



{
  // TRY
  def broken() = throw new Exception("Some Error!")

  import scala.util.{Try, Failure, Success}

  def division(dd: Int, divisor: Int): Try[Int] = {
    Try {
      dd / divisor
    }
  }

  division(10,0)
  division(10,1)

  def wrap() = Try {
    broken()
  }

  wrap() match {
    case Success(v) => println("Successful!")
    case Failure(e) => println("Failure!")
  }

  /*
    Failure!
  */
}


{
  //THE TRY MONAD

  import scala.util.{Failure, Success, Try}

  def getWebPage(url: String, pin: Int) =
    if(pin == 1234) {
      Success(url)
    } else {
      Failure(new Exception("Couldn't get webpage!"))
    }

  def getUsername(name: String, pin: Int) =
    if(pin == 1234) {
      Success(name)
    } else {
      Failure(new Exception("Couldn't get user!"))
    }


  val result = for {
          user <- getUsername("Michael", 134)
          page <- getWebPage("GOOGLE", 124)
        } yield user + page

  println(result)

  /*
    Failure(java.lang.Exception: Couldn't get user!)
  */
}


{
  // EITHER
  // like option short-circuits calculation
  // but also collects an appropriate error

  def getWebPage(url: String, pin: Int): Either[String, String] =
    if(pin == 1234) {
      Right(url)
    } else {
      Left("Couldn't get webpage!")
    }

  def getUsername(name: String, pin: Int): Either[String, String] =
    if(pin == 1234) {
      Right(name)
    } else {
      Left("Couldn't get user!")
    }


  val result = for {
          user <- getUsername("Michael", 134)
          page <- getWebPage("GOOGLE", 124)
        } yield user + page

  println(result)

  // 2.12 -- Either[L, R]
  /*
    Left(Couldn't get user!)
  getWebPage: (url: String, pin: Int)Either[String,String]
  getUsername: (name: String, pin: Int)Either[String,String]
  result: scala.util.Either[String,String] = Left(Couldn't get user!)
  */
}

