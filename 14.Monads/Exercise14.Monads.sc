// CHAPTER:   APPLICATIVES & MONADS
// OBJECTIVE: Answer the following questions.
// PROBLEM:   Use applicative and monadic style to process some user data.
// TIME:      20 m

//Q. define aname using nameFormat 
// which is nameFormat(title, name) if there is Some() title, name otherwise None
val nameFormat = (p: String, n: String) => p + n.toUpperCase

//Q. define aage as above with the applicative rather than monoidal style 

//Q. define adetails which are are all the user's jobs combined with their hobbies 
// ie., List(detecting forensic science, ..., collecting violin playing)

//Q. using for-comprehensions as above, print out these details

//Q. rewrite the following for-comprehension in terms of map and flatMap
val results = List(
  List(Some("username:sherlock"), None, Some("password:1r3n3")),
  List(Some("username:john"), None, Some("password:w1f3")),
)

val answers = for {
  user <- results
  info <- user
} yield 
    for (detail <- info) 
      yield detail.split(":").last

println(answers)

