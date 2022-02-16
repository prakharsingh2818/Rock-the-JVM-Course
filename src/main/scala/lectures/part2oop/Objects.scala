package lectures.part2oop

object Objects extends App {
  class Person(val name: String)  {
    //Instance level functionality
  }
  object Person {
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    //FACTORY METHOD
    def from(mother: Person, father: Person): Person = new Person("Child")
    def apply(mother: Person, father:  Person): Person = new Person("Second Child")
  }

  val prakhar = new Person("Prakhar")
  val gaurav = Person
  val happy = Person
  val father = new Person("Father")
  val mother = new Person("Mother")
  val child = Person.from(father, mother)
  val secondChild = Person(mother, father)

  println(gaurav.N_EYES)
  println(Person.N_EYES)
  println(prakhar == gaurav)
  println(gaurav == happy)
}
