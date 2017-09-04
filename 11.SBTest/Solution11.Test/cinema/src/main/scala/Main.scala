
object CinemaApplication {
  def main(args: Array[String]): Unit = {
    val fantasia = Film("Fantasia")
    val fantasound = new Showing(fantasia, "Fantasound Stage")

    val oldPictureHouse = new Cinema("The Old Picture House",
                                     Map(fantasia -> fantasound))

    val customers = Vector(Customer(Premium()),
                           Customer(Standard("B10")))


    oldPictureHouse.describe() +: oldPictureHouse.admit(fantasia, customers) foreach {
      println _
    }
  }
}