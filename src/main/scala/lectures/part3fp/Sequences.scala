package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(print)

  println()

  (1 to 10).foreach(x => println("Hello"))

  // Lists
  val aList = List(1, 2, 3)
  val prepended = 42 :: aList
  println(prepended)
  val newList = 42 +: aList :+ 89
  println(newList)

  val apples5 = List.fill(5)("Apple")
  println(apples5)

  println(aList.mkString("-|-"))

  // Arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements.foreach(print))

  // Mutation
  numbers(2) = 0 // syntax sugar  for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // Arrays and Sequences
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // Vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // Vectors v/s List

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating in middle takes long time
  println(getWriteTime(numbersList))

  // depth of tree is small
  // needs to replace an entire 32- element chunk
  println(getWriteTime(numbersVector))
}
