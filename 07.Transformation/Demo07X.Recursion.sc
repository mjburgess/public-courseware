

// PART 3 -- RECURSION


{ //RECURSION

  //why recursion?
  val people = List("Michael", "Sherlock", "Fluffy")

  var str = ""
  for(p <- people) str += people

  //or,
  str = "" + people(0) + people(1) + people(2)

  //or,
  // str = f(Nil) + f(people(0)) + f(people(1)) + f(people(2))

  //or, -- what's going to keep the counter? there's no i += 1
  // str = f(f(f(f(people))))

  // what must f do?
  // peel away list into working-on-bit and pass on rest of it
}


{
  // RECURSION IDIOMS
  def iloop(): Unit =  iloop()

  // terminating recursion
  def loop(condition: Boolean): Int = condition match {
    case true => loop(condition)        //continue
    case false => 0                     //stop
  }

  val locations = List("UK", "US", "CA")

  def join(strs: List[String], sep: String = " "): String = strs match {
    case head :: tail => head + sep + join(tail)
    case Nil => ""                         // lists are Nil-terminated
  }
}


{
  // INTERNAL ITERATION VS RECURSION

  val basket = List(("Lemonade", 4.50), ("Cherries", 3.45), ("Bread", 2.33))
  val locations = List("UK", "US", "CA")


  def factorial(n: Int) = (1 to n).product

  val joined = locations.mkString " "
  val total  = basket map { _._2 } reduce { _ + _ }

  println(joined)
  println(total)

  //explicit recursion is rarely a need, in practice:
  //1. internal iterators do 90% of the work
  //2. methods should provide functional APIs: not necessarily implementation

  def fac(n: Int): Int = {
    var i = n
    var acc = 1

    while (i > 0) {
      acc *= i
      i   -= 1
    }

    acc
  }
}




{
  //ASIDE: tailcall-recursive

  def factorial(n: Int): Int = if (n == 0) 1 else n * fact(n-1)

  val f, g, h = (x: Int) => x     // for the following example

  // tail-calls
  def fn(x: Int): Int =
    if(f(x) > 0)
      g(x)
    else
      h(x) + 1


  def fac(n: Int): Int = {
    def go(i: Int, acc: Int): Int =
      if (i == 0)
        acc
      else
        go(i - 1, acc * i)

    go(n, 1)
  }


  @scala.annotation.tailrec
  def fact(a: Int, prd: Int = 1): Int = a match {
    case 0 => prd
    case _ => fact(a - 1, prd * a)
  }
}

{
  val basket = List( ("Lemonade", 4.50), ("Cherries", 3.45), ("Bread", 2.33) )
  def average(cart: List[(String, Double)]): Double = cart match {
    case head :: tail => head._2/cart.length + average(tail)
    case Nil => 0
  }
}