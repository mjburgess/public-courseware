package inheritance

// class Foo has a private constructor, so instances cannot be
// created using 'new'
class Foo private (val i: Int)

// object Foo has an apply method that is used to create
// instances
object Foo {
  	def apply(n: Int) = new Foo(n)
}