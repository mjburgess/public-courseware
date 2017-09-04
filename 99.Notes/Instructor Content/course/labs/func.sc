package labs

object func {
  println("Functional prog and collections")      //> Functional prog and collections
  
  def toInt(s: String): Option[Int] = {
  	try {
  		Some(Integer.parseInt(s))
  	}
  	catch {
  		case e: Exception => None
  	}
  }                                               //> toInt: (s: String)Option[Int]
  
  val lst = List("1", "two", "4", "six", "10")    //> lst  : List[String] = List(1, two, 4, six, 10)
  
  val ints = lst flatMap toInt                    //> ints  : List[Int] = List(1, 4, 10)
  
  val cities = Map("London" -> "UK", "Paris" -> "France", "Istanbul" -> "Turkey")
                                                  //> cities  : scala.collection.immutable.Map[String,String] = Map(London -> UK, 
                                                  //| Paris -> France, Istanbul -> Turkey)
  
  for ((k,v) <- cities) yield (v, k)              //> res0: scala.collection.immutable.Map[String,String] = Map(UK -> London, Fran
                                                  //| ce -> Paris, Turkey -> Istanbul)
  
  
  class Employee(val name: String, val reports: Employee*)
  
  val lara = new Employee("Lara")                 //> lara  : labs.func.Employee = labs.func$$anonfun$main$1$Employee$1@cfc82a6
  val bob = new Employee("Bob")                   //> bob  : labs.func.Employee = labs.func$$anonfun$main$1$Employee$1@15e566f3
  val julie = new Employee("Julie", lara, bob)    //> julie  : labs.func.Employee = labs.func$$anonfun$main$1$Employee$1@7c9e6c0
  val jane = new Employee("Jane", julie)          //> jane  : labs.func.Employee = labs.func$$anonfun$main$1$Employee$1@584c4b3e
  
  val persons = List(lara, bob, julie, jane)      //> persons  : List[labs.func.Employee] = List(labs.func$$anonfun$main$1$Employe
                                                  //| e$1@cfc82a6, labs.func$$anonfun$main$1$Employee$1@15e566f3, labs.func$$anonf
                                                  //| un$main$1$Employee$1@7c9e6c0, labs.func$$anonfun$main$1$Employee$1@584c4b3e)
                                                  //| 
  
  val fm1 = persons filter (p => !p.reports.isEmpty)
                                                  //> fm1  : List[labs.func.Employee] = List(labs.func$$anonfun$main$1$Employee$1@
                                                  //| 7c9e6c0, labs.func$$anonfun$main$1$Employee$1@584c4b3e)
  fm1 flatMap (p => (p.reports map (c => (p.name, c.name))))
                                                  //> res1: List[(String, String)] = List((Julie,Lara), (Julie,Bob), (Jane,Julie))
                                                  //| 
}