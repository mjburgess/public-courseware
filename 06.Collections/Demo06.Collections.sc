// CHAPTER 9:  GENERICS & COLLECTIONS
// PROBLEM:    Use collections to analyse information about animals and ports.
// PROCESS:    Demonstration & Discussion.
// EXP?        Linked Lists, Arrays, HashMaps, etc.
// USE?        Efficient representation and access of data.


{
  //REVIEW: TYPES
  /*
   T[A]  T of A
   A, B  A and B
   A =>  A to B

   */
}
// *COLLECTIONS DIAGRAM*

/*
The Traversable "implements the behavior common to all collections
in terms of a foreach method"

Iterable trait defines an iterator,
which lets you loop through a collection’s elements one at a time

An IndexedSeq indicates that random access of elements is efficient

A LinearSeq implies that the collection can be efficiently
split into head and tail components

Scala Map is a collection of key/value pairs, where all the keys must be unique.
*/

{

  // CREATING COLLECTIONS
  import scala.collection.immutable._

  Traversable(1, 2, 3)                    // foreach: filter, ..
  Iterable("x", "y", "z")                 // for(e <- xs)

  IndexedSeq(1.0, 2.0)                    // fast xs(i)
  LinearSeq("A", "B", "C")                // fast xs.head

  Map("x" -> 24, "y" -> 25, "z" -> 26)
  Set('R', 'G', 'R', 'B')
  SortedSet("hello", "world")

  /*
scala> res27: immutable.Traversable[Int] = List(1, 2, 3)
scala> res28: immutable.Iterable[String] = List(x, y, z)
scala> res29: immutable.IndexedSeq[Double] = Vector(1.0, 2.0)
scala> res30: immutable.LinearSeq[String] = List(A, B, C)
scala> res31: immutable.Map[String,Int] = Map(x -> 24, y -> 25, z -> 26)
scala> res32: immutable.Set[Char] = Set(R, G, B)
scala> res33: immutable.SortedSet[String] = TreeSet(hello, world)
   */
}

{
  // CONVERTING BETWEEN TYPES OF COLLECTION
  "ABCD".toList
  Map("S" -> 1, "A" -> 2).toList
  List("UK", "UK", "US").toSet
  List( ("name", "email"),  ("sherlock", "s@example.com") ).toMap

  (0 to 10).toList
  (0 to 10).toArray

  /*
scala> res34: List[Char] = List(A, B, C, D)
scala> res35: List[(String, Int)] = List((S,1), (A,2))
scala> res36: immutable.Set[String] = Set(UK, US)
scala> res38: List[Int] = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
scala> res39: Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

   */
}

{
  // RANGE
  val rangeInt = 1 to 10 by 2

  val rangeDouble = 1.0 to 5.0 by 0.2

  val rangeChr = 'a' to 'z'

  ('a' to 'z').toList

  1 to 10 by 2

  1.0 to 5.0 by 0.2

  println('a' to 'z' by 2)

  ('0' to '9').toList

  /*
rangeInt: immutable.Range = inexact Range 1 to 10 by 2
scala> rangeDouble: immutable.NumericRange[Double] = NumericRange 1.0 to 5.0 by 0.2
scala> rangeChr: immutable.NumericRange.Inclusive[Char] = NumericRange a to z
scala> res40: List[Char] = List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z)
scala> res41: immutable.Range = inexact Range 1 to 10 by 2
scala> res42: immutable.NumericRange[Double] = NumericRange 1.0 to 5.0 by 0.2
scala> NumericRange a to z by 
scala> res44: List[Char] = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

   */
}

{
  // ARRAY & ARRAY BUFFER
  import scala.collection.mutable.ArrayBuffer

  val _ab = IndexedSeq(1, 2, 3)
  val ab = new ArrayBuffer[String]

  ab += "Michaael"
  ab += "Michaael"
  ab += "Michaael"

  /*
scala> ab: mutable.ArrayBuffer[String] = ArrayBuffer()
scala> res45: ab.type = ArrayBuffer(Michaael)
scala> res46: ab.type = ArrayBuffer(Michaael, Michaael)
scala> res47: ab.type = ArrayBuffer(Michaael, Michaael, Michaael)

   */
}

{
  // LIST & LIST BUFFER
  import scala.collection.mutable._

  val names = List("Michael", "Sherlcok", "Watson")
  val mutNames = ListBuffer("Michael", "Sherlock", "Watson")

  val numbers = Seq(1,2,3)

  println(names)

  val countries = "UK" :: "FRANCE" :: "US" :: Nil


  List.empty
  List.empty == Nil


  List.fill(3)("foo")
  "foo".toList

  (for(c <- 'a' to 'z') yield c + 1).toList
  (for(c <- 'a' to 'z') yield c + 1).toList.mkString

  /*

scala> names: List[String] = List(Michael, Sherlcok, Watson)
scala> mutNames: mutable.ListBuffer[String] = ListBuffer(Michael, Sherlock, Watson)
scala> numbers: mutable.Seq[Int] = ArrayBuffer(1, 2, 3)
scala> List(Michael, Sherlcok, Watson)
scala> countries: List[String] = List(UK, FRANCE, US)
scala> res49: List[Nothing] = List()
scala> res50: Boolean = true
scala> res51: List[String] = List(foo, foo, foo)
scala> res52: List[Char] = List(f, o, o)
scala> res53: List[Int] = List(98, 99, 100, 101, 102, 103, 104, 105,
                               106, 107, 108, 109, 110, 111, 112, 113,
                               114, 115, 116, 117, 118, 119, 120, 121,
                               122, 123)
scala> res54: String = 98991001011021031041051061071081091101111121131
                       14115116117118119120121122123

   */
}



{
  // VECTOR
  // the best, typical-use-case collection

  val names = Vector("Fluffy", "Spot", "Fido")
  val letters = for(c <- 'a' to 'z') yield c + 1

  val cent = 100 +: Vector.empty
  val dbl  = 200 +: 100 +: Vector.empty

  for (name <- names) println(name)

  /*
names: immutable.Vector[String] = Vector(Fluffy, Spot, Fido)
scala> letters: immutable.IndexedSeq[Int] = Vector(98, 99, 100, 101,
102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115,
116, 117, 118, 119, 120, 121, 122, 123)

scala> cent: immutable.Vector[Int] = Vector(100)
scala> dbl: immutable.Vector[Int] = Vector(200, 100)
scala> Fluffy
Spot
Fido
   */
}

{
  // MAPs
val point = 0 -> 1

  println(point._1)
  println(point._2)

  val prefs = Map("Michael" -> "DrPepper", "Adam" -> "Coffee")

  println(prefs)
  println(prefs("Michael"))
  println(prefs - "Michael")


  val preferences = Map(
    "Michael" -> List("Coke", "Pepsi"),
    "Tim" -> List("Apple Juice", "Water")
  )

  preferences + ("Lucie" -> List("Beer", "Wine"))

  println(preferences.keySet)

  val value: Option[List[String]] = preferences.get("Susan")

  for ((person, preference) <- prefs) println(person, preference)
  for (pair <- prefs) println(pair)

  /*

scala> point: (Int, Int) = (0,1)
scala> 0
scala> 1
scala> prefs: mutable.Map[String,String] = Map(Adam -> Coffee,
                                               Michael -> DrPepper)

scala> Map(Adam -> Coffee, Michael -> DrPepper)
scala> DrPepper
scala> Map(Adam -> Coffee)
scala>  preferences: mutable.Map[String,List[String]] = Map(Tim -> List(Apple Juice, Water), Michael -> List(Coke, Pepsi))
scala> res61: mutable.Map[String,List[String]] = Map(Tim -> List(Apple Juice, Water), Michael -> List(Coke, Pepsi), Lucie -> List(Beer, Wine))
scala> Set(Tim, Michael)
scala> value: Option[List[String]] = None
scala> (Adam,Coffee)
(Michael,DrPepper)

scala> (Adam,Coffee)
(Michael,DrPepper)

   */
}

{
  // SETS
  val people = Set("Adrian", "Michael", "Sherlock")
  val good = Set("Michael")
  val bad = Set("Adrian")

  people & good
  people &~ bad

  for (person <- people) println(person)


  val names = List("Michael", "Michael", "Sherlock")
  (names.toSet &~ good).toList

  val services =  scala.io.Source.fromFile("""/etc/services""")
  val inUsePorts = (for (
    line <- services.getLines;
    port <- """\d+""".r.findFirstIn(line)
  ) yield port.toInt).toSet

  val freePorts = Set(1 to 200: _*) &~ inUsePorts
  println(freePorts)

  /*
  people: mutable.Set[String] = Set(Michael, Sherlock, Adrian)
scala> good: mutable.Set[String] = Set(Michael)
scala> bad: mutable.Set[String] = Set(Adrian)
scala> res65: mutable.Set[String] = Set(Michael)
scala> res66: mutable.Set[String] = Set(Michael, Sherlock)

scala> Michael
Sherlock
Adrian

scala> names: List[String] = List(Michael, Michael, Sherlock)
scala> res68: List[String] = List(Sherlock)
scala> services: scala.io.BufferedSource = non-empty iterator

inUsePorts: immutable.Set[Int] = Set(2163, 645, 69, 2199, 3021, 1322, 1036, 10007, 9131, 2630, 3873, 1586, 1501, 2452, 4560, 3962, 1879, 5422, 1337, 2094, 9208, 3944, 1411, 7427, 3883, 19540, 2612, 4094, 1024, 1469, 7272, 365, 2744, 1369, 138, 2889, 1823, 1190, 1168, 2295, 2306, 3053, 4450, 3345, 760, 4005, 2341, 101, 2336, 3008, 2109, 23401, 1454, 2031, 1633, 2778, 2072, 11372, 8097, 3661, 38204, 20014, 4062, 3399, 1995, 2263, 6544, 3930, 479, 4899, 3798, 1105, 347, 3666, 4729, 4022, 1729, 3434, 3167, 13783, 9754, 28241, 2412, 2876, 20300, 12109, 2921, 3477, 3698, 333, 628, 3979, 249, 11164, 7100, 2463, 7798, 3397, 12753, 3581, 1315, 3863, 2787, 11752, 5676, 518, 1850, 2499, 2427, 2480, 3969, 24005, 1982, 468, 2559, 3449, 32770, 10081, 6074, 2544, 5999, 5778, 0, 3927, ...
scala> freePorts: mutable.Set[Int] = Set(71, 109, 74, 149, 81, 196, 146, 172, 72, 195, 110)

scala> Set(71, 109, 74, 149, 81, 196, 146, 172, 72, 195, 110)

   */
}


{
  // EMPTY
  Nil

  List.empty

  List.empty[Int]

  Seq.empty[Int]

  Vector.empty[Int]

  Map.empty[String,String]

  /*
scala> res70: immutable.Nil.type = List()
scala> res71: List[Nothing] = List()
scala> res72: List[Int] = List()
scala> res73: mutable.Seq[Int] = ArrayBuffer()
scala> res74: immutable.Vector[Int] = Vector()
scala> res75: mutable.Map[String,String] = Map()

   */
}






// PART 3 -- COLLECTIONS IDIOMS

{
  //IDIOMS: PATTERN MATCHING
  val ingredients = List("Flour", "Sugar")
  val List(first, second) = ingredients

  val point  = (1, 2)
  val (x, y) = point

  val people = "Michael" :: "Sherlock" :: List()
  val pets   = "Spot" :: "Fluffy" :: Nil

  people match {
    case head :: rest => s"H ${head} R: ${rest}"
    case Nil => "X"
  }

  /*

scala> ingredients: List[String] = List(Flour, Sugar)
scala> first: String = Flour
second: String = Sugar

scala> point: (Int, Int) = (1,2)
scala> x: Int = 1
y: Int = 2

scala> people: List[String] = List(Michael, Sherlock)
scala> pets: List[String] = List(Spot, Fluffy)
scala> res76: String = H Michael R: List(Sherlock)

   */
}

{
  //IDIOMS:  FOR COMPREHNSIONS REVIEW
  for(person <- List("Matt", "Mark", "Luke")) println(person)
  for(pair <- Map("Cat" -> "Mammal", "Raven" -> "Bird")) println(pair)
  for((animal, kind) <-  Map("Dog" -> "Mammal", "Crow" -> "Bird"))  {
    println(s"$animal is a $kind")
  }

  // FOR COMPREHNSIONS AS TRANSFORMATIONS
  val simple = for(
    (animal, kind) <- Map("Dog" -> "Mammal", "Crow" -> "Bird")
  ) yield kind + ":" + animal

  /*

scala> Matt
Mark
Luke

scala> (Raven,Bird)
(Cat,Mammal)

scala> Dog is a Mammal
Crow is a Bird

scala>
scala> simple: mutable.Iterable[String] = ArrayBuffer(Mammal:Dog, Bird:Crow)

   */
}

{

  // ZIPPING
  val names = List("Michael", "Mark", "Tim")
  val cs = Array("Purple", "Green", "Blue")

  for((name, c) <- names.zip(cs)) println(s"$name is assigned $c")

  println(names zip cs)

  val nameColoursMap = names.zip(cs).toMap

  // with index
  for((letter, index) <- ('a' to 'd').zipWithIndex) {
    println(s"$letter is the ${index + 1}th letter of the english alphabet")
  }

  /*
scala> names: List[String] = List(Michael, Mark, Tim)
scala> cs: Array[String] = Array(Purple, Green, Blue)

scala> Michael is assigned Purple
Mark is assigned Green
Tim is assigned Blue

scala> List((Michael,Purple), (Mark,Green), (Tim,Blue))
scala> nameColoursMap: immutable.Map[String,String] = Map(Michael -> Purple, Mark -> Green, Tim -> Blue)

scala>
scala> a is the 1th letter of the english alphabet
b is the 2th letter of the english alphabet
c is the 3th letter of the english alphabet
d is the 4th letter of the english alphabet

   */
}

{
  //IDIOMS: TRAIT FACTORIES
  val orderedSupplies = Seq("Apertif", "Soup", "Starter", "Main Course",
    "Dessert", "Coffee", "Digestif")

  val randomSupplies = IndexedSeq("Food", "Water", "Shelter")

  randomSupplies
  orderedSupplies

  /*
scala> orderedSupplies: mutable.Seq[String] = ArrayBuffer(Apertif, Soup, Starter, Main Course, Dessert, Coffee, Digestif)
scala> randomSupplies: mutable.IndexedSeq[String] = ArrayBuffer(Food, Water, Shelter)
scala> res83: mutable.IndexedSeq[String] = ArrayBuffer(Food, Water, Shelter)
scala> res84: mutable.Seq[String] = ArrayBuffer(Apertif, Soup, Starter, Main Course, Dessert, Coffee, Digestif)
   */
}

{
  //IDIOMS: PROGRAMMING TO TRAITS

  def total(prices: Seq[Double]): Double = prices.sum
  def menu(ingr: Seq[String]) = for(i <- ingr) println(i)

  val lprices: Seq[Double] = List(2, 3, 4)
  val vprices: Seq[Double] = Vector(4, 5, 6)

  val aingr: Seq[String] = Array("Flour", "Sugar", "Milk")

  println(total(lprices))
  println(total(vprices))

  menu(aingr)

  /*
total: (prices: Seq[Double])Double

scala> menu: (ingr: Seq[String])Unit

scala> lprices: Seq[Double] = List(2.0, 3.0, 4.0)

scala> vprices: Seq[Double] = Vector(4.0, 5.0, 6.0)

scala> aingr: Seq[String] = WrappedArray(Flour, Sugar, Milk)

scala> 9.0

scala> 15.0

scala> Flour
Sugar
Milk
   */
}
