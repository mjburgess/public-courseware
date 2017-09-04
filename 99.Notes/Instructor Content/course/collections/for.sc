package collections

object for_test {
  println("for comprehension examples")           //> for comprehension examples
  
  val r = 0 to 5                                  //> r  : scala.collection.immutable.Range.Inclusive = Range(0, 1, 2, 3, 4, 5)
  val r1 = for(i <- r) yield i*2                  //> r1  : scala.collection.immutable.IndexedSeq[Int] = Vector(0, 2, 4, 6, 8, 10)
                                                  //| 
  
  val r2 = for {
  	i <- 0 to 2
  	j <- 1 to 3} yield (i, j)                 //> r2  : scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((0,1), (0,2
                                                  //| ), (0,3), (1,1), (1,2), (1,3), (2,1), (2,2), (2,3))
  	
  val r3 = for(i <- 0 to 20 if (i%2 == 0 && i > 14)) yield i*i
                                                  //> r3  : scala.collection.immutable.IndexedSeq[Int] = Vector(256, 324, 400)
        
  class Person(val name: String, val female: Boolean)
  
  val people = List(
  	new Person("Dave Smith", false),
  	new Person("Jill Smith", true),
  	new Person("Bill Davis", false),
  	new Person("Anna Bryant", true)
  )                                               //> people  : List[collections.for_test.Person] = List(collections.for_test$$ano
                                                  //| nfun$main$1$Person$1@1350a3dc, collections.for_test$$anonfun$main$1$Person$1
                                                  //| @1335ca6c, collections.for_test$$anonfun$main$1$Person$1@307c824e, collectio
                                                  //| ns.for_test$$anonfun$main$1$Person$1@3e4ee7c0)
 	for(person: Person <- people
    if !person.female
    if person.name.contains("ill"))
    println(person.name)                          //> Bill Davis
  
  // Alternative syntax with curly braces
  for { person <- people
    if !person.female
    name = person.name
    if name.contains("ill") }
    println(name)                                 //> Bill Davis
    
    
  case class Company( val name :String, val region :String, val avgSalary :Int )
	case class Employee( val name :String, val companyName :String, val age :Int )
	
	val companies = List( Company( "SAL", "HE", 2000 ),
                      Company( "GOK", "DA", 2500 ),
                      Company( "MIK", "DA", 3000 ) )
                                                  //> companies  : List[collections.for_test.Company] = List(Company(SAL,HE,2000)
                                                  //| , Company(GOK,DA,2500), Company(MIK,DA,3000))
 
	val employees = List( Employee( "Joana", "GOK", 20 ),
                      Employee( "Mikey", "MIK", 31 ),
                      Employee( "Susan", "MIK", 27 ),
                      Employee( "Frank", "GOK", 28 ),
                      Employee( "Ellen", "SAL", 29 ) )
                                                  //> employees  : List[collections.for_test.Employee] = List(Employee(Joana,GOK,
                                                  //| 20), Employee(Mikey,MIK,31), Employee(Susan,MIK,27), Employee(Frank,GOK,28)
                                                  //| , Employee(Ellen,SAL,29))

	val result =
    for{ e <- employees
                if e.age > 25
                salary = e.age * 100
 
          c <- companies
                if c.region == "DA"
                if c.name == e.companyName
                if c.avgSalary < salary
    }
    yield ( e.name, c.name, salary - c.avgSalary )//> result  : List[(String, String, Int)] = List((Mikey,MIK,100), (Frank,GOK,30
                                                  //| 0))
 
	println( result )                         //> List((Mikey,MIK,100), (Frank,GOK,300))
}