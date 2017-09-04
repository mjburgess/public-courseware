package inheritance

trait BaseTrait {
	// Base version does nothing
	def doSomething(s: String) { }
}

trait ConcreteTrait extends BaseTrait {
	override def doSomething(s: String) { println(s) }
}

class Foo extends BaseTrait {
	def bar() { doSomething("hi!") }
}

trait One extends BaseTrait {
	override def doSomething(s: String) { println("oh, " + s) }
}

trait Two extends BaseTrait {
	override def doSomething(s: String) { super.doSomething("say, " + s) }
	def doSomethingElse(s: String) { println("doSomethingElse: " + s) }
}

trait Three extends Two {
	override def doSomething(s: String) { super.doSomething("well, " + s) }
	override def doSomethingElse(s: String) { super.doSomethingElse("Another " + s) }
}

class Foo2 extends One with Two {
	def bar() { doSomething("hi!"); doSomethingElse("ouch!") }
}

object traits {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val v1 = new Foo                                //> v1  : inheritance.Foo = inheritance.Foo@7827aaf0
  v1.bar
  
  val v2 = new Foo with ConcreteTrait             //> v2  : inheritance.Foo with inheritance.ConcreteTrait = inheritance.traits$$a
                                                  //| nonfun$main$1$$anon$2@e805af4
  v2.bar                                          //> hi!
  
  val v3 = new Foo2                               //> v3  : inheritance.Foo2 = inheritance.Foo2@769fb152
  v3.bar                                          //> oh, say, hi!
                                                  //| doSomethingElse: ouch!
  
  val v4 = new Foo2 with Three                    //> v4  : inheritance.Foo2 with inheritance.Three = inheritance.traits$$anonfun
                                                  //| $main$1$$anon$1@1be514e4
  v4.bar                                          //> oh, say, well, hi!
                                                  //| doSomethingElse: Another ouch!
}