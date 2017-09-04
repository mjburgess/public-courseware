package labs

class Account(val number: Long, openingBalance: Double) {
	private var _balance = openingBalance
	private var _interestRate = 0.0
	// Secondary constructors
	def this(number: Long) = this(number, 0.0)
	
	def balance() = _balance
	
	def deposit(amount: Double) = {
	  if (amount > 0.0) _balance += amount
	  else throw new IllegalArgumentException("Negative deposit not allowed")
	}
	
	def withdraw(amount: Double) = {
	  if (amount <= 0.0) throw new IllegalArgumentException("Negative deposit not allowed")
	  else if (_balance - amount < 0.0) throw new IllegalArgumentException("Cannot overdraw")
	  else _balance -= amount
	}
}

object Account {
  def apply(number: Long, balance: Double) = {
    val acc = new Account(number, balance)
    acc._interestRate = 3.0
    acc
  }
  
  def apply(number: Long) = {
    val acc = new Account(number, 0.0)
    acc
  }
}