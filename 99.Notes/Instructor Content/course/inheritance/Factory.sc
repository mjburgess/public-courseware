package inheritance

// Make a constructor private in order to force the use of the factory
object Factory {
  println("WLooking at factories")                //> WLooking at factories
  
  // val f = new Foo		// won't work because ctor is private
  val f = Foo(1)                                  //> f  : inheritance.Foo = inheritance.Foo@5de39029
}