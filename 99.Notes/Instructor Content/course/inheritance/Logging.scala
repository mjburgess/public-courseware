package inheritance

trait Logger {
  def Log(msg: String) 
}

trait ConsoleLogger extends Logger {
  override def Log(msg: String) = println(msg)
}

trait EncryptingLogger extends ConsoleLogger {
  override def Log(msg: String) = {
    super.Log("x"+ msg + "x")  
  }
}

class Test extends ConsoleLogger {
	def TestMethod(s: String) = Log(s)
}

object Logging extends App {
	val l1 = new Test
	
	l1.TestMethod("Testing...")

	val l2 = new Test with EncryptingLogger
	l2.Log("Foo")
}