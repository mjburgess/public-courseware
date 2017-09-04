package matching

object PersonTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def fn(p: Person) = p match {
  	case Person(n, Address(cty, ctry)) => println(s"$n lives in $cty")
  	case _ => println("???")
  }                                               //> fn: (p: matching.Person)Unit
  
  val p1 = new Person("fred", new Address("London", "UK"))
                                                  //> p1  : matching.Person = Person(fred,Address(London,UK))
  fn(p1)                                          //> fred lives in London
  
}