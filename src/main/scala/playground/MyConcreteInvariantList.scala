package playground

import playground.Empty.{head, tail}

trait MyPredicate {
  def test(element: Int): Boolean
}

trait MyTransformer {
  def transform(element: Int): Int
}

trait MyConcreteInvariantList {
  def head: Int

  def tail: MyConcreteInvariantList

  def add(element: Int): MyConcreteInvariantList

  def printElements(accumulator: String = ""): String

  def isEmpty: Boolean

  def map(f:Int => Int): MyConcreteInvariantList

  def filter(predicate: MyPredicate): MyConcreteInvariantList

  def ++(list: MyConcreteInvariantList): MyConcreteInvariantList

  def flatMap(f: Int => MyConcreteInvariantList): MyConcreteInvariantList
}

object Empty extends MyConcreteInvariantList {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyConcreteInvariantList = throw new NoSuchElementException

  override def add(element: Int): MyConcreteInvariantList = new Node(element, Empty)

  override def printElements(accumulator: String = ""): String = s"$accumulator" + "NULL"

  override def isEmpty: Boolean = true

  override def map(f: Int => Int): MyConcreteInvariantList = Empty

  override def filter(predicate: MyPredicate): MyConcreteInvariantList = Empty

  override def ++(list: MyConcreteInvariantList): MyConcreteInvariantList = list

  override def flatMap(f: Int => MyConcreteInvariantList): MyConcreteInvariantList = Empty
}

class Node(information: Int, next: MyConcreteInvariantList = Empty) extends MyConcreteInvariantList {
  override def head: Int = information

  override def tail: MyConcreteInvariantList = next

  override def add(element: Int): MyConcreteInvariantList = new Node(element, this)

  override def printElements(accumulator: String = ""): String = {
    if (this == Empty) printElements(s"$accumulator$information --->")
    else tail.printElements(s"$accumulator$information ---> ")
  }

  override def isEmpty: Boolean = false

  override def map(f: Int => Int): MyConcreteInvariantList = {
    new Node(f(head), tail.map(f))
  }

  override def filter(predicate: MyPredicate): MyConcreteInvariantList = {
    if(predicate.test(head)) new Node(head, tail.filter(predicate))
    else tail.filter(predicate)
  }


  /**
   * listOne ++ listTwo = [1, 2, Empty] ++ [3, 4, Empty] ===> [new Node(1, new Node(2, Empty)] ++ [new Node(3, new Node(4, Empty)]
   * [1, {[2, Empty] ++ [3, 4, Empty]} ------(1) ===> [new Node(1, {new Node(2, Empty) ++ [new Node(3, new Node(4, Empty)]}]
   * [2, {[Empty] ++ [3, 4, Empty]} ------(2) ===> [new Node(1, {new Node(2, {Empty ++ [new Node(3, new Node(4, Empty)]}}]
   * [3, 4, Empty] ------(3) ====> new Node(3, new Node(4, Empty) ===> [new Node(1, {new Node(2, new Node(3, new Node(4, Empty)}]
   * [2, 3, 4, Empty] -----(4) ===> new Node(1, new Node(2, new Node(3, new Node(4, Empty))))
   * [1, 2, 3, 4, Empty]
   */

  override def ++(list: MyConcreteInvariantList): MyConcreteInvariantList = new Node(head, tail ++ list)

  override def flatMap(f: Int => MyConcreteInvariantList): MyConcreteInvariantList = {
    f(head) ++ tail.flatMap(f)
  }
}

object InvariantTester extends App {
  val emptyList = Empty
  val listWithAnElement = new Node(4)
  val listWithTwoElements = listWithAnElement.add(3)
  val listWithThreeElements = listWithTwoElements.add(2)
  println(emptyList.printElements())
  println(listWithAnElement.printElements())
  println(listWithTwoElements.printElements())
  println(listWithThreeElements.printElements())

  val doubledList = listWithThreeElements.map(element => element * 2)
  println(doubledList.printElements())

  val evenList = listWithThreeElements.filter(new MyPredicate {
    override def test(element: Int): Boolean = element % 2 == 0
  })

  println(evenList.printElements())

  val listOne = new Node(1, new Node(2, Empty))
  val listTwo = new Node(3, new Node(4, Empty))
  println((listOne ++ listTwo).printElements())

  println(listWithThreeElements.flatMap(element => new Node(element, new Node(element * 2, Empty))).printElements())


}
