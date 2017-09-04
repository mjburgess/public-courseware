// CHAPTER 15: HIGHER KINDED TYPES
// PROBLEM:
// PROCESS:   Demonstration & Discussion
// EXP?
// USE?

import scalaz._
import Scalaz._
{
  //REVIEW: POLYMORPHISM

  //How may an identifier specialize its meaning?
  //A. by type 

  //Three important ways to achive type-dependent behavior:

  //1. parametric polymorphism:   def f[A]()
  //2. sub-type polymorphism:     C extends P 
  //3. ad-hoc polymorphism:       implicit object
}

{
  //REVIEW: POLYMORPHISM      1. parametric
  
  //the first method is parametrically polymorphic 
  //its behavior depends on A
  def first[A](group: Seq[A]): A = group(0)

  println(first(List(1, 2, 3)))
  println(first(Vector("Michael", "London", "UK")))
    
  //parametric polymorphism is limited:
  //type A is unconstrained
  //so no operations which concern A can be used

  /*
    1
    Michael
   */
}

{
  //REVIEW: POLYMORPHISM      2. subtyping

  //another form of polymorphism is subtyping
  trait Appendable[T] {
    def append(other: T): T
  }

  //the specific behavior of append() depends on
  //class implementing Appendable

  def ap2[A <: Appendable[A]](left: A, right: A) = 
    left.append(right).append(right)

  //here we define a general function ap2
  //which appends any Appendable to another
  //twice

  //and now a class which implements Appendable
  //an account with a balance

  class Account(val balance: Double) extends Appendable[Account] {
    def append(other: Account) = new Account(balance + other.balance)
  }

  //finally we can use our ap2 
  println(ap2(new Account(100), new Account(200)).balance)

  //limitation: A constrained by inheritance, must know parent up-front
  //eg., cannot use append() with Int, String, etc.

  /*
    500.0
   */
}

{

  //REVIEW: POLYMORPHISM      3. ad-hoc

  //we define a trait as before
  //to describe the specializable behavior 
  trait Addable[A] {
    def add(left: A, right: A): A
  }

  //and a class which to implement this behavior
  class Account(val balance: Double) {
    def mergeAccounts(other: Account) = 
      new Account(balance + other.balance)
  }

  //however:
  //the class does not implement it directly
  /**/


  //an object implements the trait instead
  //and we use this object with our Accounts
  implicit object AccountAddable extends Addable[Account] {
    def add(left: Account, right: Account) = 
      left.mergeAccounts(right)
  }

  //implicit allows us to access it anywhere 
  //eg., in this method:

  def myAdd[A : Addable](left: A, right: A) = 
    implicitly[Addable[A]].add(left, right)


  //[A : Addable] requires an Addable[A] object
  //implicitly[Addable[A]] is that object

  //myAdd is polymorphic it specializes 
  //by finding an object of a parameterized type
  /**/

  //Why?
  //consider another Addable[A] object:

  implicit object IntAddable extends Addable[Int] {
    def add(left: Int, right: Int) = left + right
  }

  //now myAdd is specialized for Ints and Accounts: 
  println(myAdd(100, 300))
  println(myAdd(new Account(100), new Account(200)).balance)

  //this is ad-hoc polymorphism 
  //it is the most powerful kind:

  //subtyping requires a preknown inheritance tree
  //whereas implicit objects are defined whenever


  /*
  400
  300.0
  */
}

{
  //REVIEW: TYPECLASSESES -- MONOID
  
  //a monoid is a type with a zero and append

  trait Monoid[A] {
    def append(a1: A, a2: A): A
    val zero: A
  }


  //so we can abstract over all monoids

  def aggregate[A : Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.zero)(m.append)
  }


  //ie., write behavior which polymorphically
  //selects the right .zero and .append

  //two instances of Monoid for Int and String

  implicit val IntMulMonoid: Monoid[Int] = 
    new Monoid[Int] {
      def append(a: Int, b: Int) = a * b
      val zero: Int = 1
    }

  implicit val StringCatMonoid: Monoid[String] = 
    new Monoid[String] {
      def append(a: String, b: String) = a + b
      val zero: String = ""
    }


  //immediately we can use our aggregate method

  println(aggregate(List(2, 2, 2, 2)))
  println(aggregate(List("2", "2", "2", "2")))


  //ASIDE: scalaz provides append() as |+|

  val one: Option[Int] = Some(1)
  val two: Option[Int] = Some(2)

  println( one |+| None )
  println( two |+| one  )

  /*  
  16
  Some(1)
  Some(3)
  */
}

{
  //RECIPE FOR A TYPECLASS

  //the recipe:

  // 1. a trait which defines the typeclass, 
  // 2. helper objects, methods and operators
  // 3. instances of the typeclass

}

{
  //RECIPE FOR A TYPECLASS

  //suppose we have:
  
  case class Worker(val salary: Double) { def raise = Worker(salary * 1.05) }

  //and we want to be able to treat numbers as workers
  // ie., roughly:  println(10100.raise)

  //so something CanBeWorkerID (typeclass)
  //if there is a function to call which makes one:

  trait CanBeWorker[A] {
    def makeWorker(a: A): Worker
  }

  //helper to define instances of the typeclass
  // scalaz likes to provide similar methods:

  object CanBeWorker {
    def defineInstance[A](f: A => Worker) = new CanBeWorker[A] {
      def makeWorker(a: A): Worker = f(a)
    }
  }

  //instances of CanBeWorker
  // could define these with implicit objects instead
  // just need an object around which : CanBeWorker

  implicit val cbwIntInstance = CanBeWorker.defineInstance[Int](
    (i: Int) => Worker(i.toDouble)
  )

  implicit val cbwStringInstance = CanBeWorker.defineInstance[String](
    (s: String) => Worker(s.toDouble)
  )

  //utility operations for use with types that CanBeWorker

  implicit class CanBeWorkerOperations[A : CanBeWorker](nonworker: A) {
    def worker = implicitly[CanBeWorker[A]].makeWorker(nonworker)
    def printRaise = println(worker.raise)
  }


  // Int : CanBeWorker
  // so we can use the helper operations:

  10.printRaise

  //and so for string

  println("10100.50".worker.raise)

  /*
    Worker(10.5)
    Worker(10605.525)
  */
}

{
  //SCALAZ
  //OVERALL STRUCTURE:

  // 1. New Classes & Type Classes (eg. Validation)
  // 2. Extensions to standard (eg. ListOps)
  // 3. General utility functions, generalized with
  //      ad-hoc poly-morphism, traits + objects + implicits

  //EG.
}

{/*
  //SBT FOR SCALAZ

  //scalaz can be added to your sbt dependencies
  //as usual:

  scalaVersion := "2.12.0"
  val scalazVersion = "7.2.14"
  libraryDependencies ++= Seq(
    libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.14"
  )

  //the sbt console then has access to scalaz:

  initialCommands in console := "import scalaz._;  import Scalaz._; "
*/}

{
  //SIMPLE TYPECLASS: EQUAL & ORDER

  //scalaz typeclasses will, broadly,
  //introduce new methods and operators

  //Equal introduces === and =/=

  println("hello" === "hello")
  println(1 == "hello")           // false
  // println(1 === "hello")       // ERROR: fails to compile!

  println("Hello" =/= "hello")
  println(1 != "hello")           // true
  // println(1 =/= "hello")       // ERROR: fails to compile!

  //Order introduces ?|?, min, max, ...

  println(5 > 10.0)
  println(5 gt 10)
  // println(5 gt 10.0) // ERROR: fails to compile!
                        // type mismatch

  println(3.5 min 2.3)
  println(3.5 ?|? 2.3)    //LT, GT, EQ

  //implementing is straightforward
  //here a Equal[Worker] instance is defined:
  
  case class Worker(val salary: Double)

  implicit val WorkerEqual : Equal[Worker] = 
    Equal.equal(_.salary == _.salary)

  //and now we can use =/= on our type:

  println(Worker(100) =/= Worker(300))

  /*
    true
    false
    true
    true
    false
    false
    2.3
    GT
    true
  */
}


{/*
  //ASIDE: KINDS

  scala> :k Int
  scala.Int's kind is A

  scala> :k Option
  scala.Option's kind is F[+A]

  scala> :k Some
  scala.Some's kind is F[+A]

  scala> :k None
  scala.None's kind is A

  scala> :k Either
  scala.util.Either's kind is F[+A1,+A2]

  scala> :k Functor
  scalaz.Functor's kind is X[F[A]]

  scala> :k Applicative
  scalaz.Applicative's kind is X[F[A]]

  scala> :k Monad
  scalaz.Monad's kind is X[F[A]]

  //X is about a type constructor F which is about A
*/}


{
  //HIGHER-KINDED TYPES: FUNCTOR

  //a Functor is any container type which defines a map
  //ie.,

  trait Functor[F[_]] {
    def map[A, B](container: F[A])(f: A => B): F[B]
  }

  //eg.
  implicit object ListFunctor extends Functor[List] {
    def map[A, B](container: List[A])(f: A => B): List[B] = 
      container.map(f)
  }

  //and so:

  ListFunctor.map(List(2, 3, 4))({ _ + 2})

  //this doesnt allow us to do much yet... 
  //with operator(s)

  implicit class FunctorOps[A, F[A] : Functor](container: F[A]) {
    //eg.,
    def mm(f: A => A): F[A] = implicitly[Functor[F]].map(container)(f andThen f)

    //but map is the essential
    def map[B](f: A => B): F[B] = implicitly[Functor[F]].map(container)(f)
    //forward the operator to the tc instance's
  }

  //now we can use mm with any Functor

  println(List(1, 2, 3).mm { (_: Int) + 2 })

  //and write generic functions over Functors, eg.

  def mo[A, B, F[A] : Functor](container: F[A])(f: A => B) = 
    Some(implicitly[Functor[F]].map(container)(f))

  println(mo(List(3, 4, 5)) { _ * 3 })

  //ASIDE: MAPPING OVER FUNCTIONS WITH FUNCTIONS
  //scalaz defines a Functor[F[_]] instance for functions!
  //ie., treating a function as a kind of container which can be mapped over
  //cf. set view of function, f = {(0, 0), (0, 1) ...}
  //in this case, map = compose


  /*
  List(5, 6, 7)
  Some(List(9, 12, 15))
  */
}

{
  //ASIDE: GENERALIZING FUNCTOR

  //let's generalize the functor

  //a functor is a container 
  //which can map a function over its contents

  //what about a function of multiple arguments?
  //...where would the other argument come from?

  // List("hello", "bye") map { _.toUpper + _ }   // ERROR

  //first step: 
  //from the original eg. List (any Functor)
  //create a List of functions:

  val listOfFns = List("hello", "bye") map {
    (m: String, suffix: String) => m + suffix
  }.curried
  
  //so that,
  
  println( listOfFns(0)("ERROR") )

  //or,
  //map calling the fns with ERROR

  println(listOfFns map { _("ERROR") })          
  
  //in one go:

  val suffixd = List("hello", "bye").map({ 
        (m: String, suffix: String) => m.toUpperCase + suffix
    }.curried).map { 
        f => f(" -- ERROR!") 
    }

  //here we map the two-arg function over first
  //currying the first parameter with the list element
  //producing a list of one-arg functions

  //*then* map a function which calls each 
  //with the remaining argumnet 
  //and so, in effect, map a 2-arg function

  println(suffixd)

  /*
  helloERROR
  List(helloERROR, byeERROR)
  List(HELLO -- ERROR!, BYE -- ERROR!)
  */
}

{
  //HIGHER-KINDED TYPES: APPLY, APPLICATIVE

  //Applicative Functor 
  //provides functionality to maps n-arg functions
  //more easily

  //scalaz defines:

  trait Apply[F[_]] extends Functor[F] {
    def ap[A, B](container: F[A])(containerOfFn: F[A => B]): F[B]
  }

  trait Applicative[F[_]] extends Apply[F] {
    def point[A](a: => A): F[A]
  }

  //via Apply's ap, we have the <*> operator
  //which takes a container and a wrapped function, 
  //and applies that wrapped function to the contents

  val one: Option[Int] = Some(1)
  val two: Option[Int] = Some(2)
  val none: Option[Int] = None

  val fnUnary :  Int       => Int  =      i => i + 2
  val fnBinary: (Int, Int) => Int  = (l, r) => l + r

  println( one <*> Some(fnUnary) )

  //notice that fnB has two args:
  one <*> (two <*> Some(fnBinary.curried)) 

  //via Applicative's point, we also have constructor operators
  println( 1.some <*> fnUnary.some )

  //Applicative provides a way to wrap values in Functors
  //ie., point
  //and a way to call wrapped *functions*
  //ie., <*>

  /*
    Some(3)
    Some(12)
    Some(3)
  */
}
