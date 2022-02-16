package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract members
  abstract class Animal {
    val creatureType: String = "Wild"
    def eat(): Unit
  }

  class Dog extends Animal {
    override val creatureType = "Canine" // override keyword not necessary if creatureType was not defined
    def eat(): Unit = println("Crunch Crunch!")
  }

  //Traits
  trait Carnivore {
    val preferredMeal: String = "Meat"
    def eat (animal: Animal): Unit

  }
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "Croc"
    override def eat(): Unit = println("Nom Nom!")
    override def eat(animal: Animal): Unit = println(s"I am a $creatureType and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  /***
   * Traits do not have constructor parameters
   * Multiple traits may be inherited by the same class
   * Traits = "behavior"; Abstract class = "thing"
   */
}
