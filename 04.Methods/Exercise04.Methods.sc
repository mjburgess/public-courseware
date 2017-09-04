// CHAPTER:   4. METHODS
// OBJECTIVE: Answer the following questions.
// PROBLEM:   Define methods to handle login and profile information.
// TIME:      20 m


//CODE BLOCKS
//Q. modify the definition of name so it refers
//... to the last name of Sherlock Holmes

val name = {
  val parts = "Sherlock Holmes".split(" ")
  parts(0)
}

println(name)


// METHOD DEFINITION
// Q. define a method called parts
// which takes a csv-formatted string and returns its parts
//HINT: csv formatted means separated with a comma
val exampleCSV = "jefferson, pa$$, virginia, president"

//Q. call parts with exampleCSV and print out each part


//KEYWORD ARGUMENTS
//Q. define a method getUser with arguments username and password
// the method should return a Map("username" -> username, "password" -> password)
// password should have a default value of "pa$$"


//Q.  call getUser supplying the password argument first ("test")
//... then the username ("guest")
//... and print out the user's details

//Q. now just print getUser with the username "guest"



//VARIADICS
//Q. define printProfile,
// a function of an arbitrary number of arguments
// which prints all of them

printProfile("Irene", "Paris", "45")


//EXTRA
//Q. can you add a return type to the getUser method?

//Q. dbl the ages list & print
def dbl(nums: Int*) = {
  for(n <- nums) yield n * 2
}

val ages = List(18, 20, 22)
