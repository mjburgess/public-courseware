package collections

object tuples {
  println("Looking at tuples")                    //> Looking at tuples
  
  val t = (1, "fred", 45)                         //> t  : (Int, String, Int) = (1,fred,45)
  println(t._1)                                   //> 1
  
  val (id, name, _) = t                           //> id  : Int = 1
                                                  //| name  : String = fred
  println(id)                                     //> 1

  def sumAndAverage(nums: List[Int]) = {
  	val sum = nums.sum
    val average = (sum:Double) / nums.length
    (sum, average)  // return a tuple
  }                                               //> sumAndAverage: (nums: List[Int])(Int, Double)
  
	val l = List(10, 11, 12, 13, 14)          //> l  : List[Int] = List(10, 11, 12, 13, 14)
	val (sum, av) = sumAndAverage(l)          //> sum  : Int = 60
                                                  //| av  : Double = 12.0
}