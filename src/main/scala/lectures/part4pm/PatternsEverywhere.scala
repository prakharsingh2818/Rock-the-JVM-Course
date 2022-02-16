package lectures.part4pm

object PatternsEverywhere extends App {

  // 1
  try {
    // code
  } catch {
    case e: RuntimeException => "Runtime"
    case npe: NullPointerException => "npe"
    case _ => "Something else"
  }

  // catches are matches
  /*try {

  } catch (e) {
    e match {
      case e: RuntimeException => "Runtime"
      case npe: NullPointerException => "npe"
      case _ => "Something else"
    }
  }*/

  // 2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second

  // 3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(s"$a $b $c")

  val head :: tail = list
  println(head)
  println(tail)

  // 4
  // partial function
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is Even"
    case 1 => "The One"
    case _ => "Something else"
  } // partial function literal
  val mappedList2 = list.map { x => x match {
      case v if v % 2 == 0 => v + " is Even"
      case 1 => "The One"
      case _ => "Something else"
    }
  }
  println(mappedList)
}
