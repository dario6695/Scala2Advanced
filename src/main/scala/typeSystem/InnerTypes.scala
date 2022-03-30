package typeSystem

import typeSystem.InnerTypes.o

object InnerTypes extends App {

  class Outer{
    class Inner
    object InnerObject
    type InnerType

    def print(i: Inner) = println(i)
    def printGeneral(i: Outer#Inner) = println()
  }

  def aMethod: Int = {
    class HelperClass
    //I can define everything inside a functionexcept type, i can only if they are type alias
    //type HelperType
    type HelperType = String
    2
  }

  //per-instance
  val o = new Outer
  val inner: o.Inner = new o.Inner // o.Inner is a TYPE

  val oo = new Outer
  //oo.Inner != o.Inner
  //val otherInner: oo.Inner = new o.Inner

  o.print(inner)
  // oo.print(inner)

  // this above is path-dependent type





  //common superclass: OuterInner


}
