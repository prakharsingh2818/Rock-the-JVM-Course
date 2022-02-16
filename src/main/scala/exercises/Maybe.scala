package exercises

abstract class Maybe[+T] {
  def isEmpty: Boolean

  def add[B >: T](element: B): Maybe[B]

  def filter(f: T => Boolean): Maybe[T]

  def map[B](f: T => B): Maybe[B]

  def flatMap[B](f: T => Maybe[B]): Maybe[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

case object EmptyMaybe extends Maybe[Nothing] {
  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): Maybe[B] = NodeMaybe(element)

  override def filter(f: Nothing => Boolean): Maybe[Nothing] = EmptyMaybe

  override def map[B](f: Nothing => B): Maybe[B] = EmptyMaybe

  override def flatMap[B](f: Nothing => Maybe[B]): Maybe[B] = EmptyMaybe

  override def printElements: String = ""
}

case class NodeMaybe[+T](element: T, tail: Maybe[Nothing] = EmptyMaybe) extends Maybe[T] {
  override def isEmpty: Boolean = false

  override def add[B >: T](element: B): Maybe[B] = NodeMaybe[B](element, EmptyMaybe)

  override def printElements: String = element.toString

  override def filter(f: T => Boolean): Maybe[T] = if(f(element)) this else EmptyMaybe

  override def map[B](f: T => B): Maybe[B] = NodeMaybe[B](f(element), EmptyMaybe)

  override def flatMap[B](f: T => Maybe[B]): Maybe[B] = f(element)
}

object Tester extends App {
  val node = NodeMaybe(1)
  println(node)
  val anotherNode = node.add(2)
  println(anotherNode)

  println(node.filter(_ % 2 != 0))
  println(node.filter(_ % 2 == 0))
  println(node.map(item => item * 2))
  println(node.flatMap(x => NodeMaybe(x % 2 != 0)))
}


