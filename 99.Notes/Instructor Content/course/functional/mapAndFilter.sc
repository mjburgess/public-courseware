package functional

object mapAndFilter {
  println("Map and filter")                       //> Map and filter
  
  val prices = Map("Tea" -> 10, "Coffee" -> 15, "Coke" -> 12)
                                                  //> prices  : scala.collection.immutable.Map[String,Int] = Map(Tea -> 10, Coffee
                                                  //|  -> 15, Coke -> 12)
  prices.mapValues(x => x * 1.2)                  //> res0: scala.collection.immutable.Map[String,Double] = Map(Tea -> 12.0, Coffe
                                                  //| e -> 18.0, Coke -> 14.399999999999999)
  
  val nums = List(1, 2, 3, 4, 5, 6)               //> nums  : List[Int] = List(1, 2, 3, 4, 5, 6)
  
  nums.filter(x => x%2 == 0)                      //> res1: List[Int] = List(2, 4, 6)
  nums.filter(x => (x%2 == 0 && x > 2))           //> res2: List[Int] = List(4, 6)
  
  val numbers = List(5, 4, 8, 6, 2)               //> numbers  : List[Int] = List(5, 4, 8, 6, 2)
	numbers.foldLeft(0) ( (a, i) => a + i )   //> res3: Int = 25
	numbers.foldLeft(0) ( _ + _ )             //> res4: Int = 25
	
	numbers.foldLeft(0)( _ / _ )              //> res5: Int = 0
	
	val words = List("scala", "akka", "play framework", "sbt", "typesafe")
                                                  //> words  : List[String] = List(scala, akka, play framework, sbt, typesafe)
  val sentence = "Does this sentence say anything about scala?"
                                                  //> sentence  : String = Does this sentence say anything about scala?
  val sentence2 = "And how about this one?"       //> sentence2  : String = And how about this one?

  (words.foldLeft(false)( _ || sentence.contains(_) ))
                                                  //> res6: Boolean = true
  (words.foldLeft(false)( _ || sentence2.contains(_) ))
                                                  //> res7: Boolean = false

}