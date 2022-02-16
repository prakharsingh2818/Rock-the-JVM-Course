package lectures.part2oop

object Inheritance extends App {

  // single class inheritance
  class Animal {
    val creatureType = "wild"
    // no modifier means public
    def eat(): Unit = println("Nom Nom Nom!")
    private def privateMethod(): Unit = println("I cannot be called by child classes and can be called only inside the class")
    protected def protectedMethod(): Unit = println("I can be accesed only inside this class and sub classes")
  }

  class Cat extends Animal

  val cat = new Cat
  cat.eat()

  //Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  //class Adult(name: String, age: Int, idCard: String) extends Person(name, age) //Valid
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //overriding
  class Dog(override val creatureType: String) extends Animal {
    //override val creatureType: String = "domestic"
    override def eat(): Unit = {
      super.eat()
      println("Crunch Crunch!")
    }
  }
  val dog = new Dog("K9")
  dog.eat()
  println(dog.creatureType)

  //type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat()

  //super

  //preventing overrides
    // 1- Use final on member
    // 2- Use final n class
    // 3- using "sealed" before "class"  = extend class in this file only and prevents extension in other files


}
