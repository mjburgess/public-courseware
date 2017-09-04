// CHAPTER:   6. COLLECTIONS
// OBJECTIVE: Answer the following questions.
// PROBLEM:    Use collections to analyse information about animals and ports.
// TIME:       25 m


// FOR COMPREHENSIONS REVIEW
//Q. for each author, print their name
val authors = List("Matt", "Mark", "Luke")

//Q. for each pair animal/kind print s"$animal is a $kind"
val animalTypes = Map("Cat" -> "Mammal", "Raven" -> "Bird")

// Q. from animalTypes make a new list of strings called dict
// formatted as "Cat: Mammal"

//Q. print dict

// BUFFERS
//Q. define an ArrayBuffer[String] of s"$animal: $kind"
//ie., build up an ArrayBuffer using += 
import scala.collection.mutable.ArrayBuffer

//Q. print the arraybuffer

// SETS and RANGES
//Q. print all the closed ports
val ports = (1 to 200).toSet
val openPorts = Set(80, 22)


//Q. is port 100 closed?
//HINT: .contains

// ZIPPING
//Q. loop over names and cols at the same time,
// print an element from each
val names = List("Michael", "Mark", "Tim")
val cols = Array("Purple", "Green", "Blue")


//Q. print out all the colours with their index
//HINT: .zipWithIndex


// CONVERTING BETWEEN TYPES OF COLLECTION
//Q. convert letters to a list and print it
val letters = "ABCD"

//Q. convert countries to a set and print it
val countries = List("UK", "UK", "US")


//Q. convert person to a map and print it
val person = List( ("name", "sherlock"),  ("email", "s@example.com") )
println(person.toMap)


// REVIEW: What did you learn from this exercise?