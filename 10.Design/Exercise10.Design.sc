// CHAPTER:   10. DESIGN: ALGEBRA & SPECIAL TYPES
// PROBLEM:    Handle errors using generic types. 
// TIME:       25 m
 

//GENERICS
//Q. define a generic class Person with properties name, age A, height H

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


//Q. define a function getProfile() that calls scala.io.StdIn.readLine
//... expecting an input of sherlock,pa$$
//.. the function should return an Option of Person
//ie., None if the password isnt "pas$$"


//Q. define a function getDetails that calls scala.io.StdIn.readLine
//.. expecting an input of: Sherlock Holmes,27,1.81
//.. the function should return an Option of Person
//ie., None if the details are malformed


//Q. using a for-comprehension and your above functions get a username and password,
//.. also get a fullname, age and height if the password is pa$$


// TRY 
//Q. redefine getProfile, getDetails to getProfileX, getDetailsX
//in each case now throw an IllegalArgumentException exception
// when an error occurs

//Q. define a function getUser which uses Try/pattern-matching
// and the above functions
// to get a user details as before



// EXTRA

// PACKAGE OBJECT + IMPORT 

// REVIEW: What did you learn from this exercise?