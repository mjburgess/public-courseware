package matching

object xml {
  println("Using matching with XML")              //> Using matching with XML
  
  val x1 = <Foo/>                                 //> x1  : scala.xml.Elem = <Foo/>
  val x2 = <Foo></Foo>                            //> x2  : scala.xml.Elem = <Foo></Foo>
  val x3 = <Foo>bar</Foo>                         //> x3  : scala.xml.Elem = <Foo>bar</Foo>
  val x4 = <Foo><Bar/></Foo>                      //> x4  : scala.xml.Elem = <Foo><Bar/></Foo>
  val x5 = <Foo>aaa<Bar/></Foo>                   //> x5  : scala.xml.Elem = <Foo>aaa<Bar/></Foo>
  
  // Match on empty elements
  def mt1(e: scala.xml.Elem) = e match {
  	case <Foo/> => "empty"
  	case _ => "not empty"
  }                                               //> mt1: (e: scala.xml.Elem)String
  
  mt1(x1)                                         //> res0: String = empty
  val xx = List(x1, x2, x3, x4, x5)               //> xx  : List[scala.xml.Elem] = List(<Foo/>, <Foo></Foo>, <Foo>bar</Foo>, <Foo>
                                                  //| <Bar/></Foo>, <Foo>aaa<Bar/></Foo>)
  
  xx foreach(x => println(mt1(x)))                //> empty
                                                  //| empty
                                                  //| not empty
                                                  //| not empty
                                                  //| not empty
  // Match on different numbers of child elements
  def mt2(e: scala.xml.Elem) = e match {
  	case <Foo>{c}</Foo> => s"1: $c"
  	case <Foo>{c1}{c2}</Foo> => s"2: $c1, $c2"
  	case _ => "other"
  }                                               //> mt2: (e: scala.xml.Elem)String

  xx foreach(x => println(mt2(x)))                //> other
                                                  //| other
                                                  //| 1: bar
                                                  //| 1: <Bar/>
                                                  //| 2: aaa, <Bar/>
  // match on everything, and process it
  def mt3(e: scala.xml.Elem) = e match {
  	case <Foo>{c @ _*}</Foo> => for (o <- c) print(o + ", ")
  	case _ => "not empty"
  }                                               //> mt3: (e: scala.xml.Elem)Any

  xx foreach(x => println(mt3(x)))                //> ()
                                                  //| ()
                                                  //| bar, ()
                                                  //| <Bar/>, ()
                                                  //| aaa, <Bar/>, ()
}