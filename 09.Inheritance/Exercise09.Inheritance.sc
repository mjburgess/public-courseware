// CHAPTER:   INHERITANCE
// OBJECTIVE: Answer the following questions.
// PROBLEM:   Use inheritance to define relationships between kinds of people.
// TIME:      15 * 2 m

// PART 1
// Q. create an abstract class Person with a val fullname
//... and an abstract method def getID: String

//Q. create the classs Worker with a worker-id 
// whose getID returns their worker-id
//.. and a FamilyMember with a role
// whose getID returns their role (eg. Father)

//HINT: 
// worker's constructor will accept two parameters
// passing the first to Person's constructor 


//Q. create a List of Person with Workers and FamilyMembers
//loop through the list and print out all their IDs



// PART 2
// Q. define a trait Talker that has a speak method that println's "Hello"

// Q. define a class Dog which extends from this trait
// and overrides speak to println "woof"

// Q. define a class Human (with no parents or body)

// Q. create a List of Talkers which contains Human and Dog objects
//HINT:  new ... with ...

// Q. define a function say() which takes a List of Talkers
//.. it should iterate through this list and ask each to speak


// EXTRA
// Q. create an abtract class Animal(var weight: Double)
// which has an eat() and a walk() method:
// the walk method accepts a effort: Int,
// the eat method accepts a amount: Int
// the eat method increases the weight of the animal
// the walk method is undefined/abstract


// Q. create a class Human which extends Animal(100)
// it walks weight * effort feet
// -- eg., prints "walking...", distance


// Q. create a class Bird which extends Animal(10)
// it walks weight * effort * 0.1 feet

//Q. create a bird, and a human
// have them eat something
// have them walk with the same effort


// REVIEW: What did you learn from this exercise?