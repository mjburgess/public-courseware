package collections

object maps {
  println("Map examples")                         //> Map examples
  
  val ages = Map("Dave" -> 45, "Jane" -> 33)      //> ages  : scala.collection.immutable.Map[String,Int] = Map(Dave -> 45, Jane ->
                                                  //|  33)
  
  println(ages("Jane"))                           //> 33
  
  val ages2 = ages + ("Bill" -> 50)               //> ages2  : scala.collection.immutable.Map[String,Int] = Map(Dave -> 45, Jane -
                                                  //| > 33, Bill -> 50)
	for((person, age) <- ages2)
		println(s"$person is $age")       //> Dave is 45
                                                  //| Jane is 33
                                                  //| Bill is 50
}