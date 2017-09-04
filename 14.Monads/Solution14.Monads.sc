
//PART 3 -- APPLICAIVE:
//RECALL: the applicative style is (a1 |@| a2 |@| a3)(fn)

//Q. define aname using nameFormat 
// which is nameFormat(title, name) if there is Some() title, name otherwise None
val nameFormat = (p: String, n: String) => p + n.toUpperCase
val aname = (myTitle |@| myName)(nameFormat)

//Q. define aage as above with the applicative rather than monoidal style 
val aage  = (myAge |@| 1.some) { _ + _ } 

//Q. define adetails which are are all the user's jobs combined with their hobbies 
// ie., List(detecting forensic science, ..., collecting violin playing)
val adetails = (myJobs |@| myHobbies) { _ + " " + _ }

//Q. using for-comprehensions as above, print out these details
for(n <- fname; a <- fage) println(s"Next year $n is $a")
for(d <- fdetails) println(d)


//Q. rewrite the following for-comprehension in terms of map and flatMap
val results = List(
  List(Some("username:sherlock"), None, Some("password:1r3n3")),
  List(Some("username:john"), None, Some("password:w1f3")),
)

val answers = for {
  user <- results
  info <- user
} yield 
    for (detail <- info) yield detail.split(":").last

println(answers)

val withoutFor = results flatMap { _ map { _ map { _.split(":").last }}}

println(withoutFor)
