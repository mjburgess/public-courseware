// CHAPTER:   9. INHERITANCE
// OBJECTIVE: Answer the following questions.
// PROBLEM:   Use inheritance to define relationships between kinds of people.
// TIME:      15 * 2 m

// PART 1
// Q. create an abstract class Person with a val fullname
//... and an abstract method def getID: String
abstract class Person(val fullname: String) {
  def getID: String
}

//Q. create the classs Worker with a worker-id number whose getID returns their id number
//.. and a FamilyMember with a role whose getID returns their role (eg. Father)
//HINT: worker's constructor will accept a name and a ni, passing the name to Person

class Worker(fn: String, val ni: String) extends Person(fn) {
  def getID = ni
}

class FamilyMember(fn: String, val role: String) extends Person(fn) {
  def getID = role
}

//Q. create a List of Person with Workers and FamilyMembers
//loop through the list and print out all their IDs
val people = List(
  new Worker("Michael", "JH 00 00 00 B"),
  new FamilyMember("Michael", "Son")
)

for(person <- people) {
  println(person.getID)
}



// PART 2
// Q. define a trait Talker that has a speak method that println's "Hello"
trait Talker {
  def speak = println("Hello")
}

// Q. define a class Dog which extends from this trait and overrides speak to println
//.. "woof"
class Dog extends Talker {
  override def speak: Unit = println("Woof!")
}

// Q. define a class Human (with no parents or body)
class Human

// Q. create a List of Talkers which contains Human and Dog objects
//HINT:  new ... with ...
val talkers: List[Talker] = List(new Human with Talker, new Dog)

// Q. define a function say() which takes a List of Talkers
//.. it should iterate through this list and ask each to speak
def say(ts: List[Talker]) = for(t <- ts) t.speak

//OR,
def say(ts: List[Talker]) = ts foreach { _.speak }




// EXTRA

// Q. create an abtract class Animal(var weight: Double)
// which has an eat() and a walk() method:
// the walk method accepts a effort: Int,
// the eat method accepts a amount: Int
// the eat method increases the weight of the animal
// the walk method is undefined/abstract

abstract class Animal(var weight: Double) {
  def eat(amount: Int) = weight += amount

  def walk(effort: Int): Unit
}

// Q. create a class Human which extends Animal(100)
// it walks weight * effort feet -- eg., prints "walking...", distance
class Human extends Animal(100) {
  def walk(effort: Int) = println(s"Walking ${weight * effort} ft")
}

// Q. create a class Bird which extends Animal(10)
// it walks weight * effort * 0.1 feet
class Bird extends Animal(10) {
  def walk(effort: Int) = println(s"Walking ${weight * effort * 0.1} ft")
}

//Q. create a bird, and a human
// have them eat something
// have them walk with the same effort

var b = new Bird
var h = new Human

b.walk(10)
h.walk(10)

// REVIEW: What did you learn from this exercise?