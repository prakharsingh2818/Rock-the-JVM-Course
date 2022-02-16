package lectures.part1basics

object Functions extends App {
  def aFunction(a: String, b: Int): String = a + " " + b
  println(aFunction("Hello", 3))

  // Parameterless Function
  def aParameterlessFunction(): Int = 99
  println(aParameterlessFunction())
  //println(aParameterlessFunction) //valid before Scala3

  def aRepeatedFunction(aString: String, n: Int): String = {//Return type necessary
    if(n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }
  println(aRepeatedFunction("Prakhar", 3))

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)


  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n-1)
  }

  def aGreetingFunction(name: String, age: Int): String = s"Hi, my name is $name and I am $age years old"
  def factorialFunction(number: Int): Int = {
    if(number <= 0) 1
    else number * factorialFunction(number - 1)
  }

  def fibonacciFunction(number: Int): Int = {
    if(number <= 2) 1
    else fibonacciFunction(number - 1) + fibonacciFunction(number - 2)
  }
  def checkPrimeFunction(number: Int): Boolean = {
    def iterate(num: Int, factors: Int): Boolean = {
      if(num == 1) {
        if(factors == 1) true
        else false
      }
      else {
        if(number%num == 0) iterate(num - 1, factors+1)
        else iterate(num - 1, factors)
      }
    }
    iterate(number, 0)
  }

  def anotherCheckPrimeFunction(number: Int): Boolean = {
    def primeUntil(num: Int): Boolean = {
      if(num <= 1) true
      else number%num != 0 && primeUntil(num - 1)
    }
    primeUntil(number/2)
  }

  //println(aGreetingFunction("Prakhar", 24))
  //println(factorialFunction(5))
  println(fibonacciFunction(8))
  //println(anotherCheckPrimeFunction(6))
}
