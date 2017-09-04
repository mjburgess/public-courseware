package collections

object lists {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val fruit = List("apple", "banana", "kumquat")  //> fruit  : List[String] = List(apple, banana, kumquat)
  
  val fruit2 = "pear" :: fruit                    //> fruit2  : List[String] = List(pear, apple, banana, kumquat)
  
  
  val nums = 1 :: 2 :: 3 :: 4 :: Nil              //> nums  : List[Int] = List(1, 2, 3, 4)
  println(nums.head)                              //> 1
  println(nums.tail)                              //> List(2, 3, 4)
  println(nums.init)                              //> List(1, 2, 3)
  println(nums.last)                              //> 4
  
  val a = nums take 2                             //> a  : List[Int] = List(1, 2)
  val b = nums drop 2                             //> b  : List[Int] = List(3, 4)
  
  // ++ and ::: do the same thing
  val nums2 = a ++ b                              //> nums2  : List[Int] = List(1, 2, 3, 4)
  val nums3 = a ::: b                             //> nums3  : List[Int] = List(1, 2, 3, 4)
  
  println(nums(1))                                //> 2
  
  val add1 = (x: Int) => x+1                      //> add1  : Int => Int = <function1>
  val times2 = (x: Int) => x*2                    //> times2  : Int => Int = <function1>
    
  val n = 1 to 5 toList                           //> n  : List[Int] = List(1, 2, 3, 4, 5)
  
  n map add1                                      //> res0: List[Int] = List(2, 3, 4, 5, 6)
  val n1 = n map add1 map times2                  //> n1  : List[Int] = List(4, 6, 8, 10, 12)
  
  n1 foreach (n => println(n))                    //> 4
                                                  //| 6
                                                  //| 8
                                                  //| 10
                                                  //| 12
  val l1 = List(1, 2, 3)                          //> l1  : List[Int] = List(1, 2, 3)
  val l2 = l1 ++ l1                               //> l2  : List[Int] = List(1, 2, 3, 1, 2, 3)
  
}