// CHAPTER:   3. FLOW
// OBJECTIVE: Complete the following questions.
// PROBLEM:   Use selection expressions to select different greetings.
// PROBLEM:   Use for-comprehensions to analyse a list of people.
// TIME:      2 * 10 m


// PART 1 -- BRANCHING & MATCHING

val time = "AM"

//Q. using one assignment,
//.. define the val greeting to be either "Hello" if time is AM,
//.. or "Goodbye" if time is PM


// Q. add a case to handle "spot"
val dog = "spot"

val message = dog match {
  case "fido" => "Hello Boy!"
  case "fluffy" => "Hello Girl!"
}

println(message)

// Q. rewrite the above if-expression as a match-expression
//... called secondGreeting

//Q. and print it

//Q. print the greeting without defining a variable
// ie., just the match expression



// PART 2 -- FOR COMPREHENSIONS

val mixed = List(1, "2", false, 3.4)

//Q. print out every element of this list

//Q. using a range, print out "Ho!" three times

// Q. print out s"you're allowed in $name" for all the the adults below
// HINT: .split .toInt
// HINT: you can define name using a let-expression
val people = List("Michael 10", "John 20", "Watson 40")

//Q. define ages, a List[Int] of the ages
//HINT: .split .toInt yield


// Q. determine the average age
//HINT: use a var total

println(s"The average is ${total/ages.length}")



//EXTRA

// simple roguelike games print grids as their videogame "frame"
// which the player moves around on...
// complete the following questions to use for-comprehensions to generate such a grid...

// Q. use a for-comprehension to create a sequence of 5 '.'s called row
// HINT: the input must be the same length as the output, in this case

// Q. print out row on one line
//HINT:  use print()

// Q. use a for-comprehension to create a sequence of 5 rows called grid
//HINT: yield a for-comprehension

// Q. print out grid
// with each row on its own line
//HINT: .mkString is easier than for-print


val person = ("Sherlock Holmes", 27)

//Q. assign pName, pAge from person
println(s"$pName is $pAge ")


// REVIEW: What did you learn from this exercise?