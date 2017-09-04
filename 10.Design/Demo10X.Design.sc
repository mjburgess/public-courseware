
// NOTHINGNESS
/*
* `Null`: Trait
* null: instance of Null
* corresponds to java's null

* `Nil`: empty List
* used to build lists

* `Nothing` is a Trait -- subtype of every type.
* No instances
* Used in type signatures of empty objects (eg. empty lists)

* `None`: Object
* Subtype of Option.
* used when returning an Option
* and when no senisble value can be returned

* `Unit`: Class
* () -- single instance of Unit.
* used for action ("void") situations, eg. I/O
*/



//EXAMPLE OF ALGEBRA:

{
  // EXAMPLE: LOGICAL OPERATORS
  //sketch
  abstract class LogicExpr

  case class T() extends LogicExpr
  case class F() extends LogicExpr

  case class And(val l: LogicExpr, val r: LogicExpr) extends LogicExpr
  case class Or(val l: LogicExpr, val r: LogicExpr) extends LogicExpr

  val myX : LogicExpr = And(T(), And(F(), Or(T(), F())))

}

{
  // in full
  abstract class LogicExpr {
    def meaning: Boolean
  }

  case class T() extends LogicExpr {
    def meaning = true
  }

  case class F() extends LogicExpr {
    def meaning = false
  }

  case class And(val l: LogicExpr, val r: LogicExpr) extends LogicExpr {
    def meaning = l.meaning && r.meaning
  }

  case class Or(val l: LogicExpr, val r: LogicExpr) extends LogicExpr {
    def meaning = l.meaning || r.meaning
  }


  val myA : LogicExpr = And(T(), And(F(), Or(T(), F())))
  val myO : LogicExpr = Or(T(), And(F(), Or(T(), F())))

  println(myA.meaning)
}




{
  //PATH DEPENDENT TYPES
}



//ASIDE: STRUCTURAL TYPES

{
  // structural types ~= duck typing ~= ad hoc polym.
  def typed_speak(d: {def quack(value: String): String}) {
    println(d.quack("!"))
  }

  type Duck = { def quack(value: String): String }

  def speak(d: Duck) {
    println(d.quack("!"))
  }

  class Mallard {
    def quack(s: String) = "Quack" + s
  }

  class Doctor {
    def quack(s: String) = "Hello" + s
  }

  speak(new Mallard)
  speak(new Doctor)

  /*

  */
}

