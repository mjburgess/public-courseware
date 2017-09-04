package functional

object foldReduce {
  println("fold and reduce")                      //> fold and reduce
  
 	val names = List("Dave", "Emma", "Bill")  //> names  : List[String] = List(Dave, Emma, Bill)
	// fold takes a starting value for the accumulator, so it always comes first
 	val str = names.foldLeft("") ( (acc, n) =>	acc + ", " + n	)
                                                  //> str  : String = , Dave, Emma, Bill
 
  println(str)                                    //> , Dave, Emma, Bill

	val s1 = (names :\ "")(_ + ", " + _)      //> s1  : String = "Dave, Emma, Bill, "
	println(s1)                               //> Dave, Emma, Bill, 
	
	// reduce doesn't take a starting value, so it is useful for concatenation
 	val str2 = names.reduceLeft ( (acc, n) => acc + ", " + n )
                                                  //> str2  : String = Dave, Emma, Bill
 
  println(str2)                                   //> Dave, Emma, Bill
  
  val add1 = (x: Int) => x+1                      //> add1  : Int => Int = <function1>
  val times2 = (x: Int) => x*2                    //> times2  : Int => Int = <function1>
    
  val nums = 1 to 5 toList                        //> nums  : List[Int] = List(1, 2, 3, 4, 5)
    
  val n = nums map add1 map times2                //> n  : List[Int] = List(4, 6, 8, 10, 12)
  // results in times2(add1(x))
  val addAndTimes = times2 compose add1           //> addAndTimes  : Int => Int = <function1>
  val n2 = nums map addAndTimes                   //> n2  : List[Int] = List(4, 6, 8, 10, 12)

  val addAndTimes2 = add1 andThen times2          //> addAndTimes2  : Int => Int = <function1>
  val n3 = nums map addAndTimes2                  //> n3  : List[Int] = List(4, 6, 8, 10, 12)
    
  val funcs = List(add1, times2, add1)            //> funcs  : List[Int => Int] = List(<function1>, <function1>, <function1>)

  nums map (funcs reduceLeft (_ compose _))       //> res0: List[Int] = List(5, 7, 9, 11, 13)
}