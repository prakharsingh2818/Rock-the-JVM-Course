package lectures.part4pm

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {
  // 1- Constants
  /*val x: Any = "Scala"
  val constants = x match {
    case 1 => "A Number"
    case "Scala" => "The Scala"
    case true => "The truth"
    case AllThePatterns => "A singleton object"
  }
  println(constants)
  // 2- Match anything
  //2.1 wildcards

  val matchAnything = x match {
    case _ =>
  }

  //2.2 variable
  val matchAVariable = x match {
    case something => s"I have $something"
  }

  // 3- Tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case(1, 1) =>
    case (something, 2) => s"I have found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }
  // PMs can be NESTED

  // 4- Case Classes
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Cons(h, t) => ???
    case Empty => ???
    case _ => ???
  }
  val anotherMatchAList = aList match {
    case Cons(h, Cons(subhead, subtail)) => ???
    case Empty => ???
    case _ => ???
  }

  // 5- List Patterns
  val aStandardList = List(1, 2, 3, 42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => //extractor
    case List(1, _*) => // list of arbitrary length
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 42 => // infix pattern
  }

  // 6- Type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => //explicit type specifier
  }

  //7- Name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => //name binding
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
  }

  //8- Multi Patterns
  val multiPattern = aList match {
    case Empty | Cons(0, _) => // multi pattern
  }

  // 9- if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
  }*/

  /**
   * Questions
   */
  val numbers = List(1, 2, 3) // Type Erasure
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "A list of Strings"
    case listOfNumbers: List[Int] => "A List of Numbers"
    case _ => ""
  }
  println(numbersMatch)
}