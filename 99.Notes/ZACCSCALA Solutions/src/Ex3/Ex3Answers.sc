
class BankAccount(val AccountNumber : String, StartingBalance : Double){
  def this(AccountNumber: String) = this(AccountNumber, 0)
  var privateBalance = StartingBalance;

  def balance = privateBalance;

  def deposit(amount : Double) {privateBalance += amount}

  def withdraw(amount : Double): Unit = {
    if (amount <= privateBalance){
      privateBalance -= amount;
    } else {
      println("Not enough money to withdraw amount")
    }
  }

  override def toString = "BankAccount [" + AccountNumber + ", "+  privateBalance + "]"

}

object BankAccount {
  private var nextAccountNo = 0;

  def apply(balance: Int) = {
    nextAccountNo += 1;
    new BankAccount(nextAccountNo.toString, balance);
  }

  def apply() = {
    nextAccountNo += 1;
    new BankAccount(nextAccountNo.toString)
  }
}
class Person(n: String, a: Int, e:String){
  //vars as we want these to be changable
  private var privatename = n;
  private var privateage = a;
  private var privateemail = e;
  val bankAccount = BankAccount();

  //getters
  def name = privatename;
  def age = privateage;
  def email = privateemail;

  //setters
  def name_=(newname : String) {
    privatename = newname
  }

  def age_=(newage : Int) {
    if (newage > privateage)
      privateage = newage
    else
      println("Did not change age, you're not getting any younger!")
  }

  def email_=(newemail : String) {
    privateemail = newemail
  }
  override def toString = "Person [" + privatename + ", " + privateage + ", " + privateemail + "\n" + bankAccount + "\n]";
}
val alice = new Person("alice", 23, "alice@email.com")
//println(alice);
alice.age = 21;
alice.age = 29;
val aliceAccount = new BankAccount("123", 10);
aliceAccount.deposit(100);
aliceAccount.withdraw(50);
println(aliceAccount)
alice.bankAccount.deposit(10);
alice;
val bob = new Person("Bob", 52, "Bob@email.com");
bob.bankAccount.deposit(500);
bob.bankAccount.withdraw(50);
bob;