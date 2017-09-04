package functional

object flatMap {
  println("map and flatMap examples")             //> map and flatMap examples
  
  val l = List(1, 2, 3, 4)                        //> l  : List[Int] = List(1, 2, 3, 4)
  
  def f(v: Int) = List(v-1, v, v+1)               //> f: (v: Int)List[Int]
 	 
  l.map(x => f(x))                                //> res0: List[List[Int]] = List(List(0, 1, 2), List(1, 2, 3), List(2, 3, 4), Li
                                                  //| st(3, 4, 5))
 	l.flatMap(x => f(x))                      //> res1: List[Int] = List(0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 4, 5)
 	
 	val names = List(List("fred", "bill"), List(), List("anne", "carol"))
                                                  //> names  : List[List[String]] = List(List(fred, bill), List(), List(anne, caro
                                                  //| l))
 	
 	names.map( _.map( _.toUpperCase) )        //> res2: List[List[String]] = List(List(FRED, BILL), List(), List(ANNE, CAROL))
                                                  //| 
  names.flatMap( _.map( _.toUpperCase) )          //> res3: List[String] = List(FRED, BILL, ANNE, CAROL)
}