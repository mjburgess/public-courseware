package functional

object Option {
  println("Looking at Option")                    //> Looking at Option
  
  def foo(n: Int) = if (n%2 == 0) Some(n) else None
                                                  //> foo: (n: Int)Option[Int]
  
  foo(2)                                          //> res0: Option[Int] = Some(2)
  foo(3)                                          //> res1: Option[Int] = None
  
  val e: List[Option[Int]] = List(Some(1), None, None, Some(4), Some(5), None)
                                                  //> e  : List[Option[Int]] = List(Some(1), None, None, Some(4), Some(5), None)
	val v = e map (x => x)                    //> v  : List[Option[Int]] = List(Some(1), None, None, Some(4), Some(5), None)
	val v1 = e.flatMap(x => x)                //> v1  : List[Int] = List(1, 4, 5)
}