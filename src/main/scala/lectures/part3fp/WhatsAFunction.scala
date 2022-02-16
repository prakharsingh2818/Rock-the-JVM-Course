package lectures.part3fp

object WhatsAFunction extends App {
  // use function as first class elements

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  // Function types is by default supported by Scala

  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /**
   * 1. Function to take 2 strings and concatenate them
   * 2. transform MyPredicate and MyTransformer into function types
   * 3. Define a function which takes an int as argument and returns another function that takes int and returns int
   *  - Define type of the function
   *  - Implement it
   */

  val stringConcatenation: Function2[String, String, String] = new Function2[String, String, String] {
    override def apply(stringOne: String, stringTwo: String): String = stringOne + stringTwo
  }
  println(stringConcatenation("Prakhar is ", "learning Scala!"))

  val increment: Int =>  Int = number => number + 1

  val hof: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(number: Int): Function1[Int, Int] = {
      val decrement: Int => Int = number => number - 1
      decrement
    }
  }
  val syntacticSugarHof: Int => Int => Int = number => num2 => number + num2

  val  specialAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(numberOne: Int): Function1[Int, Int] =  new Function1[Int, Int] {
      override def apply(numberTwo: Int): Int = numberOne + numberTwo
    }
  }

  val adder3 = specialAdder(3)
  println(adder3(4))
  println(specialAdder(3)(4))
}

trait MyFunction[A, B] {
  def apply(element: A): B = ???
}
