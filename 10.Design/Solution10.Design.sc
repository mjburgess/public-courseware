// CHAPTER:   10. ALGEBRA AND SPECIAL TYPES
// OBJECTIVE: Answer the following questions.
// PROBLEM:   Use generic types to handle pin-protected data.
// TIME:      30 m


//GENERICS
//Q. define a generic class Person with properties id: I, age: A, height: H
//.. where I, A, H are type parameters
class Person[I, A, H](val id: I, val age: A, val height: H)

//Q. create a person with an integer id, age and height
//Q. create a person with a string id, double age and height
//.. and print them
val me = Person(10, 27, 181)
val you = Person("Sherlock", 35.1, 1.84)

println(me.id, me.age, me.height)
println(you.id, you.age, you.height)


//Q. define a generic Room with a name which may be any type
// and an location which may be any time
class Room[N, L](val name: N, val location: L) {
  def describe() = println(name, location)
}

// Q. give Room a describe() method which prints its name and location
// Q. create a room with a name "Yellow" and location ("SW", "I St", "Bentonville")
// Q. ask the room to describe itself
val yellow = new Room("Yellow", ("SW", "I St", "Bentonville"))
yellow.describe()



// OPTION
def getRow(index: Int): Option[(String, Int)] = {
  val database = Map(
    1 -> ("michael", 1234),
    2 -> ("tom", 8888)
  )

  database.get(index)
}

//Q. define a function getPassword
// it should accept an pin: Int
// if the pin supplied is 1234 return Some("pa$$")
// otherwise return None
def getPassword(pin: Int) = if(pin == 1234) Some("pa$$") else None

//Q. define a function printDetails
//.. it should accept a String, Int and String and print them
def printDetails(name: String, pin: Int, pass: String) = println(name, pin, pass)

//Q. use a for-comp to get a pin from getRow(1)
// then getPassword() using that pin
// finally printDetails() of the username, pin and password


for((name, pin) <- getRow(1);
    pass <- getPassword(pin)) printDetails(name, pin, pass)


//Q. EXTRA: rewrite using match expressions
val deets = getRow(1)

deets match {
  case Some((u, p)) => getPassword(p) match {
    case Some(pass) => printDetails(u, p, pass)
    case None => ()
  }
  case None => ()
}

// TRY
//Q. write a for-comprehension which gets the username and webpage data
// by calling the following functions

import scala.util._


def getUsername(pin: Int) =
  if(pin == 1234) {
    Success("guest")
  } else {
    Failure(new Exception("Couldn't get user!"))
  }

def getWebPage(url: String, username: Int) =
  if(username == "guest") {
    Success(url)
  } else {
    Failure(new Exception("Couldn't get webpage!"))
  }


//Q. redefine getRow and getPass to getRowX and getPassX
//in each case now throw an exception when an error occurs

//Q. use Try() and pattern-matching with the above functions
// to printDetails() as before



// EXTRA


//Q. using a for-comprehension and your above functions get a username and password,
//.. also get a fullname, age and height if the password is pa$$


// REVIEW: What did you learn from this exercise?