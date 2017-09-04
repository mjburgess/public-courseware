package matching

object lists {
  println("Matching on lists")                    //> Matching on lists
  
  def func(l: List[Int]) = l match {
  	case Nil => "nil"
  	case h :: Nil => "one"
  	case h :: h2 :: Nil => "two"
  	case h :: rest => "three"
  	case _ => "something else"
  }                                               //> func: (l: List[Int])String
  
  func(List())                                    //> res0: String = nil
  func(List(1))                                   //> res1: String = one
  func(List(1,2))                                 //> res2: String = two
  func(List(1,2,3))                               //> res3: String = three
  func(List(1,2,3,4,5))                           //> res4: String = three
  
  def sum(l: List[Int], s: Int): Int = l match {
  	case Nil => s
  	case h :: rest => sum(rest, s+h)
  }                                               //> sum: (l: List[Int], s: Int)Int
  
  val l = sum(List(1,2,3,4,5), 0)                 //> l  : Int = 15
  
  def sum2(l: List[Int]): Int = {
  	def sumx(l: List[Int], s: Int): Int = l match {
  		case Nil => s
  		case h :: rest => sum(rest, s+h)
  	}
		sumx(l, 0)
  }                                               //> sum2: (l: List[Int])Int

  val l2 = sum2(List(1,2,3,4,5))                  //> l2  : Int = 15
}