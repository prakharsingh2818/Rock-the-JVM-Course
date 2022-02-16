package lectures.part3fp

object MapFlatMapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  println(chars.flatMap(x => numbers.map(n =>x +  n.toString)))
  val colors = List("black", "white")
  println(numbers.flatMap(x => chars.flatMap(y => colors.map(z => x.toString + y + z))))

  //foreach
  list.foreach(print)
  println()

  //for-comprehensions
  val forCombinations = for {
    a <- numbers
    b <- chars
    c <- colors
  } yield a + b.toString + c

  println(forCombinations)

  val forEvenCombinations = for {
    a <- numbers if a % 2 == 0
    b <- chars
    c <- colors
  } yield a + b.toString + c
  println(forEvenCombinations)

  val evenCombinations = numbers.filter(_ % 2 == 0).flatMap(a => chars.flatMap(b => colors.map(c => a + b.toString + c)))
  println(evenCombinations)

  val equivalentForEach =  for {
    a <- numbers
  } print(a)

  // syntax overload
  list.map { x =>
    x * 2
  }



}
