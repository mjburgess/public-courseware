package concurrency

import scala.actors.Actor
import scala.actors.Actor._

class Actor1 extends Actor {
	def act() {
		while (true) {
			receive {
				case msg => println("got: " + msg)
			}
		}
	}
}

object actors {
  println("Actors")                               //> Actors
  
  val ac = new Actor1                             //> ac  : concurrency.Actor1 = concurrency.Actor1@2e1972bf
  ac.start                                        //> res0: scala.actors.Actor = concurrency.Actor1@2e1972bf
  
  ac ! "Hello"
  
  val x = 3                                       //> x  : Int = 3
  
	val countActor = actor {
  	loop {
    	react {
      	case "how many?" => {
        	println("I've got " + mailboxSize.toString + " messages in my mailbox.")
      	}
    	}
  	}
	}                                         //> countActor  : scala.actors.Actor = scala.actors.Actor$$anon$1@13adc735\
 countActor ! 1
 countActor ! 2
 countActor ! "how many?"
 
}