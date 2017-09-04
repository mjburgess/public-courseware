package classes

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val acc1 = new Account(123456)                  //> acc1  : classes.Account = Balance: 0.0, rate: classes.Account$@9c71a93.inter
                                                  //| estRate
  println(acc1.balance)                           //> 0.0
  
  acc1.deposit(100.0)
  val x = acc1.balance                            //> x  : Double = 100.0
  
  println(acc1)                                   //> Balance: 100.0, rate: classes.Account$@9c71a93.interestRate
  
  println(acc1.number)                            //> 123456
  
  //Account.interestRate = 2.5
  
  val car1 = Car("Ford", false)                   //> car1  : classes.Car = classes.Car@619dcdad
  val car2 = Car("Renault")                       //> car2  : classes.Car = classes.Car@3f2a4aec
}