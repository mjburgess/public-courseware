package matching

object basic {
  println("Basic matching")                       //> Basic matching
  
  case class Foo(v: Int) { }
  
  new Foo(3) == new Foo(3)                        //> res0: Boolean = true
  
  def dayName(n: Int) = n match {
    	case 1 => "Monday"
    	case 2 => "Tuesday"
    	case 3 => "Wednesday"
    	case 4 => "Thursday"
    	case 5 => "Friday"
    	case 6 => "Saturday"
    	case 7 => "Sunday"
    	case _ => "???"
    }                                             //> dayName: (n: Int)String
    
    val s = dayName(3)                            //> s  : String = Wednesday

		def test(f: Foo) = f match {
    	case Foo(1) => "Value is 1"
    	case Foo(2) => "Value is 2"
    	case Foo(3) => "Value is 3"
      case _      => "Some other value"
		}                                 //> test: (f: matching.basic.Foo)String

	val s2 = test(new Foo(2))                 //> s2  : String = Value is 2
	val s3 = test(new Foo(7))                 //> s3  : String = Some other value
	
	def size(x:List[Any]) = x match {
  	case List() => "None"
    case List(_) => "One"        // match a list of one element
    case List(_,_) => "Two"      // match two elements
    case List(_,_,_) => "Three"  // three elements
    case _ => "Many"
  }                                               //> size: (x: List[Any])String
  
  val l1 = List(1, 2)                             //> l1  : List[Int] = List(1, 2)
  val sz = size(l1)                               //> sz  : String = Two
  val l2 = List(1, 2, 3, 4, 5)                    //> l2  : List[Int] = List(1, 2, 3, 4, 5)
  val sz2 = size(l2)                              //> sz2  : String = Many
  
  def mt(a: Any) = a match {
  	case i: Int => s"$i is an Int"
  	case s: String => s"$s is a String"
  	case _ => "something else"
  }                                               //> mt: (a: Any)String
  
  mt(3)                                           //> res1: String = 3 is an Int
  mt("foo")                                       //> res2: String = foo is a String
  mt(3.7)                                         //> res3: String = something else
}