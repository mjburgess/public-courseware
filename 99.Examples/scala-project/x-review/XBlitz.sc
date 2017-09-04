// CHAPTER:   OBJECT ORIENTATION, INHERITANCE, ALGEBRA
// OBJECTIVE: Answer the following questions.

// BLITZ EXERCISES

// INTRO
//Q. run this in the IDE:
object Application {
  def main(a: Array[String]) = println("Hello World")
}

//FUNDAMENTALS
//Q. print the last part of location
val location = "London, Greater London, United Kingdom"

//Q. if the last part of location is "United Kingdom"
// print "Going to the United Kingdom" other wise
// print "Going somewhere else?"

//METHODS
def jsonPerson(name: String, age: Int) =
  s"""{
     | "name" : "$name",
     | "age"  : $age
     |}""".stripMargin


val sherlockAge = 30
val sherlockName = "Sherlock"

//Q. print the json for sherlock, supply the age first


//FUNCTIONS
def sendGuest(json: String, formatter: String => String) = println(formatter(
  jsonPerson("Guest", 0)
))

//Q. use sendGuest, but uppercase the JSON before it is sent

//COLLECTIONS
val range: IndexedSeq[Int] = 1 to 100 by 20
val buffer: Seq[String] = new scala.collection.mutable.ListBuffer[String]

//Q. loop over range and print out each part

//Q. add some strings to buffer (eg. Fido, Fluffy, Spot)
// then print out each element with its index


// COMBINATORS
val people = List("Sherlock Holmes", "John Watson", "Irene Adler")

//Q. rewrite these with a simplified syntax
people.map( (s: String)    =>  s.split(" ") )
people.exists( (s: String) => s.contains("Sherlock") )


//CLASSES
//Q. add a describe method to Person which prints
// the person's name with their pet's name
class Pet(val name: String)

class Person(val name: String, val myPet: Pet)

val me = new Person("Watson", new Pet("Winston"))

//OBJECTS
//Q. define an object called House with a val owner as above
//.. and a List[Pet] which contains several other pets
//Q. call the owner's describe() and print out each of the other pets


//INHERITANCE
//Q. define class Dog which is a child of Pet
// Dog's constructor will need a parameter to pass to Pet's constructor

//Q. add to Pet a describe() method
//Q. change Person's describe so that it call its pets describe


// ALGEBRA
//Q. define case classes and traits such that:
// Maybe = Just(Int) | None()

val jobs = List((10, 10), (20, 20), (30, 0))
def div(t: Int, b: Int) = if(b == 0) None else Some(t / b)

val results = for((t, b) <- jobs) yield div(t/b)

//Q. print each result