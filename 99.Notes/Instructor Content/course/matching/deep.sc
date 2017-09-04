package matching

object deep {
  println("Deep matching and guards")             //> Deep matching and guards

	case class Location(val city: String, val country: String) {}
	case class Person(val name: String, val age: Int, val loc: Location) {}
  
  val london = new Location("London", "UK")       //> london  : matching.deep.Location = Location(London,UK)
  val brussels = new Location("Brussels", "Belgium")
                                                  //> brussels  : matching.deep.Location = Location(Brussels,Belgium)
  val nyc = new Location("New York", "US")        //> nyc  : matching.deep.Location = Location(New York,US)
  val p1 = new Person("fred", 45, london)         //> p1  : matching.deep.Person = Person(fred,45,Location(London,UK))
  val p2 = new Person("eric", 55, brussels)       //> p2  : matching.deep.Person = Person(eric,55,Location(Brussels,Belgium))
  val p3 = new Person("jane", 12, nyc)            //> p3  : matching.deep.Person = Person(jane,12,Location(New York,US))
  
  def test(p: Person) =  p match {
  	case Person("fred", age, Location(city, country)) => s"fred lives in $city"
  	case Person(name, age, Location(city, "Belgium")) => s"$name lives in Belgium"
  	case Person(name, age, loc) if age < 18 || age > 65 => s"$name is not of working age"
  	case _ => "default"
  }                                               //> test: (p: matching.deep.Person)String
  
  test(p1)                                        //> res0: String = fred lives in London
  test(p2)                                        //> res1: String = eric lives in Belgium
  test(p3)                                        //> res2: String = jane is not of working age
}