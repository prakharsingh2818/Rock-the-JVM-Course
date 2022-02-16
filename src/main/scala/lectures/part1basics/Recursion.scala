package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(number: Int): Int = {
    if(number <= 1) 1
    else {
      println("factorial("+number+") = "+number+" * factorial("+(number-1)+")")
      val result = number * factorial(number - 1)
      println(s"factorial($number): $result")
      result
    }

  }
  factorial(10)

  /*
  def fibonacci(number: Int): Int =  {
    if(number <= 2) {
      println("Fibonacci("+number+") : Fibonacci("+(number-1)+") + Fibonacci("+(number-2)+")")
      val result = 1
      println(s"Fibonacci($number): $result")
      result
    }
    else {
      println("Fibonacci("+number+") : Fibonacci("+(number-1)+") + Fibonacci("+(number-2)+")")
      val result = fibonacci(number-1) + fibonacci(number-2)
      println(s"Fibonacci($number): $result")
      result
    }
  }
  println(fibonacci(5))
*/

  def tailRecursionFactorial(number: Int): BigInt = {
    @tailrec
    def calculateFactorial(number: Int, accumulator: BigInt): BigInt = {
      if(number <= 1) accumulator
      else calculateFactorial(number - 1, number*accumulator)
    }
    calculateFactorial(number, 1)
  }

  println(tailRecursionFactorial(10000))

  def stringConcatenator(message: String, count: Int): String = {
    @tailrec
    def concatenator(message: String, accumulator: String, count: Int): String = {
      if(count < 1) accumulator
      else concatenator(message, accumulator.concat(message), count - 1)
    }
    concatenator(message, "", count)
  }
  println(stringConcatenator("Prakhar", 3))

  def isPrimeTailRecursion(number: Int): Boolean = {
    @tailrec
    def isPrime(num: Int, result: Boolean): Boolean = {
      if(num <= 1) result
      else isPrime(num - 1, number%num != 0 && result)
    }
    isPrime(number/2, result = true)
  }
  println(isPrimeTailRecursion(13))

  def fibonacciTailRecursion(number: Int): Int = {
    @tailrec
    def calculateFibonacci(num: Int, last: Int, nextToLast: Int): Int = {
      if(num >= number) last
      else calculateFibonacci(num + 1, last + nextToLast, last)
    }
    if(number <= 2) 1
    else calculateFibonacci(2, 1, 1)
  }
}
