package part1

object PatternMatch extends App{


  class NumberClass(n: Int)

  object even{
     def unapply(n: Int): Boolean = {
       if (n % 2 == 0)  true
       else false
     }
  }

  object singleDicit{
    def unapply(n: Int): Boolean = {
      if (n < 10)  true
      else false
    }
  }

  //exercise 1
  val n = 45
  val mathProperty = n match {
    case  singleDicit() => "single dicit"
    case even() => "even number"
    case _ => "no property"
  }

  println(mathProperty)
  //vantage: I can reuse these boolean tests in other pattern match, otherwhise it could no be optimap because code is more verbose

  //infix pattern
  val numbers = List(1)
  val descriprion = numbers match {
    case head::Nil => println(s"the only element is $head")
    case _ =>
  }

  //building our own

  //infix pattern
  case class Or[A, B](a: A, b: B)
  val either = Or(2, "two")

  val description = either match {
    case Or(number, string)  => s"$number or $string"
  }

  //decomposing sequences
  val varargs = numbers match {
    case List(1, _*) => "starting with 1"
  }

  println(varargs)

  trait MyList[A]{
    def head : A = ???
    def tail: MyList[A] = ???
  }

  /*/case object Empty extends MyList[Nothing]
  //case class Cons[+A](override  val head: A, override val tail : MyList[String]) extends MyList[A]

  object MyList{
    def unapplySeq[A](list:: MyList[A]): Option[Seq[A]]

  }

   */

  //without wrapper the below code would not compile, for matching like that, match: should be applied to a wrapper class
  //in most cases, Option should be enough
  abstract class Wraper[T]{
    def isEmpty: Boolean
    def get: T
  }

  case class Person(name: String)

  object PersonWrapper {

    def unapply(person: Person): Wraper[String] = new Wraper[String] {
      def isEmpty = false
      def get: String = person.name
    }

  }

  val bob = Person("Bob")

  //the compiler search the more appropriate unapply for us
  println(
    bob match {
      case PersonWrapper(n) => s"person name $n"
      case _ => "an alien"
    }
  )


}


//unapplay method : used to evaluate a field of a class suring a match:
//if a class has a companion object that has a method unapplay
// if the class is under a pattern matching the unapplay could be invoked to dicide the result


