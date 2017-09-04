// CHAPTER 4:  FUNCTIONS
// PROBLEM:    Define functions to handle login and profile information.
// PROCESS:    Demonstration & Discussion.
// EXP?        Defining and using methods ("functions" generally)
// USE?        Tools to describe processes, behaviours and actions.

// PART 1 -- METHODS
{

  // CODE BLOCKS
  //braces group expressions
  val firstName = {
    val parts = "Michael Burgess".split(" ")
    parts(0)                     // this is the "return value"
  }                              // ie., the value of the entire block
  
  val amount = {
    val ratio = 2.5
    val distance = 12

    distance * ratio
  }
  
  /*
firstName: String = Michael
scala> amount: Double = 30.0
   */
}


{

  // METHODS
  def bake(flour: Double, sugar: Double) {
    println(s"Making ${flour + sugar} g of cookies!")
  }


  def mix(flour: Double, sugar: Double): String = {
    val amount = flour + sugar

    return s"Making ${amount} g of cookies!"
  }


  println(mix(30, 40.5))

  /*
scala> bake: (flour: Double, sugar: Double)Unit
scala> mix: (flour: Double, sugar: Double)String
scala> Making 70.5 g of cookies!
   */
}


{

  def blocksum(a: Int, b: Int) = {
    a + b
  }


  def linesum(a: Int, b: Int): Int = a + b
  
  /*
blocksum: (a: Int, b: Int)Int
scala> linesum: (a: Int, b: Int)Int
   */
}


{

  // NOT IMPLEMENTED ("PASS")
  def notDefined(x: Int, y: String, z: Boolean): Double = ???
  // throws NotImplementedError
  
  /*
  scala> notDefined: (x: Int, y: String, z: Boolean)Double

   */
}


{

  // RETURNING UNIT
  def add(a: Int, b: Int): Int = {
    a + b
  }


  def mul(c: Int, d: Int) {
    c * d
  }


  println(mul(10, 10))
  
  /*
  
scala> add: (a: Int, b: Int)Int
scala> mul: (c: Int, d: Int)Unit
scala> ()
   */
}



{

  // DEFAULT ARGUMENTS
  def greeting(message: String = "Good Day!") {
    println("Hello and " + message)
  }


  greeting()
  greeting("Goodbye!")
  
  /*
scala> greeting: (message: String)Unit
scala> Hello and Good Day!
scala> Hello and Goodbye!

   */
}


{

  // ARGUMENT PASSING STYLE
  def appendMark(phrase: String, mark: String = "?") = phrase + mark

  appendMark("what time is it", "!!?")
  appendMark("what time is it")

  def config(host: String, user: String, pass: String) = println(s"$user:$pass@$host")
  config(user="Michael", pass= "Test", host = "UK")
  
  /*
scala> appendMark: (phrase: String, mark: String)String
scala> res10: String = what time is it!!?
scala> res11: String = what time is it?
scala> config: (host: String, user: String, pass: String)Unit
scala> Michael:Test@UK
   */
}




{

  // VARIADICS
  def sum(numbers: Int*) = {
    var counter: Int = 0
    for (n <- numbers) {
      counter += n
    }

    counter
  }


  sum(1,2,3,5,6)
  
  /*
  
scala> sum: (numbers: Int*)Int
scala> res7: Int = 17
   */
}


{

  //ASIDE: PASSING SEQUENCES TO VARIADICS
  def sum(nums: Int*) = nums.sum
  sum(1, 2, 3)

  val ages = List(18, 20, 22)
  sum(ages: _*)
  
  /*
  
scala> sum: (nums: Int*)Int
scala> res8: Int = 6
scala> ages: List[Int] = List(18, 20, 22)
scala> res9: Int = 60

   */
}


{

  // DEF VS VAL
  def helloByDef = "hello"
  val helloByVal = "hello"

  println(helloByDef)

  println(helloByVal)

  def nowByDef = new java.util.Date
  val nowByVal = new java.util.Date

  nowByDef
  nowByVal

  nowByDef
  nowByVal
  
  /*
scala> helloByDef: String
scala> helloByVal: String = hello
scala> hello
scala> hello
scala> nowByDef: java.util.Date
scala> nowByVal: java.util.Date = Tue Apr 18 20:41:14 BST 2017
scala> res15: java.util.Date = Tue Apr 18 20:41:14 BST 2017
scala> res16: java.util.Date = Tue Apr 18 20:41:14 BST 2017
scala> res17: java.util.Date = Tue Apr 18 20:41:15 BST 2017
scala> res18: java.util.Date = Tue Apr 18 20:41:14 BST 2017
   */
}


{

  // LAZY VALS
  val strict = new java.util.Date //11:00:00 am

  strict  //11:00:00 am
  strict  //11:00:00 am

  def deffered = new java.util.Data // ---

  deffered //11:00:00 am
  deffered //11:00:55 am

  lazy val deferOnce = new java.util.Data // ---

  deferOnce //11:00:00 am
  deferOnce //11:00:00 am
}

{
  // LAZY ARGUMENTS
  def fact(n: Int) = (1 to n).product

  def ifS(cond: Boolean, trueVal: Int, falseVal: Int) = if(cond) trueVal else falseVal
  def ifL(cond: Boolean, trueVal: => Int, falseVal: => Int) = if(cond) trueVal else falseVal

  ifS(true, fact(5), fact(100))      // LONG TIME
  ifL(true, fact(5), fact(100))      // SHORT TIME
  /*
scala> fact: (n: Int)Int
scala> ifS: (cond: Boolean, trueVal: Int, falseVal: Int)Int
scala> ifL: (cond: Boolean, trueVal: => Int, falseVal: => Int)Int
   */
}