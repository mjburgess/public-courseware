// CHAPTER 14:  CONCURRENCY
// PROBLEM: 
// PROCESS:
// EXP?
// USE? 

// Part 1 -- FUTURES

// futures -- async computation
// future == object holding (delayed) result 
/*
The asynchronous operation is performed by an execution context, which is normally provided as an implicit value. 
*/
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

import scala.util.{Try,Success,Failure}         // remember to import!

def getComplexData(): Int { Thread.sleep(2000); 1 }

val futureResult = Future { getComplexData() } (implicit execution_context)


// EXECUTION CONTEXT 
//contexts execute tasks (with, eg. thread pool)
//default global ExecutionContext is usually sufficient


// SERIALIZING (GETTING THE RESULT)
//how do you get the result of the operation?

// Await.result -- will wait for a period and return you the result if successful, throwing an exception if not
// Await.ready  -- waits until the future completes

val lst = Await.result(futureResult, 1 seconds)
val result = Await.ready(futureResult, Duration.Inf).value.get

//however waiting for futures defeats the point (ie., blocks)
//better to provide a callback to be executed when the future returns


// CALLBACKS 
//future produces: Try[T] = Success[T] | Failure 
//callback hooks: onComplete is always called, onSuccess success, onFailure failure
// do not need to extract Try[T] with onS, onF 


import scala.util.{Success, Failure}

futureResult onComplete {
  case Success(stuff) => for(s <- stuff) println(s)
  case Failure(ex) => println(ex.getMessage)
}

futureResult onSuccess {
  case stuff => for(s <- stuff) println(s)
}

futureResult onFailure {
  case ex => println(ex.getMessage)
}


// prefer using foreach which treats future as a container 

// Handle the success case
futureResult foreach {
  case stuff => for(s <- stuff) println(s)
}

// Handle the failure case
futureResult.failed foreach {
  case ex => println(ex.getMessage)
}


// COMPOSING FUTURES
val f1 = Future {
    Thread.sleep(500)
    List("Sherlock Holmes", "Thomas Jefferson") 
} 

val f2 = Future {
    Thread.sleep(500)
    List("Sherlock Holmes", "Thomas Jefferson") 
} 


f1 onSuccess {
    case resf1 => f2 onSuccess {
        case resf2 => println(resf1 ++ resf2)
    }
}       ///ugly 



// map function in Try[T] context, ie., inside a Success
val f1 = Future {  Thread.sleep(1000); }

val f2 = f1 map {
  data => Thread.sleep(1000)
}

f2 onSuccess {
  case s => println("Both f1 and f2 worked")
}



// if future returns a future, ie., Future[Future[String]] use flatMap

//  onComplete and onSuccess, can execute in any order
// 'andThen' can be used to enforce ordering

// andThen vs map:  andThen returns a new future with same result
// map executes only if the future completes successfully (similarly to Option)

Future {
 Thread.sleep(500)
  List("Sherlock Holmes", "Thomas Jefferson")
} andThen {
  case Success(d) => println(d map { _.toUpperCase })
} andThen {
  case Success(d) => println(d)                             // original data
}

Future { 
    Thread.sleep(500)
    List("Sherlock Holmes", "Thomas Jefferson") 
} map { 
    _ map { _.toUpperCase } 
} map { 
    println(_)                                             // modified data
}  


// ERRORS  !

/*

We can use onFailed or Future.failed to handle failure cases

These will not be called for all exceptions
We can use a callback to handle failures, but these will not be called for all exceptions. Certain exceptions are "fatal errors", and these will be passed directly to the ExecutionContext. Fatal errors include Java Error types (such as LinkageError and VirtualMachineError), Scala's ControlThrowable, and InterruptedException.

All non-fatal exceptions inherit from the NonFatal type, although it is seldom necessary to use this explicitly.

onFailure is only called after the future has completed
What if we want to return another result on failure?

Use the recover combinator

The recover combinator creates a new future that holds the result of the original future if it completed successfully. If it didn't, the partial function is applied to the Throwable, and the result returned if it matches. If it doesn't match, the original Throwable is returned.

*/

val f1 = Future { throw new InterruptedException }
val f2 = Future { throw new IllegalArgumentException }

f1.failed foreach { case ex => ... }
f2.failed foreach { case ex => ... }

val f1 = Future { 
    List(1, 2, 3)
}

val f2 = f1 map {
  data => throw new InterruptedException
} recover {
  case InterruptedException => 1
  case _ => 0
}


// FUNCTIONAL FUTURES 
// futures are monads (map, flatMap, ..)

//therefore...

val f1 = Future { returnAnInt() }
val f2 = Future { returnAnotherInt() }
    
val result = for {
  x <- f1
  y <- f2
} yield x+y




// PART 2 -- ACTORS -- AKKA

//https://alvinalexander.com/scala/simple-scala-akka-actor-examples-hello-world-actors
