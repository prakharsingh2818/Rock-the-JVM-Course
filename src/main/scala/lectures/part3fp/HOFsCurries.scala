package lectures.part3fp

import scala.annotation.tailrec

object HOFsCurries extends App {

  //HOF --> Takes an Integer and a Function as argument and returns a Function.
  // The argument Function takes a String and a Function, which takes and Int and returns Boolean,
  // as argument and returns an Int

  //  val superFunction: (Int, (String, Int => Boolean) => Int) => Int => Int = ???

  // Function that applies a function n times over a given value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))

  def nTimes(f: Int => Int, n: Int, x: Int): Int = if (n <= 0) x else nTimes(f, n - 1, f(x))

  println(nTimes(x => x * 2, 3, 2))

  def nTimesBetter(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plusOne: Int => Int = x => x + 1
  val incrementBy10 = nTimesBetter(plusOne, 10)(1)
  val anotherIncrementBy10 = nTimesBetter(plusOne, 10)
  println(incrementBy10)
  println(anotherIncrementBy10(1))

  val superAdder: Int => Int => Int = x => y => y + x
  println(superAdder(2)(3))
  val adder3 = superAdder(3)
  println(adder3(2))

  // Function with multiple parameter list

  def curriedFormatter(s: String)(d: Double): String = s.format(d)

  val standardFormat = curriedFormatter("%4.2f")
  val preciseFormat = curriedFormatter("%10.8f")
  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /**
   * PRACTICE
   */
  /*def mTimes: Function3[Function1[Int, Int], Int, Int, Int] = new Function3[Function1[Int, Int], Int, Int, Int] {
    override def apply(f: Int => Int, v2: Int, v3: Int): Int = {

      new Function1[Int, Int] {
        override def apply(): Int = v3 + 1
      }
    }
  }*/
  @tailrec
  def mTime(f: Int => Int, count: Int, operand: Int): Int =
    if (count <= 0) operand
    else mTime(f, count - 1, f(operand))

  def mTimesBetter(f: Int => Int, count: Int): Int => Int =
    if(count <= 0) (x: Int) => x
      else (x: Int) => mTimesBetter(f, count - 1)(f(x))

  def toCurry(f: (Int, Int) => Int): Int => Int => Int = x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = (x, y) => f(x)(y)

/*  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int = x => g(f(x))*/

  def compose[A, B, T](f: A => B, g: T => A): T => B = x => f(g(x))

  def andThen[A, B, T](f: A => B, g: B => T): A => T = x => g(f(x))

  def superAdderTwo: Int => Int => Int = toCurry(_ + _)

  def add4 = superAdderTwo(4)
  println(add4(17))

  def simpleAdder: (Int, Int) => Int = fromCurry(x => y => x + y)
  // def simpleAdder: (Int, Int) => Int = fromCurry(superAdderTwo)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))

}
