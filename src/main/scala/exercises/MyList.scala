package exercises

import lectures.part2oop.Generics.MyList

import scala.runtime.Nothing$

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(a: A): B
}

/*class EvenPredicate[-T] extends MyPredicate[T] {
  override def test[T <: Int](element : T): Boolean = {
    element%2 == 0
  }
}*/

/*class StringToIntTransformer[-A,B] extends MyTransformer[A,B] {
  override def transform[A <: String, B <: Int](a: A): B  = a.toInt
}*/

abstract class MyList[+A] {
  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String
  /*def map[B](transformer: MyTransformer[A,B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]*/

  def filter(f: A => Boolean): MyList[A]

  def map[B](f: A => B): MyList[B]

  def flatMap[B](f: A => MyList[B]): MyList[B]

  def forEach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C]

  def fold[B](start: B)(f: (B, A) => B): B

  def ++[B >: A](list: MyList[B]): MyList[B]

  override def toString: String = "[" + printElements + "]"

}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  /*override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty*/

  override def filter(f: Nothing => Boolean): MyList[Nothing] = Empty

  override def map[B](f: Nothing => B): MyList[B] = Empty

  override def flatMap[B](f: Nothing => MyList[B]): MyList[B] = Empty


  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def forEach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int) = Empty

  override def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException
    else Empty
  }

  override def fold[B](start: B)(f: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = Cons(element, this)

  override def printElements: String = {
    if (this.isEmpty) //Will never run
      "" + h //Will never run
    else "" + h + " " + this.tail.printElements

  }

  /*override def map[B](transformer: MyTransformer[A, B]): MyList[B] = {
    new Cons(transformer.transform(h), t.map(transformer))
  }

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }

  override def filter(predicate: MyPredicate[A]): MyList[A] = {
    if(predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }*/

  override def filter(f: A => Boolean): MyList[A] = if (f(head)) Cons(head, t.filter(f)) else t.filter(f)

  override def map[B](f: A => B): MyList[B] = Cons(f(head), t.map(f))

  override def flatMap[B](f: A => MyList[B]): MyList[B] = f(head) ++ t.flatMap(f)

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  override def forEach(f: A => Unit): Unit = {
    f(head)
    tail.forEach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(value: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(value, Empty)
      else if (compare(value, sortedList.head) <= 0) Cons(value, sortedList)
      else Cons(sortedList.head, insert(value, sortedList.tail))
    }

    val sortedTail = tail.sort(compare)
    insert(head, sortedTail)
  }

  override def fold[B](start: B)(f: (B, A) => B): B =
    tail.fold(f(start, head))(f)

  override def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException
    else {
      Cons(f(this.head, list.head), tail.zipWith(list.tail, f))
    }
  }
}

object ListTest extends App {
  /*val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(list.tail.head)
  println(list.add(4).head)
  println(list.toString)*/

  val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonedListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Cons(6, Empty)))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  /*println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(a: Int): MyList[Int] = new Cons(a, new Cons(a + 1,  Empty))
  }).toString)*/

  println(listOfIntegers.filter(_ % 2 == 0))
  println(listOfIntegers.map(_ * 2))
  println(listOfIntegers.flatMap(element => Cons(element, Cons(element + 1, Empty))))

  // Because Cons in a case class so equals method are defined
  println(clonedListOfIntegers == listOfIntegers)

  println(listOfIntegers.forEach(print))

  println(listOfIntegers.zipWith(anotherListOfIntegers, (x, y) => x * y))

  println(listOfIntegers.fold(1)((x, y) => x * y))

  println(listOfIntegers.sort((x: Int, y: Int) => y - x))

  val forCombinations = for {
    a <- listOfIntegers
    b <- listOfStrings
  } yield a.toString + b

  println(forCombinations)
}