package part2

object PartialFunctions extends App{

  //classical partial function

  val aFunction = (x: Int) => x + 1

  //acept only 1, 2
  //bad implementation, it could be
  val aFussyFunction = (x: Int) =>
    if(x == 1) 42
    else if (x == 2) 56

  val betterFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
  }

  //domain
  //{1,2} => Int

  //scala support this, better to specify the domain and the codomain
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
  }

  //partial function utilizzation:
  //verify if a partial function is applicable without crushing the program
  println(aPartialFunction.isDefinedAt(78))

  //lift
  val lifted = aPartialFunction.lift //Int => Option[Int]
  println(lifted(2))  // => Some(56)
  println(lifted(78)) // => None


  //exercise

  //anonymus istance of the partial function trait
  val aPartialFunctionForExercise = new PartialFunction[Int, Int] {

    override def apply(v1: Int): Int = v1 match{
      case 1 => 42
      case 2 => 56
    }

    override def isDefinedAt(v1: Int): Boolean = v1 match{
      case 1 => true
      case 2 => true
      case _ => false
    }
  }


}
