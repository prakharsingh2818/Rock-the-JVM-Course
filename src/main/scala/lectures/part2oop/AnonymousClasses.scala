package lectures.part2oop

object AnonymousClasses extends App {
  abstract class Animal {
    def eat: Unit
  }
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("Hahahahaha")
  }
  println(funnyAnimal.getClass)

  /*
   Equivalent to
   class AnonymousClasses$$anon$1 {
    override def eat: Unit = println("Hahahahaha")
   }
   val funnyAnimal: Animal =  new AnonymousClasses$$anon$1
   */

  // Anonymous classes work with both abstract and concrete classes and Traits
  // Anonymous classes of traits and abstract classes must implement abstract fields and methods
  class Person(val name: String) {
    def sayHi: Unit = println(s"Hi my name is $name. How can I help?")
  }
  val jim = new Person("Jim") {
    override def sayHi: Unit = // super.sayHi
     println(s"Hi my name is $name. How can I help?")
  }
  new Person("Prakhar").sayHi
  jim.sayHi
}
