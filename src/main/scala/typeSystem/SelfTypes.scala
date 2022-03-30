package typeSystem

object SelfTypes extends App{

  // a way of requireing a type to be mixed in

  trait InstrumentList{
    def play(): Unit
  }

  //must know how to play an instrument I want to enforce this at type level
  trait Singer{
    self: InstrumentList => // == whoever implements the singer trait must also implement the InstrumentalList

    def sing(): Unit
  }

  //example
  class LeadSinger extends Singer with InstrumentList{
    //this is a valid extension because together with singer there is also InstrumentList

    override def play(): Unit = ???
    override def sing(): Unit = ???

  }

  /* this does not compile even if I implement the method sing

  class Vocalist extends Singer{
    override def sing(): Unit = ???
  }
   */

  val jamesHetfields =  new Singer with InstrumentList{

    override def play(): Unit = ???
    override def sing(): Unit = ???

  }

  class Guitarist extends InstrumentList {
    override def play(): Unit = println("guitar solo")
  }

  val ericClapton = new Guitarist with Singer{ //also fine, Singer is happy because guitarist extends InstrumentalList
    override def sing(): Unit = ???
  }







  //WHY DO WE NEED THIS?
  // it helps to separate the concepts of Singer and InstrumentalList



  //vs Inerhitance
  class A
  class B extends A // B is an A

  trait T
  trait S {self: T =>} // S requires a T



  //CAKE PATTERN => "dependency injection"
  //because the depencencies create thinks like layers of abstraction

  //in java
  class Component{
    //API
  }
  class ComponentA extends Component
  class ComponentB extends Component
  class DependentComponent(val component: Component)
  //if spring these are injected by the framework at runtime (classical dependency injection)

  //CAKE PATTERN
  trait ScalaComponent{
    //API
    def action(x: Int): String
  }
  trait ScalaDependentComponent{
    self: ScalaComponent => //whoever implements scala dependent component must also implement some form of ScalaComponentg

    def dependentAction(x: Int): String =  action(x) + "this extension"
    // I can call action because the compile knows in advance this will be provided

  }

  //HOW WOULD YOU CREATE ABSTRACTION LAYERS
  //model how various element on frontend would look like on backend

  //layer 1 small components
  trait Picture extends ScalaComponent
  trait Stats extends ScalaComponent

  // layer 2 - compose (you can choose which components from the previous layer you want to mix in
  trait Profile extends ScalaDependentComponent with Picture
  trait Analytics extends ScalaDependentComponent with Stats

  //layer 3 - app
  trait ScalaApplication{
    self: ScalaDependentComponent =>
  }

  trait AnalyticsApp extends ScalaApplication with Analytics

  //dependency injection takes care to inject at runtime
  //cake pattern dependencies are checked at compile time



















  //cyclical dependencies
  //class X extends Y
  //class Y extends X
  // this do not compile


  //with self type this is possible
  trait X {self: Y =>}
  trait Y {self: X =>}
  //it seems a cicle reference but it is not like this
  //it just says: if we implement X we must also implement Y
  //              if we implement Y we must also implement X






}
