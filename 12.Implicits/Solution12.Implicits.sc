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

implicit class DogSpeaker(d: Dog) extends Speaker {
    def speak() = println("Woof!")
}

implicit class PersonSpeaker(p: Person) extends Speaker {
    def speak() = println(s"Hi, I'm ${p.name}!")
}

//Q. Use SpeakerHelper.talkWith with List(me, spot)
SpeakerHelper.talkWith(List(me, spot))


// EXTRA
trait Mover[M] {
  def move(m: M): Unit
}

object PersonMovers {
  implicit object PersonWalker extends Mover[Person] {
    def move(p: Person) = println(s"${p.name} walks by!")
  }

  implicit object PersonRunner extends Mover[Person] {
    def move(p: Person) = println(s"${p.name} runs by!")
  }
}

//Q. define a method called moveAround which accepts a List of movers
//... using a context bound
//... and calls move() on each

def moveAround[M : Mover](ms: List[M]) = for(m <- ms) implicitly[Mover[M]].move(m)

//Q. use moveAround with List(me, me)
//HINT: import PersonWalker

import PersonMovers.PersonWalker
moveAround(List(me, me))

//Q. define PersonRunner in PersonMovers which "runs" rather than "walks"
// use moveAround() again so that me runs

//import PersonMovers.PersonRunner 
//moveAround(List(me, me))



// REVIEW: What did you learn from this exercise?