

//APPENDIX:

{
  // DEPRECATION
  readLine("Prompt? ")
  // $ scala -deprecation

  scala.io.StdIn.readLine("Prompt? ")
}

{

  //-- functional programs are a single expression
  println(
    List("Sherlock Holmes", "John Watson") flatMap {
      _ split(" ")
    } map {
      _.toUpperCase
    })

  //which has the form:
  /*
    OUTPUT_ACTION( F(G(H(INPUT))) )
  */

  //where F, G, H are pure calculations

}


{
  // ASIDE: REFLECTION
  import scala.reflect.runtime.universe._

  val intType = typeOf[Int]
  intType.baseClasses map {
    _.fullName
  }

  // strings have a complex hierachy
  typeOf[java.lang.String].baseClasses map {
    _.fullName
  }

  /*
  scala> intType: reflect.runtime.universe.Type = Int

  scala> res56: List[String] = List(
    scala.Int, scala.AnyVal, scala.Any
  )

  scala> res57: List[String] = List(
    java.lang.String, java.lang.CharSequence,
    java.lang.Comparable, java.io.Serializable,
    java.lang.Object, scala.Any
  )
   */
}

{
  //ASIDE: ALIASING

  type Age = Int
  type Name = String

  def person(n: Name, a: Age) = {
    println(s"Name: $n, Age: $a")
  }

  person("Sherlock", 30)

  /*
  scala> defined type alias Age
  scala> defined type alias Name
  scala> person: (n: Name, a: Age)Unit
  scala> Name: Sherlock, Age: 30
   */
}

{
  // SYMBOLS
  val option = 'Off

  val choiceA = 'Off
  val choiceB = 'On

  option == choiceA
  option == choiceB
  
  /*
scala> option: Symbol = 'Off
scala> choiceA: Symbol = 'Off
scala> choiceB: Symbol = 'On
scala> res15: Boolean = true
scala> res16: Boolean = false

   */
}


{

  // REGEX
  """\w+""".r

  raw"\w+".r

  val numbers = """\d+""".r
  val service = "ftps              990/tcp   #FTP control, over TLS/SSL"

  numbers.findFirstIn(service)

  val validate = """[^@]+@[^.]+(\.\w+)+""".r

  validate.findFirstIn("invalid_email")
  
  /*
  
scala> res18: scala.util.matching.Regex = \w+
scala> res19: scala.util.matching.Regex = \w+
scala> numbers: scala.util.matching.Regex = \d+
scala> service: String = ftps              990/tcp   #FTP control, over TLS/SSL
scala> res20: Option[String] = Some(990)
scala> validate: scala.util.matching.Regex = [^@]+@[^.]+(\.\w+)+
scala> res21: Option[String] = None
   */
}


{ //preview: destructuring assignment (pattern matching)
  val point = (10, 20)
  val (x, y) = point

  val locations = List("UK", "FR")
  val List(a, b) = locations


  /* NB.
  scala> val Map(x -> y) = me
  <console>:12: error: value Map is not a case class,
  nor does it have an unapply/unapplySeq member
       val Map(x -> y) = me
           ^
   */

}


{
  // arrays
  val locations = Array("UK", "US", "France")

  println(locations(0))
  println(locations.last)
  /*
scala> locations: Array[String] = Array(UK, US, France)
scala> UK
scala> France
   */
}
