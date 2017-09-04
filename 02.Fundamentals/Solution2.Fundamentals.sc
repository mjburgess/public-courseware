// CHAPTER:   2. FUNDAMENTALS
// OBJECTIVE: Complete the following questions.
// PROBLEM:   Using var and val, describe yourself.
// PROBLEM:   Using boolean operators, describe a bar.
// PROBLEM:   Parse complex data types to describe your hobbies.
// TIME:      10 * 3 m


//PART 1 -- VARIABLES & OBJECTS
//Q. define a val, name, which is your name
val name = "Michael Burgess"

//Q. define a var, age, which is your age
var age = 27.0

//Q. define a val, isBirthday,
//... a bool which is whether its your bithday or not
//HINT: true false 
val isBirthday = false

//Q.  if today's your birthday increase your age by 1,
//... otherwise increase it by 1.0/365
if(isBirthday) {
  age += 1
} else {
  age += 1.0/365
}

//Q. print your age
println(age)


// PART 2 -- SIMPLE TYPES
val barDistance = 100
val barCrowd = 50
val barBeer = 5

//Q. define isBusy, isFar, isCheap 
//  the bar is busy if there's more than 25 people
//  the bar is close if it is less than 500m away
//  the bar is cheap if beer is less than 3.50

val isBusy = barCrowd > 25
val isClose = barDistance < 500
val isCheap = barBeer < 3.50


//Q. if the bar is far and not busy and cheap print "GOING TO BAR"
//Q. otherwise print "NOT GOING TO BAR"

if(!isClose && !isBusy && isCheap) {
  println("GOING TO BAR")
} else {
  println("NOT GOING TO BAR")
}

//Q. if the bar is busy and near OR cheap, print "It'll do"
//Q. otherwise print "Nope, won't do!"

if((isBusy && isClose) || isCheap) {    // Q. where do the parens go?
  println("It'll do!")
} else {
  println("Nope, won't do!")
}


// PART 3 -- COMPLEX TYPES: TUPLES, STRINGS & ARRAYS
val person = ("Sherlock Holmes", 27)

//Q. assign pName, pAge from person
val (pName, pAge) = person
println(s"$pName is $pAge ")


//Q. using data, define categories
// it should be a comma-separated string which contains each category of hobby 
// ie., 'photography-portraiture, programming-functional'
// HINT: split :  split ;   mkString(",")

val data = "hobbies:photography-portraiture;programming-functional;drinking-beer"
val categories = data.split(":")(1).split(";").mkString(",")

println(s"My Name is $name" )
println(s"My Age is $age")
println("My Hobbies are " + categories)


//EXTRA

//Q. if myBirthday is birthday, print "Happy Birthday!"
// HINT: toInt split ._1 ._2 ._3
val birthday = (12, 12, 1912)
val myBirthday = "12-12-1912"
val parts = myBirthday.split("-")

if(
  (birthday._1 == parts(0).toInt) &&
  (birthday._2 == parts(1).toInt) &&
  (birthday._3 == parts(2).toInt)
) {
  println("Happy Birthday!")
}

// REVIEW: What did you learn from this exercise?