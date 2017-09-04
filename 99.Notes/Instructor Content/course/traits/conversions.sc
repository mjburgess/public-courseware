object Traits {
  println("Playing with traits")                  //> Playing with traits
  
  trait Conversions {
  	def from(x: Double): Double
  	def to(x: Double): Double
  }
  
  object MilesKm extends Conversions {
  	def from(miles: Double): Double = miles / 0.62137
  	def to(km: Double): Double = km * 0.62137
  }
  
  object HectaresAcres extends Conversions {
  	def from(hectares: Double): Double = hectares * 2.4711
  	def to(acres: Double): Double = acres / 2.4711
  }
  
  val ten = 10.0                                  //> ten  : Double = 10.0
  println(s"$ten miles is " + MilesKm.from(ten) + " km")
                                                  //> 10.0 miles is 16.093470878864444 km
  println(s"$ten hectares is " + HectaresAcres.from(ten) + " acres")
                                                  //> 10.0 hectares is 24.711 acres
  
}