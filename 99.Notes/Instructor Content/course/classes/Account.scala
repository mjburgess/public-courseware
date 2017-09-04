class Account(val accountNumber: Long, openingBalance: Double) {
  		private var _balance = openingBalance
  		
  		def this(accountNumber: Long) = this(accountNumber, 0.0)
  		
  		def deposit(amt: Double) { 
  		  if (amt < 0.0) throw new IllegalArgumentException("Can't deposit negative amount")
  		  _balance += amt
  		 }
  		
  		def withdraw(amt: Double) { 
  		  if (amt < 0.0) throw new IllegalArgumentException("Can't withdraw negative amount")
  		  _balance -= amt 
  		}
  		
  		def balance = _balance
  		
  		override def toString() = accountNumber + ": " + _balance
  }
  
  // "companion object"
  object Account {
	  private var _num: Long = 1000
	  
  		def printAccountDetails(acc: Account) { println(acc._balance) }
  		// Use apply as a factory
	  	// You can overload this as many times as you like
  		def apply() = { _num += 1; new Account(_num, 0.0) }
  }