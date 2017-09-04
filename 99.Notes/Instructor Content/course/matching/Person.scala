package matching

case class Person(val name: String, val address: Address)

//object Person {
//  def unapply(p: Person): Option[(String, Address)] = 
//    Some((p.name, p.address))
//}

case class Address(val city: String, val country: String)

//object Address {
//  def unapply(a: Address): Option[(String, String)] = 
//    Some((a.city, a.country))
//}