package lectures.part1basics

object Expressions extends App {

  // expressions
  val x = 1 + 2
  println(x)

  println(2 + 3 * 4)

  println(1 == x)

  println(!(1 == x))

  var aVariable = 2
  aVariable += 3 // also work with -= *= /=(these all are side effects)
  println(aVariable)

  // Instruction vs Expression
  /**
  * -instruction is something which you tell the computer to do eg: print or change the variable etc
  * -an expression is something that has a value and or a type ==> used in functional programming
  * i.e every single bit of your code will compute a value
  **/


  //IF expression
  val aCondition = true
  val aConditionValue = if(aCondition) 1 else -1
  println(aConditionValue)

  //Unit type
  val aUnitValue = (aVariable)
  println(aUnitValue)

  //Code block
  val aCodeBlock = {
    //Values will be visible inside the block only
    val y = 2
    val z = y + 1
    if(z > 2) "Hello!" else "Goodbye!"
  }
  println(aCodeBlock)

  val someValue = 2 < 3
  val someOtherValue = {
    if(someValue) 1 else 0
    33
  }
  println(someOtherValue)

  val check = {
    3+2
    2
  }
  println(check)
}
