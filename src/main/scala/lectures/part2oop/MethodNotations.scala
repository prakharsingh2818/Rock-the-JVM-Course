package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangsOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def - (person: Person): String = s"${this.name} and ${person.name} are not together"
    def + (nickname: String): Person = new Person(s"${this.name} ($nickname)", this.favoriteMovie)

    def unary_! : String = s"$name, what should I do?"
    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)
    def isAlive: Boolean = true
    def learns(subject: String): String = s"${this.name} learns $subject"
    def learnsScala: String = learns("Scala")

    def apply() : String = s"Hi, My name is $name and I like \"$favoriteMovie\""
    def apply(count:Int): String = s"${this.name} has watched ${this.favoriteMovie} $count times"
  }

  val prakhar = new Person("Prakhar", "The Lunchbox")
  println(prakhar.likes("The Lunchbox"))
  println(prakhar likes "The Lunchbox") //Infix notation or operator notation
  // works only with methods with only one parameter

  //"Operators" in Scala
  val gaurav = new Person("Gaurav", "Udaan")
  println(prakhar hangsOutWith gaurav)
  val bhavya = new Person("Bhavya", "Satyamev Jayate")
  println(prakhar - bhavya)
  //ALL OPERATORS ARE METHODS IN JAVA

  //Prefix notation
  val x = -1 //equivalent to 1.unary_-
  //unary_ prefix works only with - = ~ !
  println(!prakhar)
  println(prakhar.unary_!)

  //Postfix notation
  println(prakhar.isAlive)
  println(prakhar isAlive) //methods which do not receive any parameters can be used in postfix notation

  //apply
  println(prakhar.apply())
  println(prakhar())


  //EXERCISES
  //EXERCISE 1
  val prakharWithNickname = prakhar + "The Cinephile"
  println(prakharWithNickname.name)

  //EXERCISE 2
  val rishabh = new Person("Rishabh", "Tamasha", 25)
  val agedRishabh = +rishabh
  println(s"Rishabh was ${rishabh.age} years old but now he is ${agedRishabh.age} years old")

  //EXERCISE 3
  println(prakhar learnsScala)

  //EXERCISE 4
  println(prakhar (3))

}
