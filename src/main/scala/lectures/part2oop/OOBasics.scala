package lectures.part2oop

object OOBasics extends App {

  val person = new Person("Prakhar", 24)
  //println(person.age)  //Valid
  //println(person.x) //Valid
  person.greet()
  person.greet("Gaurav")

  val author = new Writer("George", "Orwell", 1912)
  val imposter = author
  val anotherImposter = new Writer("George", "Orwell", 1912)
  val novel = new Novel("Animal Farm", 1975, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter)) //comparison based on address
  println(novel.isWrittenBy(anotherImposter))

  val counter = new Counter(10)
  println(counter.increment(5).counter)
  println(counter.decrement(5).counter)

  //val personTwo = new Person("Singh", 24)

}

class Person(val name: String, val age: Int) {
  //body
  val x = 2 //field
  //println(1+x)  //valid

  def this(name:  String) = this(name, 0) //Better practice is to provide default parameters
  def this() = this("Prakhar")

  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")
  def greet(): Unit = println(s"Hi, I am $name")
}

// class parameter are not fields

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge: Int = yearOfRelease - author.yearOfBirth
  def isWrittenBy(author: Writer): Boolean = author == this.author
  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

class Writer(val firstName: String, val surname: String, val yearOfBirth: Int) {
  def fullName: String = firstName.appended(' ').appended(surname).toString()
}

class  Counter(val counter: Int) {
  def increment: Counter = {
    println("Incrementing by 1")
    new Counter(counter + 1)
  }
  def decrement: Counter = {
    println("Decrementing by 1")
    new Counter(counter - 1)
  }

  def increment(inc: Int): Counter = {
    if(inc <= 0) this
    else {
      val counter = increment
      counter.increment(inc - 1)
    }

  }
  /*def decrement(dec: Int): Counter = {
    if(dec <= 0) this
    else {
      val counter = decrement
      counter.decrement(dec - 1)
    }
  }*/

  def decrement(dec: Int): Counter = if(dec <= 0) this else decrement.decrement(dec - 1)
}
