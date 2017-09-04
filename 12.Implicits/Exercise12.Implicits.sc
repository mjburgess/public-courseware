// CHAPTER:   IMPLICITS 
// PROBLEM:   Define instances of typeclasses so dogs and people can speak and move. 
// TIME:       20 m

// below a small speaker library has been defined:
//a trait Speaker  along with a SpeakerHelper that can work with Speakers

trait Speaker {
  def speak(): Unit
}

object SpeakerHelper {
  def talkWith(speakers: List[Speaker]) = speakers foreach { _.speak() }
}

//Q. define a class Person with a name, and a class Dog 
// and create a person called me and a dog called spot
case class Person(name: String)
case class Dog()

val me = Person("Taft")
val spot = Dog()



//Q. define implicit classes so that both me and spot can speak
//all dogs should woof, and people should speak a greeting with their name 


//Q. Use SpeakerHelper.talkWith with List(me, spot)


// EXTRA
trait Mover[M] {
  def move(m: M): Unit
}

object PersonMovers {
  implicit object PersonWalker extends Mover[Person] {
    def move(p: Person) = println(s"${p.name} walks by!")
  }
}

//Q. define a method called moveAround which accepts a List of movers
//... using a context bound
//... and calls move() on each
// HINT: implicitly[Mover[M]]



//Q. use moveAround with List(me, me)
//HINT: import 


//Q. define PersonRunner in PersonMovers which "runs" rather than "walks"
// use moveAround() again so that me runs


// REVIEW: What did you learn from this exercise?