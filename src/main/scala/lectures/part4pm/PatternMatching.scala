package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  val random = new Random()
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else"
  }
  println(x)
  println(description)

  //1. Decompose Values
  case class Person(name: String, age: Int)
  val prakhar = Person("Prakhar", 24)

  val greeting = prakhar match {
    case Person(n, a) => s"Hi my name is $n and I am $a years old"
    case _ => s"I don't know who I am"
  }

  val anotherGreeting = prakhar match {
    case Person(n, a) if a < 21 => s"Hi my name is $n and I can't drink in India"
    case _ => s"I don't know who I am"
  }
  println(anotherGreeting)

  /**
   * Cases are matched in order
   * MatchError in case of no match
   * type of Patten Match expression  => unified type of all the types in all the cases
   */

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(breed) => println(s"I am a $breed Dog")
    case Parrot(greeting) => println(s"Parrot says: $greeting")
    case _ => ???
  }

  trait Expr
  case class Number(a: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Product(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Product(e1, e2) => {
      def maybeShowParentheses(expr: Expr) = expr match {
        case Product(_, _) => show(expr)
        case Number(_) => show(expr)
        case _ => "(" + show(expr) + ")"
      }
      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
    }
  }

  println(show(Sum(Number(1), Number(2))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Product(Sum(Number(2), Number(3)), Number(5))))
  println(show(Sum(Product(Number(2), Number(3)), Number(5))))
}
