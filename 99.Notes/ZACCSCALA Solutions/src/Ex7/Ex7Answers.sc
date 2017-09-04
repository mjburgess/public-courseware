

//1
val list = List(1,2,3,4,5,6)
//2
import scala.collection.mutable.ArrayBuffer
val array = ArrayBuffer[Int] ()
for (i <- 1 to 10) array += i
println(array)
//3
val listOfWords = "this" :: "is" :: "sparta" :: Nil

//4
val bothLists = list ++ array

//5
def nthItem[A](list : List[A], position: Int):A = {
  def nthItemInternal[A](listInt : List[A], currentPosition : Int): A = {
    if (currentPosition == 0) return listInt.head
    else nthItemInternal(listInt.tail, currentPosition-1)
  }

  nthItemInternal(list, position)
}

nthItem(list, 4)

//6
def positionInList[A](list : List[A], x: A): Int ={
  def position[A](list: List[A], x : A, currentPosition: Int): Int = {
    if (list.head == x) return currentPosition
    else position(list.tail, x, currentPosition + 1)
  }

  position(list, x, 0)
}

val listOfStrings = List("a","b","c","d","e","f","g","h","u","a","b","c","q")

positionInList(listOfStrings, "u");

//7
def dropNth[A](xs: List[A], n: Int) = {
  val (l1, l2) = xs splitAt n
  l1 ++ (l2 drop 1)
}

//or a recursive version
def dropNthR[A](xs: List[A], n: Int): List[A] = {
  def dropNthRInternal[A](xs: List[A], n:Int, current: Int, otherList: List[A]): List[A] = {
    if (current == n) otherList.reverse ++ xs.tail
    else dropNthRInternal(xs.tail, n, current+1, xs.head :: otherList)
  }

  dropNthRInternal[A](xs, n, 0, List())
}

val list2 = List("a","b","c","d");
dropNthR(list2, 1)
//8
def values(fn: Int => Int, min: Int, max: Int) = {
  for (x <- min to max) yield(x, fn(x))
}

values(x => x * 2, -2, 2)

//9
class Person(val id: Int, val name: String, val age: Int, val gender: Option[String]){
  override def toString = "Person: [" + id + ", " + name + ", " + age + ", " + gender + "]"
}

//10
val person1 = new Person(1, "fred", 45, Some("M"))
val person2 = new Person(2, "alice", 23, Some("F"))
val person3 = new Person(3, "", 33, None)
object Employees {
  //11
  val users: Map[Int, Person] = Map(
    1 -> person1,
    2 -> person2,
    3 -> person3)

  //12
  for((key, value) <- users) println(key + ": " + value)

  //13
  def findById(id: Int): Option[Person] = users.get(id)

  def findAll = users.values
}
val e1 = Employees.findById(1)
if (e1.isDefined) println(e1.get.name)
else println("No such ID")
val e2 = Employees.findById(11)
if (e2.isDefined) println(e2.get.name)
else println("No such ID")
//14
val in = new java.util.Scanner(new java.io.File("/Users/kat/Dropbox/IdeaProjects/test/src/Ex7/input.txt"))
while (in.hasNext)
  process(in.next)
val m = scala.collection.mutable.HashMap[String, Int]();
def process(in : String) {
  val option = m.getOrElse(in, 0);
  m += (in -> (option +1))
}


//immutable version
val in2 = new java.util.Scanner(new java.io.File("/Users/kat/Dropbox/IdeaProjects/test/src/Ex7/input.txt"))
def processFile(in : java.util.Scanner): Map[String, Int] = {
  def func(in: java.util.Scanner, lastMap: Map[String, Int]): Map[String, Int] = {
    if (in.hasNext()) {
      val str = in.next()
      def newMap = lastMap.updated(str, lastMap.getOrElse(str, 0) + 1)
      func(in, newMap)
    } else {
      lastMap
    }
  }

  val newMap = Map("" -> 0)
  func(in, newMap)
}
val out = processFile(in2)

