package collections

class A { }

class B extends A { }

class C { }

object generics {
  println("Generics and variance")                //> Generics and variance
  
  def upperTypeBound[B <: A](x: B): A = x         //> upperTypeBound: [B <: collections.A](x: B)collections.A
  
  val b = new B                                   //> b  : collections.B = collections.B@7c34cfe
  val c = new C                                   //> c  : collections.C = collections.C@4ba66ed5
  
  upperTypeBound(b)                               //> res0: collections.A = collections.B@7c34cfe
  // upperTypeBound(c)
}