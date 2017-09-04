package labs

object accounts {
  println("Playing with accounts")                //> Playing with accounts
  
  val acc1 = new Account(1000, 10.0)              //> acc1  : labs.Account = labs.Account@423798e2
  acc1.balance                                    //> res0: Double = 10.0
  
  val acc2 = Account(1001, 0.0)                   //> acc2  : labs.Account = labs.Account@50163f64
  acc2.balance                                    //> res1: Double = 0.0
  
  acc1.deposit(100.0)
  acc1.balance                                    //> res2: Double = 110.0
  
  try {
  		acc1.withdraw(1000.0)
  }
  catch {
  		case ex: IllegalArgumentException => println("Arg.Exception: " + ex.getMessage())
  		case ex: Exception => println(ex.getMessage())
  }                                               //> Arg.Exception: Cannot overdraw
}