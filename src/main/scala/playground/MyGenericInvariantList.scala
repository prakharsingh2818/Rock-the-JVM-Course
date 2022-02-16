package playground

trait MyGenericInvariantList[A] {
  def head: A

  def tail: MyGenericInvariantList[A]

  def isEmpty: Boolean

  //def add(element: A): MyGenericInvariantList[A]

  def printElements(accumulator: String = ""): String
}

object InvariantEmpty extends MyGenericInvariantList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: Nothing = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  //override def add[A](element: A): MyGenericInvariantList[A] = new InvariantNode[A](element, InvariantEmpty)

  override def printElements(accumulator: String): String = s"$accumulator"+"NULL"
}

class InvariantNode[A](info: A, next: MyGenericInvariantList[A] = InvariantEmpty) extends MyGenericInvariantList[A] {
  override def head: A = info

  override def tail: MyGenericInvariantList[A] = next

  //override def add(element: A): MyGenericInvariantList[A] = new InvariantNode[A](element, this)

  override def isEmpty: Boolean = false

  override def printElements(accumulator: String): String = {
    if(this.isEmpty) this.printElements(s"$accumulator ---> ")
    else tail.printElements(s"$accumulator$info ---> ")
  }
}

object Tester extends App {
  /*val emptyList = InvariantEmpty
  val listWithAnElement = new InvariantNode[Int](3)
  val listWithTwoElements = listWithAnElement.add(4)
  println(emptyList.printElements())
  println(listWithAnElement.printElements())
  println(listWithTwoElements.printElements())*/
}
