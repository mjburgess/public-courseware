package inheritance

abstract class Abs {
	type T								// type is not defined
	def func(arg: T): T		// abstract method
	val v: T							// abstract value
}

class Concrete extends Abs {
	type T = String
	def func(arg: String): String = v + arg
	val v: String = "hello, "
}

object inheritance {
  println("Inheritance examples")                 //> Inheritance examples

	val s = new Concrete                      //> s  : inheritance.Concrete = inheritance.Concrete@7f7f70bf
	val x = s.func("Brian")                   //> x  : String = hello, Brian
}