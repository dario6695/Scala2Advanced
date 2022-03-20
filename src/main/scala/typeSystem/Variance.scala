package typeSystem



object Variance extends App {


  trait Animal

  class Dog extends  Animal
  class Cat extends Animal
  class Crocodile extends Animal

  //what is variance
  //"inerithance type substitution of generics"


  class Cage[T]
  //should a Cage[Cat] inherit from Cage[Animal]?
  //yes - covariance
  class CCage[+T]
  val ccage: CCage[Animal] = new CCage[Cat] //replacing a generic Cage di Animal con una di Cat


  // no - invariance
  class ICage[T]
  //val icage: ICage[Animal] =  new ICage[Cat]  //I can not do that



  //hellno - opposite - contravariance, type sobstitution works in the opposite sens
  class XCage[-T]
  val xcage: XCage[Cat] = new XCage[Animal] //@if it can contain any Animal, it can also contain a CAT


  class InvariantCage[T](animal: T)


  //covariant position

  class CovariantCage[+T](val animal: T) //COVARIANT POSITION: animal can be a field declared as a covariant type

  //if here I put some contravariant type then the compiler will complain
  //class ContravariantCage[-T](val animal: T)
  /*
  if this would compile I could write
    val catCate: XCage[Cat] =  new XCage[Animal](new Crocodile)
   */


  //class CovariantVariableCage[+T](var animal: T)
  //reason: types of vars are in contravariant position,
  //I could write something like
/*
  val ccage: CCage[Animal] =  new CCage[Cat](new Cat)
  ccage.animal = new Crocodile
(since the member can change)
 */

  //class ContravariantVariableCage[-T](var animal: T) //also in covariant position
  //for contravariance the same logic apply for val

  //for var members, only invariance is oko


  //trait AnotherCovariantCage[+T] {

   // def addAnimal(animal: T) //method argoument are in contravariant position

  //}
  /*
  otherwhise I could write
  val ccage: CCage[Animal] =  new CCage[Dog]
  ccage.add(new Cat)

   */


  class AnotherCovariantCage[-T]{
     def addAnimal(animal: T) = true
  }
  val acc: AnotherCovariantCage[Cat] = new AnotherCovariantCage[Animal]

  //acc.addAnimal(new Dog) //the compiler will rise an error
  acc.addAnimal(new Cat)
  class Kitty extends Cat
  acc.addAnimal(new Kitty)


  //How to add element to a covariant collection?
  class MyList[+A] {
    def add[B >: A](element: B): MyList[B] =  new MyList[B]
  }
  //this is what we could want
  val emptyList: MyList[Kitty] = new MyList[Kitty]
  val animals = emptyList.add(new Kitty)
  //if we want to add more animals
  //the compiler is forced to extend the type, the list become of Cats
  val moreAnimals: MyList[Cat] = animals.add(new Cat) //ok because cat is supertype fo kytty so conform with B
  //the compiler is forced to extend the type, the list become of Animals
  val evenmore: MyList[Animal] = moreAnimals.add(new Dog) //ok because Dog is an animal







  // METHOD ARGUMENTS (return type) ARE IN CONTRAVARIANT POSITION
  class PetShop[-T] {
    //def get(isItaPuppy: Boolean): T //method return types are in covariant position

    def get[S <: T](isItaPuppt: Boolean, defaultAnimal: S): S = defaultAnimal
  }

  val shop: PetShop[Dog] = new PetShop[Animal]
  //val evilCat = shop.get(true, new Cat)// not working, Cats does not extend dog, so call is illegal
  class Bobby extends Dog
  val doggy = shop.get(true, new Bobby)




  /* BIG RULE
  - method argouments are in CONTRAVARIANT position
  - return types are in COVARIANT position
   */


  //EXERCISES

  /**
   * INVARIANT, COVARIANT, CONTRAVARIANT version of these API
   * Parking[T](things: List[T]) {
   *
     * park(vehicle: T)
     * impound(vehicle: List[T])
     * checkVehicles(conditions: String): List[T]
   * }
   */



}






