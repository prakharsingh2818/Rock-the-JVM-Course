package lectures.part1basics

object CallByNameCallByValue extends App {
  def calledByName(x: => Long): Unit = {
    println(s"Called by name 1 $x")
    Thread.sleep(100)
    println(s"Called by name 2 $x")
  }

  def calledByValue(x: Long): Unit = {
    println(s"Called by value 1 $x")
    Thread.sleep(100)
    println(s"Called by value 2 $x")
  }

  calledByName(System.currentTimeMillis())
  calledByValue(System.currentTimeMillis())

  def infinite(x: Int): Int = infinite(1) + 1

  def printFirst(x: Int, y: => Int): Unit = println(x)

  //printFirst(infinite(), 34) //Throws StackOverflowError
  printFirst(34, infinite(1)) //Runs Fine as call to the function is not made

  /**
   * Another example
   */
  def doSomething(): Int = {
    println("Doing Something...")
    1
  }

  def byValue(x: Int): Unit = {
    println(s"x1: $x")
    println(s"x2: $x")
  }
  def byName(x: => Int): Unit = {
    println(s"x1: $x")
    println(s"x2: $x")
  }
  println("_______CALL BY NAME_______")
  byName(doSomething())
  println("_______CALL BY VALUE_______")
  byValue(doSomething())
}
