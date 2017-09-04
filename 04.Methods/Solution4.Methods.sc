// CHAPTER:   4. METHODS
// OBJECTIVE: Answer the following questions.
// PROBLEM:   Define functions to handle login and profile information.
// TIME:      20 m


//CODE BLOCKS
//Q. modify the defintion of name so it refers
//... to the last name of Sherlock Holmes

val name = {
  val parts = "Sherlock Holmes".split(" ")
  parts(1)
}

println(name)


// METHOD DEFINTION
// Q. define a method called parts
// which takes a csv-formatted string and returns its parts
val exampleCSV = "jefferson, pa$$, virginia, president"

def parts(csv: String) = csv.split(", ")

//Q. call parts with exampleCSV and print out each part
for(part <- parts(exampleCSV)) println(part)


//KEYWORD ARGUMENTS
//Q. define a method getUser with arguments username and password
// the method should return a Map("username" -> username, "password" -> password)
// password should have a default value of "pa$$"

def getUser(username: String, password: String = "pa$$") = Map(
  "username" -> username,
  "password" -> password
)

//Q.  call getUser supplying the password argument first ("test")
//... then the username ("guest")
//... and print out the user's details
println(getUser(password="test", username="guest"))

//Q. now just print getUser with the username "guest"
println(getUser("guest"))



//VARIADICS
//Q. define printProfile,
// a function of an abitary number of arguments
// which prints all of them

def printProfile(info: String*) = for(i <- info) println(i)
printProfile("Irene", "Paris", "45")


//EXTRA
//Q. can you add a return type to the getUser method?
//def getUser(u: String, p: String): Map[String, String]

//Q. dbl the ages list & print
def dbl(nums: Int*) = {
  for(n <- nums) yield n * 2
}

val ages = List(18, 20, 22)

println(dbl(ages: _*))
