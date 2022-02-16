package lectures.part1basics

object ValuesVariablesTypes extends App {

  val firstVal: Int = 24  // vals are immutable
  println(firstVal)

  val anIntVal = 42 // compiler can infer types

  val aString = "hello scala"
  val aBoolean = true
  val anInt = 5
  val aShort = 4512
  val aLong = 45698745214L
  val aFloat = 45.2f
  val aDouble = 22.14
  val aChar = 'c'


  //variables

  var aVariable: Int = 2
  aVariable  = 4
  // variables or vars can be reassigned
  //in functional programming we know this as side-effects i.e changing a variable
  //these are important to understand functioning of programme

}
