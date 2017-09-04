package labs

object expr {
  println("Expression evaluation")                //> Expression evaluation
  
  trait Expression
  
  case class Const(v: Int) extends Expression
	case class Neg(e: Expression) extends Expression
	case class Add(l: Expression, r: Expression) extends Expression
	
	def eval(e: Expression): Int = e match {
  	case Const(c) => c
  	case Neg(ex) => - eval(ex)
  	case Add(l, r) => eval(l) + eval(r)
	}                                         //> eval: (e: labs.expr.Expression)Int

	val e1 = Add(Const(10), Neg(Add(Const(3), Const(4))))  // 10 + (-(3 + 4))
                                                  //> e1  : labs.expr.Add = Add(Const(10),Neg(Add(Const(3),Const(4))))

	val v = eval(e1)                          //> v  : Int = 3
	
}