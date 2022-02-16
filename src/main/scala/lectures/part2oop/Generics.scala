package lectures.part2oop

object Generics extends App {

  // GENERIC CLASSES/TRAITS
  class MyList[+A] {
  // use the type A
    def add[B >: A](element: B): MyList[B] = ???

    /**
     * A = Cat
     * B = Dog =  Animal
     * val catList: MyList[Animal] = new MyList[Cat]
     * catList.add(new Dog) // This will pollute the list
     * Solution is to use upper bonded type
     * Since B is an Animal so in this case the list returned will be a list of animals
     */
  }

  class MyMap[Key,Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // GENERIC METHODS
  // objects cannot be type parameterised
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // VARIANCE PROBLEM

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  /**
   * Q) Does the list of Cats extends List of Animals
   * A) 1) Yes ---> List[Cat] extends List[Animal] ---> COVARINCE
   */
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  //animalList.add(new Dog)

  /**
   * A) 2) No ---> INVARIANCE
   */
  class InvariantList[A]
  // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] //Does not work
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]


  /**
   * A) 3) Absolutely No ---> CONTRAVARIANCE
   */
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal] //Trainer of Animals is better as it can train all animals

  //BOUNDED TYPES

  /**
   * Upper Bonded Type
   */
  class Cage[A <: Animal](animal:  A) //Class Cage only accepts Type parameters A that are subtypes of Animal \
  val cage = new Cage(new Dog)

  class Car
  //val newCage = new Cage(new Car) //Won't work // Generic types needs proper bounded type

  /**
   * Lower Bonded Type
   */
   // class Cage[A >: Animal](animal: A)  //Cage only accepts something that is a supertype of Animal


}
