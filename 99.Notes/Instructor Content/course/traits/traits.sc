package traits

// A trait to work with accounts - this increases the balance
trait AccountMocker extends Account {
	override def balance = _balance * 1.2
}

// A second trait to work with accounts - this reduces the balance
trait AccountMocker2 extends Account {
	override def balance = _balance * 0.9
}

object traits {
  println("Examples of traits")                   //> Examples of traits
  
  // Create using constructor
  val acc1 = new Account(12345, 100.0)            //> acc1  : traits.Account = traits.Account@470d6c27

  // Create using apply()
  val acc2 = Account(23456)                       //> acc2  : traits.Account = traits.Account@423798e2

	// Create an account with a trait that overrides the balance method
  val acc3 = new Account(34567, 100.0) with AccountMocker
                                                  //> acc3  : traits.Account with traits.AccountMocker = traits.traits$$anonfun$ma
                                                  //| in$1$$anon$2@4d0e7233

	// Show how the last trait takes precedence
  val acc4 = new Account(34567, 100.0) with AccountMocker with AccountMocker2
                                                  //> acc4  : traits.Account with traits.AccountMocker with traits.AccountMocker2 
                                                  //| = traits.traits$$anonfun$main$1$$anon$1@7827aaf0
  
  println(acc1.balance)                           //> 100.0
  println(acc2.balance)                           //> 0.0
  println(acc3.balance)                           //> 120.0
  println(acc4.balance)                           //> 90.0
}