// CHAPTER 5:  FUNCTIONS & FUNCTIONAL PROGRAMMING
// PROBLEM:   Define functions to handle login and profile information.
// PROCESS:   Demonstration & Discussion.
// EXP?       Function objects: anonymous functions, closures, lambdas, etc.
// USE?       Higher-level abstractions.

// PART 1 -- FUNCTIONAL PROGRAMMING 



{
    // WHAT IS FUNCTIONAL PROGRAMMING? 

    // imperative program: actions on state
    class Ingredients(var amount: Double) {
        def prep() = amount *= 2
        def mix() =  amount += 2
        def bake() = amount /= 2.0
    }

    val ing = new Ingredients(100)
    ing.prep()
    ing.mix()
    ing.bake()

    println(ing.amount)             // why?!

    /*
    101.0
    */

}


{
  // WHAT IS FUNCTIONAL PROGRAMMING? 

  // functional programming:   all data flow is explicit
  def prep(amount: Double) = amount * 2
  def mix(amount: Double)  = amount + 2
  def bake(amount: Double) = amount / 2.0

  val ing = 100.0
  val prepd = prep(ing)
  val mixd  = mix(prepd)
  val baked = bake(mixd)

  println(baked)

  //or,
  println( bake(mix(prep(100.0))) )

  //nb. the entire application is one expression

  /*
  101.0
  101.0
  */
}

{
  // WHAT IS FUNCTIONAL PROGRAMMING? 

  def prep(amount: Double) = amount * 2.0
  def mix(amount: Double) = amount + 2.0
  def bake(amount: Double) = amount / 2.0

  // like is substitutable for like
  println(bake(mix(200.0)))
  println(bake(202))
  println(101.5)


  // thef. the program is easy to reason about and to parallelize
  val serialA = bake(mix(prep(100)))
  val serialB = bake(mix(prep(100)))


  // cf., Q. can these be parallelized? A. who knows.
  /*
  ing.prep()
  ing.mix()
  ing.bake()
  */

  /*
  101.0
  101.0
  101.5
  */
}


{
  // PURE FUNCTIONS

  // a pure function is a mapping from inputs to outputs
  def last_name(name: String) = name.split(" ").last

  // String to String == any single String to a single String
  // this is equivalent to a(n infinite) set of pairs..

  val last_name = Set( ("Sherlock Holmes", "Holmes"),  ("", "") ) //... for every string
}


{
  // FUNCTIONS
  val square = (x: Int) => x * x

  val printHello = () => println("Hello")

  val add = (x: Int, y: Int) => x + y
  val sub = (x: Int, y: Int) => x - y

  printHello()
  println(square(add(10, 10)))

  /*
  Hello
  400
  */
}

{
  // THE FUNCTION TYPE
  // method syntax
  def m(x: String): Int  = x.length             // String => Int
  // for every string, a single integer is defined

  // function syntax
  val f: String => Int = _.length

}


{
  // 'FUNCTION' vs METHOD
  // scala reserves the name 'function' to mean a function object
  // which can be assigned to a variable, ie., a value --
  // & theref. available run-time

  // scala reserve the name 'method' to mean a function
  // defined lexically, ie., as syntax for the compiler --
  // & theref. not available at run-time

  val function = (name: String) => name.toUpperCase
  def method(name: String) = name.toUpperCase

  println(function("victoria"))
  println(method("victoria"))

  println(function.apply("victoria"))

  val fnFromMethod = method _         // wrap up method into a function

  /*
  VICTORIA
  VICTORIA
  VICTORIA
  */
}

{
  // FUNCTIONS vs METHODS
  val first = (name: String) => name.split(" ")(0)

  def firstName(name: String) = name.split(" ")(0)

  println(firstName("Fido Holmes"))
  println(first("Fluffy Jefferson"))      // called the same way

  println(first)
  // scala reads a variable containing a value

  // ERROR //  println(firstName)
  // scala reads a command to call a method


  // repl // first.

  println(first.apply("Fido Holmes"))
  // first.apply is an apply method of the fido object
  // methods are not objects, they have no methods


  /*
  Fido
  Fluffy
  Fido
  */
}
                   


{
  // HIGHER ORDER FUNCTIONS
  def sendMessage(formatter: String => String) {
    println(formatter("Hello -- How are you?"))
  }

  sendMessage( (m: String) => m.toUpperCase )
  sendMessage( m => m.toUpperCase )
  sendMessage( _.toUpperCase )
  sendMessage({ _.toUpperCase })
  sendMessage { _.toUpperCase }       // standard form

  /*
  HELLO -- HOW ARE YOU?
  HELLO -- HOW ARE YOU?
  HELLO -- HOW ARE YOU?
  HELLO -- HOW ARE YOU?
  HELLO -- HOW ARE YOU?
  */
}

{
  // a more complex example
  val add = (x: Int, y: Int) => x + y
  val sub = (x: Int, y: Int) => x - y

  def reop(op: (Int, Int) => Int, x: Int, y: Int) = op(op(x, y), op(y, x))

  reop(add, 1, 2)()
  reop(sub, 2, 4)()


  /*
    6
    12
  */
}

{
  // FUNCTIONS AS DATA (ie., which can be subject to operations themselves)
  val total = 5 + 5           // data can be combined with operations

  val first = (n: String) => n.split(' ')(0)
  val upper = (n: String) => n.toUpperCase

  val firstUpper = first andThen upper
  val upperFirst = first compose upper

  println(firstUpper("John Watson"))

  //cf.,
  println(first(upper("John Watson")))


  // why? for example, to use with HOFs ..
  def sendMessage(formatter: String => String) {
    println(formatter("Hello -- How are you?"))
  }

  sendMessage(firstUpper)

  /*
  JOHN
  JOHN
  HELLO
  */
}


{
  // HOFs which transform function
  val join = (a: Char, b: Char) => "" + a + b

  val flip = (f: (Char, Char) => String) => (x: Char, y: Char) => f(y, x)
  val rjoin = flip(join)

  join('O', 'K')
  rjoin('O', 'K')
  flip(join)('O', 'K')

  /*
  OK
  KO
  KO
  */
}

// CURRYING
{
  //methods
  def sum(a: Int)(b: Int)(c: Int) = a + b + c
  val addOne = sum(1) _   // _ means "omitting every other argument"
  addOne(2)(3)


  //functions
  val fsum = (x: Int, y: Int) => x + y
  val sumWith = fsum.curried            //since fsum is an object
  val sumOne = sumWith(1)
  sumOne(4)

  /*
  6
  5
  */
}

{
  //WHY CURRYING?

  //syntax (also, implicits)
  def price(original: Double)(discount: Double => Double) = {
    discount(original * 2)
  }

  price(10)( p => p * 0.3 )

  price(10) {
    _ * 0.3
  }

  //partially applying
  def configure(host: String)(user: String)(pass: String) =
    s"Configuring ${host} (u: ${user} p: ${pass})"

  val configureUK = configure("uk-host") _
  val configureFrance = configure("french-host") _

  println(configureUK("user")("pa$$"))
  println(configureFrance("user")("pa$$"))

  /*
    Configuring uk-host (u: user p: pa$$)
    Configuring french-host (u: user p: pa$$)
  */
}

{
  // ASIDE: FORCING CURRYING

  def configure(host: String, user: String, pass: String) =
    s"Configuring ${host} (u: ${user} p: ${pass})"

  val configureWith = (configure _).curried
  val configureUK = configureWith("uk-host")
  val configureFrance = configureWith("french-host")


  //imagine...
  def authService(config: (String, String) => String) {
    println(config("authUser", "authPass"))
  }


  authService(Function.uncurried(configureUK))
  authService(Function.uncurried(configureFrance))

  /*
    Configuring uk-host (u: user p: pa$$)
    Configuring french-host (u: user p: pa$$)
  */
}


{
  // ASIDE: type aliases may be clearer...
  type PairToInt = (Int, Int) => Int
  def reop(op: PairToInt, x: Int, y: Int): Int = {
    op(op(x, y), op(x, y))
  }

  /*

  */
}