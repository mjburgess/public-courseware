package inheritance


object packages {
  println("Packages and package objects")         //> Packages and package objects
  
  // Namespace 'one' is in scope
  val a = new one.Item("Watch", 200.0)            //> a  : inheritance.one.Item = inheritance.one.Item@2f012501
  
  // Namespace 'two' is also in scope, and we can access members
  // of the package object 'Discount'
  val c = a.discountedCost(two.Discount.medium)   //> c  : Double = 170.0
}