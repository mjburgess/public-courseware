package labs

object repo {
  println("Option lab")                           //> Option lab
  
  class Person(val id: Int, val name: String, val age: Int, val gender: Option[String])
  
  object Employees {
  	private val users: Map[Int, Person] = Map(1 -> new Person(1, "fred", 45, Some("M")),
  	                        2 -> new Person(2, "", 33, None))
  	def findById(id: Int): Option[Person] = users.get(id)
  	def findAll = users.values
  }
  
  val e1 = Employees.findById(1)                  //> e1  : Option[labs.repo.Person] = Some(labs.repo$$anonfun$main$1$Person$1@58c
                                                  //| d7fdb)
  if (e1.isDefined)
  	println(e1.get.name)
  else
  	println("No such ID")                     //> fred

  
  val e2 = Employees.findById(11)                 //> e2  : Option[labs.repo.Person] = None
  if (e2.isDefined)
  	println(e2.get.name)
  else
  	println("No such ID")                     //> No such ID
}