package playground

trait MyCovariantTransformer[-A, B] {
  def transform(element: A): B
}

trait MyGenericCovariantList[+A] {
  def head: A
  def tail: MyGenericCovariantList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyGenericCovariantList[B]
  def printElements(accumulator: String = ""): String
  def map[B >: A](transformer: MyCovariantTransformer[A, B]): MyGenericCovariantList[B]
  //def filter[B >: A](f: B => Boolean): MyGenericCovariantList[B]
  def filter(f: A => Boolean): MyGenericCovariantList[A]
  def ++[B >: A](list: MyGenericCovariantList[B]): MyGenericCovariantList[B]
  def flatMap[B >: A](transformer: MyCovariantTransformer[A, MyGenericCovariantList[B]]): MyGenericCovariantList[B]
}

object CovariantEmpty extends MyGenericCovariantList[Nothing]{
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyGenericCovariantList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyGenericCovariantList[B] = new CovariantNode[B](element, CovariantEmpty)

  override def printElements(accumulator: String = ""): String = s"$accumulator" + "NULL"

  //override def filter[B >: Nothing](f: B => Boolean): MyGenericCovariantList[B] = CovariantEmpty
  override def filter(f: Nothing => Boolean): MyGenericCovariantList[Nothing] = CovariantEmpty

  override def map[B >: Nothing](transformer: MyCovariantTransformer[Nothing, B]): MyGenericCovariantList[B] = CovariantEmpty

  override def ++[B >: Nothing](list: MyGenericCovariantList[B]): MyGenericCovariantList[B] = list

  override def flatMap[B >: Nothing](transformer: MyCovariantTransformer[Nothing, MyGenericCovariantList[B]]): MyGenericCovariantList[B] = CovariantEmpty
}

class CovariantNode[A](info: A, next: MyGenericCovariantList[A] = CovariantEmpty) extends MyGenericCovariantList[A] {
  override def head: A = info

  override def tail: MyGenericCovariantList[A] = next

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyGenericCovariantList[B] = new CovariantNode[B](element, this)

  override def printElements(accumulator: String = ""): String = {
    if(this == CovariantEmpty) this.printElements(accumulator + "---> ")
    else tail.printElements(s"$accumulator$head ---> " )
  }

  /*override def filter[B >: A](f: B => Boolean): MyGenericCovariantList[B] = {
    if(f(head)) new CovariantNode[B](head, tail.filter(f))
    else tail.filter(f)
  }*/

  override def filter(f: A => Boolean): MyGenericCovariantList[A] = {
    if(f(head)) new CovariantNode[A](head, tail.filter(f))
    else tail.filter(f)
  }

  override def map[B >: A](transformer: MyCovariantTransformer[A, B]): MyGenericCovariantList[B] = {
    new CovariantNode[B](transformer.transform(head), tail.map(transformer))
  }

  override def ++[B >: A](list: MyGenericCovariantList[B]): MyGenericCovariantList[B] = new CovariantNode[B](head, tail ++ list)

  override def flatMap[B >: A](transformer: MyCovariantTransformer[A, MyGenericCovariantList[B]]): MyGenericCovariantList[B] = {
    transformer.transform(head) ++ tail.flatMap(transformer)
  }
}
object CovariantTester extends App {
  val emptyList = CovariantEmpty
  val listWithAnElement = new CovariantNode[Int](4)
  val listWithTwoElements = listWithAnElement.add(3)
  val listWithThreeElements = listWithTwoElements.add(2)
  println(emptyList.printElements())
  println(listWithAnElement.printElements())
  println(listWithTwoElements.printElements())
  println(listWithThreeElements.printElements())

  val doubledList = listWithThreeElements.map(new MyCovariantTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  })

  println(doubledList.printElements())

  val filteredList = listWithThreeElements.filter(element => element % 2 == 0)
  println(filteredList.printElements())

  println(listWithThreeElements.flatMap(new MyCovariantTransformer[Int, MyGenericCovariantList[Int]] {
    override def transform(element: Int): MyGenericCovariantList[Int] = new CovariantNode[Int](element, new CovariantNode[Int](element * 2, CovariantEmpty))
  }).printElements())
}
