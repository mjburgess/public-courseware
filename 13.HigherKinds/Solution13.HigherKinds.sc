// CHAPTER:   15. HIGHER KINDED TYPES
// OBJECTIVE: Answer the following questions.
// PROBLEM:   Print out a user's profile information: their name, age and other details. 
// TIME:      3 * 10 m


//a user has filled in some of their profile information:
val myName: Option[String] = "Sherlock".some
val myAge: Option[Int] = 28.some
val myTitle: Option[String] = None

val myHobbies: List[String] = List("forensic science", "violin playing")
val myJobs: List[String] = List("detecting", "collecting")
val myFavPeople: List[String] = Nil



//PART 1 -- MONOID
//Q. define mname: Option[String], mage: Option[Int] and mdetails: List[String]   using |+|

// mname    is the users title followed by name if both have Some() otherwise handle Nones appropriately
// mage     is the users age +1 if it has Some(), otherwise handle None appropriately
// mdetails is the list containing all hobbies, jobs and favourite people

val mname = myTitle |+| myName
val mage  = myAge |+| 1.some 
val mdetails = myHobbies |+| myJobs |+| myFavPeople

//Q. using a for-comprehension print s"Next year $n is $a" where $n is the name, and $a the age
for(n <- mname; a <- mage) println(s"Next year $n is $a")

//Q. using a for-comprhension print all the details
for(d <- mdetails) println(d)


//Below is one way of implementing the Monid type class for Bools.
//Q. revise this defintion 
//   so that a person is allowed in only when both isOpen and isAdult

implicit object BoolMonid extends Monoid[Boolean] {
  val zero = false
  override def append(left: Boolean, right: => Boolean): Boolean = left || right 
} 

val isAdult = age.getOrElse(0) >= 18
val isOpen = false 

if(isAdult |+| isOpen) {
  println("Go in!")
} else {
  println("Stay out!")
}


//PART 2 -- FUNCTOR
//Q. redefine fname, fage and fdetails as above
//Q. make the whole name uppercase 
//Q. define age without using |+| 
//Q. make all the details lowercase

//HINT: map

val fname = (myTitle |+| myName) map { _.toUpperCase }
val fage  = myAge map { _ + 1 }

val fdetails = (myHobbies |+| myJobs |+| myFavPeople) map { _.toLowerCase }


//Q. using for-comprehensions as above, print out the user's information

for(n <- fname; a <- fage) println(s"Next year $n is $a")
for(d <- fdetails) println(d)



//EXTRA:
//Q. define a Functor instance for Ticket
//Q. using toUsd, convert the tickets to US dollars and print out each price

trait Ticket[A] {
  val price: A
}

case class SingleTicket[A](val price: A) extends Ticket[A] 
case class GroupTicket[A](val price: A, val groupSize: Int) extends Ticket[A]

val a = SingleTicket(10)
val b = GroupTicket(20, 2)
val c = SingleTicket(12)


val toUsd = (p: Int) => s"${p * 1.3} USD"
val tickets = List(SingleTicket(10), SingleTicket(11), GroupTicket(40, 4))

implicit object TicketFunctor extends Functor[Ticket] {
  def map[A, B](fa: Ticket[A])(f: A => B): Ticket[B] = fa match {
    case SingleTicket(price) => SingleTicket(f(price))
    case GroupTicket(price, size) => GroupTicket(f(price), size)
  }
}


for(t <- tickets) println(t)
