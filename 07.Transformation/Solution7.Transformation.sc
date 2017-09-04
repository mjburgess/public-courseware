// CHAPTER:   7. COMBINATORS
// OBJECTIVE: Answer the following questions.
// PROBLEM:   Use combinators to analyse some names and ages.
// TIME:      20 m

// COMBINATORS & PREDICATES
val names = List("Sherlock Holmes", "Michael John Burgess", "Thomas Jefferson", "Alex Hamilton")
val ages = List(30, 40, 50)

//Q. use foreach to print every name
names foreach { println _ }

//Q. with names, print using map:
//... obtain a list of the length of each name
//... obtain a list of bools, whether the name .isEmpty
//... obtain a list of the last names
println(names map { _.length }))
println(names map { _.isEmpty })
println(names map { _.split(" ").last })

//Q. is the last name of all of the people, >7 chars long?
//Q. does any one in your list of names have a middle name?
//HINT: forall exists
//HINT: .split .last .length

println( names forall { _.split(" ").last.length > 7 } )
println( names exists { _.split(" ").length > 2 } )


// PATTERN MATCHING
val baskets = List(
  List("Shirt", "Dress", "Socks")
  List("Bread", "Cherries", "Ham", "Cheese"),
  List("Bread", "Milk", "Lemon"),
)

/* 
  Q. from baskets above, define inStock so that:

  val inStock = List(
    List("Sliced Bread", "Cherries", "Ham", "Cheese"),
    List("Sliced Bread", "Milk", "Lemon"),
  )

  ie., keep only lists which start with bread,
        but include "Sliced Bread" instead

  Use a for-yield-match.

  HINT: 
    Matching each element on h :: t, what will h be?
    What is "Sliced Bread" :: t ?
*/


val inStock = for(cart <- baskets) yield cart match {
  case head :: tail if head == "Bread" => "Sliced Bread" :: tail
}


//EXTRA

//Q. using reduce, get the average age
val total = ages reduce { _ + _ }
println(s"The average is ${total/ages.length}")

// Q. get a list of birth years from the list of ages
// HINT: map
val years = ages map { 2017 - _ }

//Q. use foldLeft to join all the names/ages in sequence (ie., Sherlock Holmes 30, Thomas ...)
names.zip(ages).foldLeft("") {  (a, e) => a + " " + e._1 + e._2 }

//println(na.foldLeft("")( (acc, kv) => acc + kv._1 + kv._2))

// Q. use a for comprehension to get the same results as the above questions


// RECURSION
// the hasEmpty method determines if any element of a list of strings
// is empty
def hasEmpty(data: List[String]): Boolean = data match {
  case head :: tail => head.isEmpty || hasEmpty(tail)
  case Nil => false
}

println(hasEmpty(List("", "Sherlock", "London")))
println(hasEmpty(List("30", "Sherlock", "London")))


//Q. define a recursive function which produces
// a comma-separated list of the first names of this list of people
val people = List("Sherlock Holmes", "Thomas Jefferson", "Elizabeth II")

def join(people: List[String]): String = people match {
  case head :: tail => head + ", " + join(tail)
  case Nil => ""
}

join(people)

//EXTRA

// Q. revise the definition of hasEmpty to create a new method any
// any should accept two parameters, data and a function (Boolean, String) => Boolean
// it should combine head with tail using this function, rather than with ||
def any(data: List[String], f: (Boolean, String) => Boolean): Boolean = data match {
  case head :: tail => f(any(tail, f), head)
  case Nil => false
}

//Q. use any to determine if a list of strings is empty

val data = List("", "Hello")

println(any(data, { _ || _.isEmpty }))

// REVIEW: What did you learn from this exercise?