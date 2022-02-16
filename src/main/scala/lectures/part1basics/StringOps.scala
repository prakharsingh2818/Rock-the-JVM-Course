package lectures.part1basics

object StringOps extends App{
  val str: String = "Hello, I am learning Scala"

  println(str.charAt(3))
  println(str.substring(3,7)) //Beginning index in inclusive and end index is exclusive
  println(str.split(" ").toList) //List representation
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.toUpperCase())
  println(str.capitalize)
  println(str.length)

  println("*********************")
  // Scala specific utilities
  val aNumberedString = "45"
  val aNumber = aNumberedString.toInt
  println(aNumber)
  println('a' +: aNumberedString :+ 'z')
  println(str.reverse)
  println(str.take(3))

  println("*********************")
  //Scala specific String interpolators

  //s interpolators
  val name = "Prakhar"
  val age = 23
  val greeting = s"Hello, my name is $name and I am $age years old"
  println(greeting)
  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old next year"
  println(anotherGreeting)


  println("------")


  //f interpolators
  val speed = 1.2f
  val myth = f"$name%s can read $speed%2.2f parathas per minute"
  val anotherMyth = f"$name%s can read $speed%.1f parathas per minute"
  println(myth)


  println("------")


  //raw interpolator
  println(raw"This is a \n new line")
  val escaped = "This is a \n new line"
  println(raw"$escaped")
}
