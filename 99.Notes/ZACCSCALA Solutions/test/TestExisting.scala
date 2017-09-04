import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by kat on 28/01/15.
 */
class TestExisting extends FlatSpec with Matchers{

  //1
  "A BankAccount" should "be created with a zero balance when using the secondary constructor" in {
    val ba = new BankAccount("123")
    ba.balance should be (0.0)
  }

  it should "set the balance to 100 when given that parameter" in {
    val ba = new BankAccount("123", 100)
    ba.balance should be (100.0)
  }

  it should "have a balance of 50 after depositing 50 to a bank account which uses the secondary constructor" in {
    val ba = new BankAccount("123")
    ba.deposit(50)
    ba.balance should be (50.0)
  }

  it should "have an unchanged balance if the person tries to withdraw more money than they have" in {
    val ba = new BankAccount("123")
    ba.deposit(50)
    ba.withdraw(100)
    ba.balance should be (50.0)
  }

  it should "have a balance of 25 after depositing 50 and withdrawing 25" in {
    val ba = new BankAccount("123")
    ba.deposit(50)
    ba.withdraw(25)
    ba.balance should be (25.0)
  }

  //2
  def intPow(x: Double, n: Int): Double = {
    // n is >0 and even
    if (n > 0 & n % 2 == 0)  { val y = intPow(x, n/2); y*y }
    // n is odd...
    else if (n > 0) x * intPow(x, n - 1)
    else if (n == 0) 1
    else /*(n < 0)*/ 1 / intPow(x, -n)
  }

  "intPower" should "return a value of 25 when given a value of x=5 and n=2" in {
    intPow(5,2) should be (25)
  }

  it should "return a value of 1 when given n = 0" in {
    intPow(100, 0) should be (1)
  }

  it should "return the inverse when given -5" in {
    intPow(5,-2) should be (0.04)
  }

  //3
  def leapYear(year : Int) =
    if (year % 4 == 0 &&
      (year % 100 != 0 || year % 400 == 0)
    ) true else false

  "leapYear" should "return true for 2012" in {
    leapYear(2012) should be (true)
  }

  it should "return true for 2000" in {
    leapYear(2000) should be (true)
  }

  it should "return false for 2015" in {
    leapYear(2014) should be (false)
  }

}




//original code!
trait Logger {
  def Log(msg: String) {}
}

//14
trait ConsoleLogger extends Logger {
  override def Log(msg: String) {println(msg)}
}

//17
trait TimeStampLogger extends Logger {
  override def Log(msg:String) {
    import java.util.Date
    val d = new Date()

    println(d + " " +  msg)
  }
}

class BankAccount(val AccountNumber : String, StartingBalance : Double) extends Logger{
  def this(AccountNumber: String) = this(AccountNumber, 0)
  var privateBalance = StartingBalance;

  def balance = privateBalance;

  def deposit(amount : Double) {
    privateBalance += amount
    Log("New Balance: " + privateBalance)
  }

  def withdraw(amount : Double): Unit = {
    if (amount <= privateBalance){
      privateBalance -= amount;
      Log("Withdrawal successful")
    } else {
      Log("Not enough money to withdraw amount")
    }
  }
  override def toString = "BankAccount [" + AccountNumber + ", "+  privateBalance + "]"

}
