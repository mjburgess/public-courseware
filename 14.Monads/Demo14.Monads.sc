// CHAPTER 16: APPLICATIVES, MONADS AND TRANSFORMERS
// PROBLEM:
// PROCESS:   Demonstration & Discussion
// EXP?
// USE?


{
  //APPLICATIVE (vs. MONADIC) STYLE

  //Q. How do you apply a function within some context?

  val one: Option[Int] = Some(1)
  val two: Option[Int] = Some(2)
  val none: Option[Int] = None

  //via Functor, we know:
  println( one map { _ * 2 } )
  println( none map { _ * 2} )

  //and if we want to combine (via Monad):
  println( for(a <- one;
               b <- none) yield a + b )


  //however, also (via Applicative):
  println( (one |@| two |@| one) { _ / _ + _} )
  println( (one |@| none |@| one) { _ / _ + _} )

  // this is "applicative style"
  // |@| combines a-functors into a function
  // which is then called with the right-hand lambda
  // producing the final value 
  
  //notice the right-hand lambda has 3 args here
  //therefore we, in effect, map a 3-arg function
  //over three combined functors

  //you only *need* monads for flattening
  //applicatives are "simpler" in the sense they only generalize map
  //ie., flatMap isnt introduced

  /*
    Some(2)
    None
    None
    Some(1)
    None
 */
}

{
  //COLLECTIONS ARE APPLICATIVES

  //we've seen that Option is an applicative
  //so is list, et al.

  /* implicit */ object ListApplicativeInstance extends Applicative[List] {
    override def point[A](a: => A): List[A] = List(a)

    override def ap[A, B](as: => List[A])(fs: => List[(A) => B]): List[B] = for(
      f <- fs;
      a <- as
    ) yield f(a)
  }

  val people = List("Holmes", "Watson", "Adler")
  val prefix = List("Mr.", "Ms.")

  println( (prefix |@| people) { _ + _ } )


  //NB. implicit commented out above
  //so as not to actually create an instance which replaces scalaz's
  // with the |@| operator

  /*
    List(Mr.Holmes, Mr.Watson, Mr.Adler, Ms.Holmes, Ms.Watson, Ms.Adler)
  */
}

{
  //FUNCTIONS ARE APPLICATIVES 

  val upper = { (s: String) => s.toUpperCase  }
  val bang  = { (s: String) => s + "!" }

  //RECALL: functions are functors, they can be map'd!

  //map'ing a function over a function 
  //is just function composition

  println( (upper map bang)("bye") )
  println( (upper compose bang)("bye" )   
  
  //they are also applicative:

  val exclaim = (upper |@| bang) { _ + "! " + _ }   

  // this is not function composition!

  // (upper |@| bang) runs upper and bang on its on copy of the input 
  // and combines their out put via { _ + _ }

  //you can read the final lambda {   } as describing 
  //how the outputs of upper and bang *will be* combined
  //when they have run

  //we can then define the formula for their combination without running them
  //and run them later

  //and indeed pass this specialized combination around 
}


{
  //PRELUDE: LIFTING
  //we know how to convert values:
  println(1)
  println(1.toDouble)

  //but how do you convert functions?
  val addOne = (i: Int) => i + 1      //here is an Int => Int

  //can we make it Option[Int] => Option[Int] ...
  val optAddOne = (oi: Option[Int]) => oi map { _ + 1 }

  println(optAddOne(Some(5)))
  println(optAddOne(None))

  //the general name for "converting a function" is lifting
  //here is a recipie for lifiting a fn: A => A  to Option[A] => Option[A]
  def liftOpt[A]( fn: A => A) = { 
    (o: Option[A]) => o map fn        //map is just lift! (for one-arg functions)
  }

  println( liftOpt(addOne)(None) )
  println( liftOpt(addOne)(Some(6)) )

  //we can use scalaz to lift:
  //Functor (ie., map) for one-arg functions
  val optAddTwo = Functor[Option].lift { (_: Int) + 2 }
  val listAddTwo = Functor[List].lift { (_: Int) + 2 }

  println(optAddTwo(Some(1)))
  println(listAddTwo(List(1)))

  //Apply (i.e., applicative) for multi-arg:
  val prependOpt = Apply[Option].lift2( (i: Int, s: Seq[Int]) => i +: s )
  val prepend3 = Apply[Option].lift3( (i: Int, y: Int, s: Seq[Int]) => (i + y) +: s )

  println(prependOpt(Some(10), Some(Vector(1, 2, 3))))
  println(prependOpt(None, Some(Vector(1, 2, 3))))
  println(prepend3(Some(10), Some(5), Some(Vector(1, 2, 3))))
}

{
  //PRELUDE: TYPE LAMBDAS

  //RECALL: we can alias types 
  type Age = Int 

  //and higher-kinded ones:
  type FO = Functor[Option]
  type FL = Functor[List]

  //however:
  // type FM = Functor[Map] // ERROR: Map has two type arguments
                            //so cannot be a Functor

  //we can define a specialization of Map which fixes on of its args:
  type FM = Functor[ ({ type M = Map[Int, A] })#M ]

  // ({ type X =  })#X   is an inline defintion of type X 

  //this is mostly used when we want to ignore a type argument slot:
  //(ie., let it, in practice, be anything because we will never access that value)

  def useFunctorOfMap[A[_, _], B](
    f: Functor[  ({ type M[X] = A[B, X] })#M  ]      // ie., Functor[M] , ie., Functor[A[B, ?]]
  )

  //here A could be Map, B could be Int as above   
}

{
  //GENERALIZING APPLICATIVES

  //How do you apply a function requiring an A and returning an M[A], to a value of type M[A] ?
  //aside: if we were to ignore the A requirement, and put in an M[A], we would get an M[M[A]]
  //so using this function is going to require some unwrapping/flattening sort of operation

  trait Monad[F[_]] extends Applicative[F] with Bind[F] { }

  trait Bind[F[_]] extends Apply[F] {
     def bind[A, B](fa: F[A])(f: A => F[B]): F[B]   //notice the flattening, 
                                                    // (fa map f) has type F[F[B]]
  
  }

  /* implicit class BindOps ... { 
     def flatMap[B](f: A => F[B]) = implicitly[F[A]].bind(self)(f)
  }
  */

  println(List("Sherlock Holmes", "John Watson") flatMap { _ split " " })
  println(List("Sherlock Holmes", "John Watson") >>= { _.split(" ").toList })     // operator form
  

  //flatMap the inverse of a flatMap (flatten & wrap):

  println(List("Sherlock", "Watson") >>= { name: String => println(name); List(name) })
  println(List("Sherlock", "Watson") >>= { name: String => println(name); name.point[List] })
}

{
  //FOR-COMPREHENSIONS
  List("Sherlock", "Watson") >>= { name: String =>  
    List("Mr.", "Sir.") >>= { 
      prefix: String => (prefix + name).point[List] 
    }
  }

  List("Sherlock", "Watson") >>= { name: String =>  
    List("Mr.", "Sir.") ∘   { 
      prefix: String => prefix + name 
    }
  }

//∘
  List("Sherlock", "Watson") flatMap { name: String =>  
    List("Mr.", "Sir.") map { 
      prefix: String => prefix + name 
    }
  }

  for(  
      name <- List("Sherlock", "Watson");       //every line is an operation on a List, 
      prefix <- List("Mr.", "Sir.");            //ie., monadic
  ) yield prefix + name 

  //NB., 
  for( 
      name <- List("Sherlock", "Watson");     
      prefix <- List("Mr.", "Sir.");  
                                                // the only exception to the rule above is a let-expr:
      result = prefix + " " + name              // here we introduce a non-monadic term, 
                                                // ie., not in the list context
                                                // this is a local variable in the inner-most function
  ) yield result
}

{
  //THE WRITER MONAD

  // the left side can be any monoid. E.g something which support
  // concatenation and has an empty function: e.g. String, List, Set etc.
  type ErrorResult[T] = Writer[Vector[String], T]   //nb. List() is inefficient here, as +=
 
  def getWeight() : ErrorResult[Double] = {
    val weight = 70 * 2.2             //or some other computation

    weight.set(Vector("calculating a weight"))        //.set creates a writer instance, 
  }                                                 // as does WriterT.writer()
 
  def getExpectedHeight(w: Double) : ErrorResult[Double] = {
    val height = math.sqrt(w/25)

    height.set(Vector(s"calculating weight assuming bmi=25 & w=$w"))
  }
 
  def getSummary(name: String, h: Double) : ErrorResult[String] = {
    val message = s"$name is predicted to be about $h"
 
    message.set(Vector(s"calculating the message (n: $name, h: $h)"))
  }
 
 
 //to get the Vector[String] and Double 
 //we need to get the ErrorResult (ie., the Writer) and run it

 println( getWeight().run )

 //to sequence writers:
  val seqdWriter = for {
    w <- getWeight()
    h <- getExpectedHeight(w)
    m <- getSummary("Watson", h)
  } yield m
 
  //ASIDE: most useful in applicative style?

  println(seqdWriter.run)   //writers need to be run to extract their contents
}


{
  //THE FUNCTION MONAD  ("READER")
  val upper = { (s: String) => s.toUpperCase  }
  val bang  = { (s: String) => s + "!" }

  //RECALL: functions are functors, they can be map'd!
  println( (upper map bang)("bye") )
  println( (upper compose bang)("bye" )   //map'ing a function over a function 
                                          //is just function composition

  //RECALL: functions are applicative, 
  val exclaim = (upper |@| bang) { _ + "! " + _ }   // this is not function composition
                                                    // this defines how 
                                                    // the output of upper and bang 
                                                    // *will be* combined 

  println(exclaim("bye"))                           //..when run


  //NOW: functions are also monads!
  val shout = for {
    m <- upper            // m is the return value of upper *when run*
    s <- bang              // s is the independent return value of bang *when run*
   } yield m + "! " + s     // this is how m and s *will be combined*      

  //shout is a function which runs its arg through upper,
  //then bang then combines them as specified

  println(shout("bye"))

  val scream: Reader[Int, Int] = for { 
    a <- Reader(upper)
    b <- Reader(bang) 
    } yield a + b

}

{
  //MONAD TRANSFORMERS: WHY

  //suppose we have
  type Result[A] = Either[String, Option[A]]

  //a value of this type is then, eg.
  val r: Result[Int] = Right(Some(1))   //or,
  val l: Result[Int] = Left("Error!")           //RECALL:  Either[A, B] = Left[A] | Right[B]

  //to use the inner-most value we would have to unwrap twice:
  val n: Result[Int] = for(opt <- r) yield for(result <- opt) yield result + 1

  //we would like:   for(result <- r) yield result + 1
  //ie., for the comprehension (map/flatMap) to unwrap all the way down 

  //enter monad transfomers:
  //OptionT[M[_]] is a monad transformer which makes a M[Option[A]]

  // type Rezult[A] = OptionT[Either, A]    //ERROR: Either requires two type args!

  type Error[X] = Either[String, X]
  type Rezult[A] = OptionT[Error, A]      //Rezult[A] is a combined monad 
                                          //which unwraps nicely

  //or just,
  type Result[A] = OptionT[({ type e[X] = Either[String, X]})#e, A]
}

{
  //MONAD TRANSFORMERS: USING

  type Error[X] = Either[String, X]
  type Result[A] = OptionT[Error, A]    //ie., OptionT[Either[String, Option[A]], A]
                                        
  //created:  OptionT(Left(String)), OptionT(Right(Some(Int) | None))

  //therefore describes three result states:  error, no value, value 

  //lets make some Results:
  val r1: Result[Int] = OptionT(Right(Some(1)) : Error[Option[Int]]) //ugh!
  val l1: Result[Int] = OptionT(Left("ERROR!") : Error[Option[Int]])

  //we can just use .point to construct:
  val r2: Result[Int] = 1.point[Result]
  val r3: Result[Int] = OptionT(none[Int].point[Error])
  val l2: Result[Int] = OptionT(Left("error!") : Error[Option[Int]])
  
  //and now,
  println(for(result <- r1) yield s"r1 contains $result");
  println(for(result <- l1) yield s"l1 contains $result"); 

  val answer: Either[String, Option[Int]] = r1.run  //use run to remove OptionT wrapper & behaviour
}