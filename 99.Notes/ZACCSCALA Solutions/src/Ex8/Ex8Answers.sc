//1
def toInt(s: String): Option[Int] = {
  try {
    Some(Integer.parseInt(s))
  }
  catch {
    case e: Exception => None
  }
}

//2
val lst = List("1", "two", "4", "six", "10")

//3
val ints1 = lst map toInt
val ints = lst flatMap toInt

//4
val cities = Map("London" -> "UK", "Paris" -> "France", "Istanbul" -> "Turkey")
for ((k,v) <- cities) yield (v, k)

//5
val listOfNum = List(1,2,3,4,5)
listOfNum.foldLeft(0)((a: Int, i : Int) => a + i)

//6

//lunh algorithm

//take a list of numbers
val cc = List(4,5,5,6,7,3,7,5,8,6,8,9,9,8,5,5);
//remove the last one
//reverse it
val ccRev = cc.reverse
val ccRevNoHead = ccRev.tail //faster than iterating through and removing the last item
//double every other number
//if the number is above 10 subtract 9

def afterDouble =
  for (i <- 0 to ccRevNoHead.length -1)
    yield doSum(i, (ccRevNoHead.drop(i)).head)

def doSum(i : Int, x: Int) = {
  if (i % 2 == 0)
    if (x * 2 > 9) (x*2)-9 else x*2
  else x
}
//sum them
def sum = afterDouble.foldLeft(0)((a: Int, x: Int) => a + x)
//mod 10
def mod10 = sum % 10
//if output = last digit then its valid
if (mod10 == ccRev.head) println("valid") else println("not valid")


//7
class Employee(val name: String, val reports: Employee*)
//8
val lara = new Employee("Lara")
val bob = new Employee("Bob")
val julie = new Employee("Julie", lara, bob)
val jane = new Employee("Jane", julie)
val persons = List(lara, bob, julie, jane)
//9
val fm1 = persons filter (p => !p.reports.isEmpty)
//10
fm1 flatMap (p => (p.reports map (c => (p.name, c.name))))
