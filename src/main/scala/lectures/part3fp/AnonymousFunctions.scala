package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (lambda)
  val doubler: Int => Int = (x: Int) => x * 2
  val tripler: Int => Int = x => x * 3
  val quadrapler = (x: Int) => x * 4

  // Multiple parameters in lambda

  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // No parameters in Lambda

  val justDoSomething: () => Int = () => 3

  println(justDoSomething) // Function itself
  println(justDoSomething()) // Actual function call

  // Curly braces in lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /**
   * 1. Replace all FunctionX calls with Lambdas in MyList
   * 2. Rewrite the special adder as an anonymous function
   */
  
  val superAdder = (numberOne: Int) => (numberTwo: Int) => numberOne + numberTwo
}
