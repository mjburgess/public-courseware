package traits

class Account(number: Long, bal: Double) {
	var _balance = bal
	// Getter for balance
	def balance = _balance
	
	def this(number: Long) = this(number, 0.0)
}

// Companion object for Account
object Account {
  def apply(number: Long) = new Account(number, 0.0)
}