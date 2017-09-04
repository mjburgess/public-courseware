// CHAPTER:   5. FUNCTIONS
// OBJECTIVE: Answer the following questions.
// PROBLEM:   Define functions to handle login and profile information.
// TIME:      25 m

// DEFINING FUNCTIONS


// the method formatMessages accepts a list of messages
// and makes a list of messages by running a formatter function
// on them and adding "MSG: "
def formatMessages(ms: List[String], f: String => String) =
  for(m <- ms) yield "MSG: " + f(m)

//Q. define a function for use with formatMessages called fmt
// which uppercases its input
// HINT: this function should have one argument

val fmt: String => String = (s: String) => s.toUpperCase

//Q. what is the type of fmt?
//... add it to the val fmt

//Q. define a val messages which is a formatted list of messages
// given by msgs
//HINT: use formatMessages with fmt
val msgs = List("Hi!", "bye!", "Sigh?")


val messages = formatMessages(msgs, fmt)








// ACCEPTING FUNCTIONS
val join = (l: String, r: String) => s"! $l: $r"

//Q. define a method called printError which accepts a level: String, message: String
// and a third parameter, formatter which is a function with the same type as join above
// the printError method should use formatter to format the level and message then print it
def printError(level: String, message: String, formatter: (String, String) => String) = {
  println(formatter(level, message))
}




// CURRYING
// Q. redefine formatMessages as fmtMessages
// with its last argument curried
//HINT: copy and paste the defintion, change the name
//HINT: there should be parentheses around the function argument
def fmtMessages(ms: List[String])(f: String => String) =
  for(m <- ms) yield "MSG: " + f(m)

// Q. call fmtMessages with msgs, defining the lowercasing function in-line,
// ie. without using the fmt variable
fmtMessages(msgs)((s: String) => s.toUpperCase)

//Q. one pair of parentheses () can substituted for a pair of braces {}
// call fmtMessages again with braces around the function argument
fmtMessages(msgs) { (s: String) => s.toUpperCase }

//Q. when passing a function as an argument the type of the function
// can be inferred from the method parameter
// call fmtMessages again without any type hint
fmtMessages(msgs) { s => s.toUpperCase }

//Q. finally, can you use an underscore to define the function?
fmtMessages(msgs) { _.toUpperCase }




// EXTRA

// the method calcFt that accepts a list of Double
// and makes a list of Double by multiplying them by 3.28 them and
// applying a f: Double => Double   on them
def calcFt(xs: List[Double], f: Double => Double) = for(i <- xs) yield f(i * 3.28)

//Q. define a function for use with calcFt which squares its input
val square = (x: Double) => x * x

//Q. define a val areas which is the area of each of three fields bound
// by the following fences  & print it
//HINT: use calcFt with square
val fences = List(3.0, 4.0, 5.0)
val areas = calcFt(fences, square)

println(areas)

//Q. rewrite square so all the type of the container is explicit
val fsquare: (Double) => Double = (x: Double) => x * x

// CURRYING
// Q. redefine calcFt as calcFeet so that its last argument is curried
//HINT: there should be parentheses around the function argument
def calcFeet(xs: List[Double])(f: Double => Double) = for(i <- xs) yield f(i * 3.28)

// Q. call calcFeet with fences, defining the square function in-line,
// ie. without using a variable
println( calcFeet(fences) ( (x: Double) => x * x) )

//Q. one pair of parentheses () can substitued for a pair of braces {}
// call calcFeet again with braces around the function argument
println( calcFeet(fences) { (x: Double) => x * x } )

//Q. when passing a function as an argument the type of the function
//can be inferred from the method parameter
// call calcFeet again without any type hint
println( calcFeet(fences) { x => x * x } )

//Q. define a function to use with login() below
// the username/password pair "guest"/"pa$$" should succeed
// HINT: .reverse
val cmp = (l: String, r: String) => l == r.reverse

def login(username: String, password: String, compare: (String, String) => Boolean) =
  if(compare(password, "$$ap")) {
    println(s"${username} allowed!")
  } else {
    println(s"${username} denied!")
  }

login("guest", "pa$$", cmp)

// Q. call login defining a function in-line
// HINT: use _
login("guest", "pa$$", _ == _.reverse)

//Q. define clogin a curried version of login
def clogin(u: String, p: String)(cmp: (String, String) => Boolean) = login(u, p, cmp)

//Q. call clogin using _ to define the function in-line and use braces to group
clogin("guest", "pa$$") { _ == _.reverse }



//Q. define a curried method called retry which takes a numTimes as the first argument
// and function (() => Boolean) as a second argument
//it should print "retrying" and call the function numTimes or until it returns true
// HINT: for-comprehension from 1 to numTimes
// HINT: use the return keyword explicitly, return unit ()
def retry(numTimes: Int)(f: () => Boolean): Unit = for(i <- 1 to numTimes) {
  if(f()) return () else println("retrying")
}

//Q. call retry, pass a function which returns false if a random number is <= 10
// HINT: scala.util.Random.nextInt(100)
retry(10) { () => scala.util.Random.nextInt(100) <= 10 }

// REVIEW: What did you learn from this exercise?