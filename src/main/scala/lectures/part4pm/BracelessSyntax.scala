package lectures.part4pm

object BracelessSyntax {
  //if expression
  val anIfExpression = if (2 > 3) "bigger" else "smaller"
  val anIfExpressionv2 =
    if (2 > 3) "bigger"
    else "smaller"

  //Scala 3
  val anIfExpressioniv3 =
    if 2 > 3 then
      "bigger" // must have higher indentation
    else
      "smaller"

  val anIfExpressionv4 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 - one liner
  val anIfExpressionv5 = if ( 2 > 3) then "bigger" else "smaller"

  // for comprehension
  val aForComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  val aForExpression_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  //pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "The One"
    case 2 => "Double or Nothing"
    case _ => "Something else"
  }

  // scala3 versioni
  val aPatternMatch_v2 = meaningOfLife match
    case 1 => "The One"
    case 2 => "Double or Nothing"
    case _ => "Something else"

  // methods
  def computeMeaningOfLife(arg: Int): Int = {
    val partialResult = arg
    partialResult + 3
  }
  // scala 3
  def computeMeaningOfLife_v2(arg: Int): Int =
    val partialResult = arg

    partialResult + 3

  // class definition (also for traits, objects, enums)
  class Animal: // Compiler now expects body
    def eat(): Unit = println("I am eating")

    def grow(): Unit = println("I am growing")
  end Animal //end is Optional. Can be used everywhere. Should be used if block has more than 4 lines of code

  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("I am special")


  def main(args: Array[String]): Unit = {
    println(anIfExpressionv4)
    println(aForComprehension)
    println(aForExpression_v2)
  }

}
