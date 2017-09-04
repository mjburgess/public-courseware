trait Trueish[A] { self =>
  def asBoolean(a: A): Boolean
}

object Trueish {
  def defineInstance[A](f: A => Boolean): Trueish[A] = new Trueish[A] {
    def asBoolean(a: A): Boolean = f(a)
  }
}

trait TruOps[A] {
  def self: A

  implicit def tcInstance: Trueish[A]

  def trueish: Boolean = tcInstance.asBoolean(self)
}

import scala.language.implicitConversions

implicit def convertToTruOps[A](nonOps: A)(implicit trueishInstance : Trueish[A]) = new TruOps[A] {
  def self = nonOps
  implicit def tcInstance: Trueish[A] = trueishInstance
}

implicit val tcIntTrueish = Trueish.defineInstance[Int]({
    case 0 => false
    case _ => true
})

println(10.trueish)