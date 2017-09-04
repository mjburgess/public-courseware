abstract class Ticket()
case class Standard(val seat: String) extends Ticket
case class Premium() extends Ticket

case class Customer(val ticket: Ticket)

case class Film(val name: String) {
  override def toString: String = name
}

class Showing(val film: Film, val screen: String) {
  def admit(c: Customer) = c.ticket match {
    case Standard(s) => s"Welcome to $screen, $film. You're seated at $s."
    case Premium() => s"Welcome to $screen, $film. You're seated in VIP."
  }
}

class Cinema(val name: String, val showings: Map[Film, Showing]) {
  def admit(film: Film, customers: Seq[Customer]) = customers map { showings(film).admit(_) }

  def describe() = s"Welcome to $name! Now showing: ${showings.keySet.mkString}"
}


