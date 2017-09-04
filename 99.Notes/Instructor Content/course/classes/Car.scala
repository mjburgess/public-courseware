package classes

class Car(val make: String) {
  private var speed = 0
  private var isSportsCar = false
  
  // Need to qualify access to a member in the object
  def howManyWheels = Car.numberOfWheels
}

object Car {
  def apply(name: String, isSportsCar: Boolean) = {
    val c = new Car(name)
    c.isSportsCar = isSportsCar
    c
  }
  
  def apply(name: String) = new Car(name)
  
  private val numberOfWheels = 4
}