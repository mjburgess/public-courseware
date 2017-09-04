/*
New from Old:

1. if I have an O and want a N:

val o: Int = 0
val n: String = "0" 

n = f(o)


2. if I have a T[O] and want a T[N]

val os: List[Int] = Nil
val ns: List[String]

os map { o => o.toString }
for(o <- os) yield o.toString


3. if I have a T[O] and want a T[N] but get a T[T[N]]

val os: List[String] = Nil
val ns: List[String] = Nil

os flatMap { o => o.split(" ") map { p => p.toUpperCase } }
for(o <- os; p <- o.split(" ")) yield p.toUpperCase


4. if I have a T[O] but only want some O for T[N]

val os: List[String] = List("Michael", "Matt", "John")

os withFilter { o => o(0) == 'M' } map { o => o.toUpperCase }
for(o <- os if o(0) = 'M') yield o.toUpperCase


5. if I have a T[O] and want T[N] but different ns depending on os

os map {                        // partial function syntax
    case "Michael" => 1
    case _ => 2
}

for(o <- os) yield o match {
    case "Michael" => 1
    case _ => 2
}

6. if I have a T[O] and want an N
os.foldLeft("") { _ + _ }

// or: 
("" /: names)  { _ + _ }


/*
Syntax:

: N         An N
: N, N      An N and N
: O, N      An O and N
: (O, N)    A tuple of O and N
: T[O]      T of O
: T[O, N]   T of O and N
: O => N    O to N
*/

// EXAMPLES:

    //label     //type                      //object
val n         : Int                     =   0
val point     : (Int, Int)              =   (0, 0)
val points    : List[Int]               =   List(1, 2, 3)
val locations : Map[String, (Int, Int)] =   Map("x" -> point, "y" -> point)
val distance  : (Int => Double)         =   { _ * 2.0 }

/*
Rules
: N = A | B | C     an N must be made from either A or B or C
: O = O(A, A)       an O must be made from an O with A and A

Examples:

   Document = Text | Video | Audio
   Text = Text(String, String)
 */


trait Document
class Text(n: String, e: String) extends Document
class Video extends Document
class Audio extends Document
