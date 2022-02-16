package lectures.part2oop

import com.sun.security.jgss.InquireSecContextPermission

object Exceptions extends App {
  val x: String = null
  //println(x.length) //This will crash with a null pointer exception

  //Throwing exception
  lazy val aWeirdValue = throw new NullPointerException

  // Throwable classes extend the Throwable class
  // Exception (deals with program) and Error (deals with the problems of system like StackOverflowError)
  // are the major throwable subtypes

  //Catching Exception
  def getInt(withException: Boolean): Int =
    if(withException) throw new RuntimeException("No oInt for you!!!")
    else 33


  // Data type will be AnyVal as try returns Int, catch returns Unit
  // If all catch cases return Int then the data type would have been Int
  val potentialFail = try {
    // code that will fail
    getInt(true)
  }
  catch {
    case e: RuntimeException => println("Caught a runtime exception")
  }
  finally {
    // Optional
    // Does not influence the return type of this expression so use it only for side effects
    println("Code that will get executed no matter what")
  }

  /**
   * Defining our own exception
   */
  class MyException extends Exception

  val exception = new MyException

  //throw  exception


  /**
   * EXERCISES
   * 1) Crash with Out of memory Error
   * 2) Crash with a StacOverflowError
   * 3) Pocket Calculator
   *    -> add(x,y)
   *    -> subtract(x,y)
   *    -> multiply(x,y)
   *    -> divide(x,y)
   *
   *    Throw a custom exception
   *      -> OverflowException if add(x,y) exceeds Int.MAX value
   *      -> UnderflowException if subtract(x,y) exceeds Int>MIN value
   *      -> MatchCalculationException for division by 0
   */

  // val array = Array.ofDim(Int.MaxValue) //Throws OutOfMemoryError
  def infinite: Int = 1 + infinite
  // val noLimit = infinite //Throws StackOverFlowError

  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception("Division By 0")

  object Calculator {
    def add(x: Int, y: Int): Int = {
      if(x > 0 && y > 0 && (x+y) < 0) throw new OverflowException
      else if (x < 0 && y < 0 && (x+y) > 0) throw new UnderflowException
      else x + y
    }

    def subtract(x: Int, y: Int): Int = {
      if(x > 0 && y < 0) throw  new UnderflowException
      else x - y
    }

    def multiply(x: Int, y: Int): Int = {
      if(x > 0 && y > 0 && x*y < 0) throw new OverflowException
      else if(x < 0 && y < 0 && x*y < 0) throw new OverflowException
      else if(x > 0 && y < 0 && x*y > 0) throw new UnderflowException
      else if(x < 0 && y > 0 && x*y > 0) throw new UnderflowException
      else x*y
    }

    def divide(x: Int, y: Int): Double = {
      if(y == 0) throw new MathCalculationException
      else x/y
    }
  }

  try {
    println(Calculator.add(10,20))
    println(Calculator.divide(10,0))
    println(Calculator.multiply(10,10))
    println(Calculator.subtract(10,10))
  }
  catch {
    case e1: OverflowException => println("Overflow Exception in add")
    case e2: UnderflowException => println("Underflow Exception in subtraction")
    case e3: MathCalculationException => println("MatchCalculationException in division")
  }

}
