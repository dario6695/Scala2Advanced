package typeSystem

object StructuralType extends App {

  type JavaClosable = java.io.Closeable

  class HipsterClosable {

    def close(): Unit = println("yeaImclosing")
    def closeSilently: Unit = println("no sound maaan")
  }

  //I wont to accept both my closable and the standard one without duplicating the code

  //def closeQuietly(closeable: JavaClosable OR HipsterClosable)

  //-> solution
  type UnifiedCloseable = {
    def close(): Unit
  }// STRUCTURAL TYPE, it may have variables and method, I can now write
  def closeQuietly(unifiedCloseable: UnifiedCloseable): Unit = unifiedCloseable.close()

  closeQuietly(new JavaClosable{
    override def close(): Unit = ???
  })
  closeQuietly(new HipsterClosable)


  //we can define TYPE REFINEMENTS
  //example
  type AdvancedCloseable = JavaClosable {
    //add new method
    def closeSilently(): Unit
  }// the type is enriched

  class AdvancedJavaCloseable extends JavaClosable {
    override def close(): Unit = print("java closes")
    def closeSilently(): Unit = print("java closes silently")
  }//this class is a substitution of the type alyas above


  def closeShh(advancedCloseable: AdvancedCloseable): Unit = advancedCloseable.closeSilently()

  closeShh(new AdvancedJavaCloseable) //the compiler undestands that advancedJavaCloseable
  //originates from AdvanceCloseable and also has the closeSilently

  //closeShh(new HipsterClosable)// HipsterClosable do not originates from AdvancedClosable even if it has all the methods
















  //using structural types as standalone types
  def alternativeClose(closeable: { def close(): Unit}): Unit = closeable.close()


  // type-checking => duck typing (used in python)

  type SoundMaker = {
    def makeSound(): Unit
  }

  class Dog {
    def makeSound(): Unit = println("bark")
  }

  class Car {
    def makeSound(): Unit = println("wroom")
  }

  //both define to the structure defined by SoundMaker
  val dog: SoundMaker = new Dog
  //the compiler is fine with this until the type in the right type conform to the one on the left
  //=> static duck typing (if something swim, fly, walk like a duck, I can treat it as a Duck)
  //usually present on dinamcly typed languages like python
  //SCALA COMPILER DO THIS AT COMPILE => TIME SUPER COOL

  //CAVEAT: based on reflection (this is how the compiler guarantees the types compatibilities at compiletime)
  //reflection has a big cost on performances


  //exercise
  trait CBL[+T] {
    def head: T
    def tail: CBL[T]
  }

  class Human{
    def head: Brain =  new Brain
  }

  class Brain{
    override def toString: String = "Brain"
  }

  //
  def f[T](somethingWithAHead: { def head:T }): Unit = println(somethingWithAHead.head)

  /*
  f is compatible with CBL and with HUMAN? YES
   */


  //-> be very carefull when using structural types in context of type parameters


}








