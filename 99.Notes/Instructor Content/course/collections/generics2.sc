package collections

object generics2 {
	trait Similar {
  		def isSimilar(x: Any): Boolean
	}

	case class MyInt(x: Int) extends Similar {
  		def isSimilar(m: Any): Boolean =
    		m.isInstanceOf[MyInt] &&
    		m.asInstanceOf[MyInt].x == x
	}
	
	// Upper type bound
  def findSimilar[T <: Similar](e: T, xs: List[T]): Boolean =
  		if (xs.isEmpty) false
   	else if (e.isSimilar(xs.head)) true
   	else findSimilar[T](e, xs.tail)           //> findSimilar: [T <: collections.generics2.Similar](e: T, xs: List[T])Boolean
  
  	val list: List[MyInt] = List(MyInt(1), MyInt(2), MyInt(3))
                                                  //> list  : List[collections.generics2.MyInt] = List(MyInt(1), MyInt(2), MyInt(3
                                                  //| ))
  	
  println(findSimilar(MyInt(4), list))            //> false
  println(findSimilar(MyInt(2), list))            //> true
  
  // Lower type bound
  // ListNode will accept T and subtypes of T
  case class ListNode[+T](h: T, t: ListNode[T]) {
		def head: T = h
		def tail: ListNode[T] = t
		// We can't just say prepend[T], because we have [+T] on ListNode
		// So we use a lower type bound to do the same thing for the parameter,
	  // saying that U can be a superclass of T
		def prepend[U >: T](elem: U): ListNode[U] = ListNode(elem, this)
  }
  
  val empty: ListNode[Null] = ListNode(null, null)//> empty  : collections.generics2.ListNode[Null] = ListNode(null,null)
  val strList: ListNode[String] = empty.prepend("hello")
                                       .prepend("world")
                                                  //> strList  : collections.generics2.ListNode[String] = ListNode(world,ListNode
                                                  //| (hello,ListNode(null,null)))
  // Prepend an Int to a list of Strings
  val anyList: ListNode[Any] = strList.prepend(12345)
                                                  //> anyList  : collections.generics2.ListNode[Any] = ListNode(12345,ListNode(wo
                                                  //| rld,ListNode(hello,ListNode(null,null))))
  // Apply a type bound to a type definition
  case class Foo[T <: Similar](o: T) {
		val obj = o
		
		def func() = o.isSimilar(3)
  }
}