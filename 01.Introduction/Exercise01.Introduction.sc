// CHAPTER:   1. INTRODUCTION                    
// OBJECTIVE: Complete the following questions.
// PROBLEM:   Use the compiler, interpreter and repl to run scala code.
// TIME:      20 m 

// INTRODUCTION TO EXERCISES
// for every exercise there should be an exercise file available
// most questions are solved by writing code in the file
// and then running the file
// solutions are provided, you can have a look at these if you get stuck
// however its best to try on your own and ask the instructor for advice

// before you start an exercise,
// save a copy to your home folder to keep track of your solutions


// PART 1 -- RUNNING SCALA
// create a file called HelloWorld.scala
// it should contain the following...
object HelloWorld {
  def main(args: Array[String]) {
    println("Hello World!")
  }
}

//Q. run the file via: scala, scalac & scala, ide

//STEPS for IDE:
// place a main.scala file in the src folder and chose Run > Run

//Q. modify your main method, define a variable called message
// set message to "Good Day!"
// print the message

// Q. create a new *worksheet* in the IDE (hello.sc)
// copy and paste the contents of HelloWorld.scala into hello.sc
// in addition, add at the end of the file:  HelloWorld.main(Array())
// and run the file (the green triangle)

//Q. why was the last line required?

// PART 2 -- TRY THE REPL
//Q. use the REPL to find out what methods today has
//HINT: TAB
val today = new java.util.Date

//Q. try the following in the REPL
val message = "Hello"
var greeting = "Hello"

greeting += " World"
message  += " World"

//Q. Why does the second operation fail?
//Q. change the definition of message so the above operation will work

// EXTRA
// To turn off automatic running of worksheets in Intellij choose:
// Preferences > Language & Tools > Scala > Worksheets > interactive option

// REPL -- Mac, Linux users only!
//Q. what does CTRL+L, CTRL+A, CTRL+E, CTRL+U and CTRL+K do?
//Q. press CTRL+R, type val, press the right arrow key

// REVIEW: What did you learn from this exercise?
