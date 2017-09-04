import org.scalatest._

import scala.collection.immutable._

class FilmSpec extends FlatSpec with Matchers {
  "A film" should "have a name" in {
    val fantasia = Film("Fantasia")

    fantasia.name should be ("Fantasia")
    fantasia.toString should be ("Fantasia")
  }
}

class ShowingSpec extends FlatSpec with Matchers {
  "A showing" should "have a film name and screen" in {
    val fantasia = Film("Fantasia")
    val fantasound = new Showing(fantasia, "Fantasound Stage")

    fantasound.film.name should be ("Fantasia")
    fantasound.screen should be ("Fantasound Stage")
  }
}

class CinemaSpec extends FlatSpec with Matchers {
  "A cinema" should "admit premium and standard ticket holders" in {
    val fantasia = Film("Fantasia")
    val fantasound = new Showing(fantasia, "Fantasound Stage")

    val oldPictureHouse = new Cinema(
        "The Old Picture House",
        Map(fantasia -> fantasound)
    )

    val customers = Vector(Customer(Premium()),
                           Customer(Standard("B10")))


    val output = oldPictureHouse.admit(fantasia, customers)

    output should be (Vector(
      "Welcome to Fantasound Stage, Fantasia. You're seated in VIP.",
      "Welcome to Fantasound Stage, Fantasia. You're seated at B10."
    ))
  }
}