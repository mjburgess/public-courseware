package classes

object overload {
  println("Overloading and operators")            //> Overloading and operators
  
  class IntVal(val v: Int) {
    // overloaded add methods
  	def add(n: Int) = new IntVal(v + n)
  	def add(n1: Int, n2: Int) = new IntVal(v + n1 + n2)
  	
  	// A simple exponent operator
  	def **(n: Int) = java.lang.Math.pow(v, n).intValue
  }
  
  val v1 = new IntVal(3)                          //> v1  : classes.overload.IntVal = classes.overload$$anonfun$main$1$IntVal$1@10
                                                  //| 83964f
  println(v1.v)                                   //> 3
  
  val v2 = v1.add(2)                              //> v2  : classes.overload.IntVal = classes.overload$$anonfun$main$1$IntVal$1@2f
                                                  //| 012501
  println(v2.v)                                   //> 5
  
  val v3 = v1.add(2, 2)                           //> v3  : classes.overload.IntVal = classes.overload$$anonfun$main$1$IntVal$1@74
                                                  //| 247cc2
  println(v3.v)                                   //> 7
  
  println(v1 ** 2)                                //> 9
}