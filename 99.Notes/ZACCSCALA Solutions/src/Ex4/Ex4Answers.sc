
//2
abstract class Animal(name: String, age: Int){
  override def toString = "Animal [" + name + ", " + age + "]"
  def move: String
}

//3, 11
class Dog(name: String, age: Int) extends Animal(name, age) with Vocal {
  def move = "I'm running!"
  override def toString = "Dog [" + super.toString + "]"
  def makeNoise = "woof"
}
class Cat(name:String, age: Int) extends Animal(name, age) {
  def move = "saunter"
  override def toString = "Cat [" + super.toString + "]"
//  def makeNoise = "meow"
}

class Rabbit(name:String, age:Int) extends Animal(name, age){
  def move = "hop"
  override def toString = "Rabbit [" + super.toString + "]"
//  def makeNoise = "snuffle"
}

//4
val dog = new Dog("Honey", 5)
val cat = new Cat("Smokey", 12)
val rabbit = new Rabbit("Peter", 7)

//5, 15
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

//6
class SavingsAccount(id : String, startingbalance: Double, rate: Double) extends BankAccount(id, startingbalance){
  def addInterest = privateBalance = privateBalance + (privateBalance * (rate/100))
}

//7
class CurrentAccount(id : String, startingBalance: Double, overdraft: Double) extends BankAccount(id, startingBalance){
  override def withdraw (amount : Double): Unit = {
    if (amount <= (privateBalance + overdraft))
      privateBalance -= amount;
    else
      println("Can't go over overdraft!")
  }
}
val ba = new SavingsAccount("123", 100, 10);
ba.addInterest
println(ba)
val ca = new CurrentAccount("123", 100, 100)
ca.withdraw(50)
ca.withdraw(200)
ca.withdraw(75)
ca


//8
abstract trait Vocal {
  def makeNoise: String
}
//12
trait ConcreteVocal extends Vocal {
  def makeNoise = "meow?";
}
val c2 = new Cat("Kitty", 2) with ConcreteVocal
println(c2.makeNoise)

//13
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

//16
val b1 = new BankAccount("123", 100) with TimeStampLogger
b1.withdraw(50)
b1.deposit(100)
b1.withdraw(150)

//18
val b2 = new BankAccount("1", 100) with TimeStampLogger with ConsoleLogger
b2.withdraw(50)
b2.deposit(100)
b2.withdraw(150)