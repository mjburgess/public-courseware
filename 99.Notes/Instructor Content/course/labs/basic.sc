package labs

object basic {
  println("Basics")                               //> Basics
  
	for(year <- 1960 to 2010
		if ((year%4 == 0 && year%100 != 0) || year%400 == 0
    )) yield year                                 //> res0: scala.collection.immutable.IndexedSeq[Int] = Vector(1960, 1964, 1968, 
                                                  //| 1972, 1976, 1980, 1984, 1988, 1992, 1996, 2000, 2004, 2008)
   
  class Person(val name: String) {
  		println("Creating a person")

  		def sayHi() = println(s"hi, $name")
	}
  
  val p1 = new Person("Dave")                     //> Creating a person
                                                  //| p1  : labs.basic.Person = labs.basic$$anonfun$main$1$Person$1@707e4019
  p1.sayHi                                        //> hi, Dave
}