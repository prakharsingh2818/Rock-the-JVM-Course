package lectures.part2oop

object CaseClasses extends App {

  case class Person(name: String, age: Int)

  /**
   * 1) Class Parameters are converted to fields
   */
  val jim = new Person("Jim", 34)
  println(jim.name)

  /**
   * 2) Sensible toString
   */
  println(jim)

  /**
   * 3) Equals and Hashcode implemented
   */
  val prakhar = new Person("Jim", 34)

  println(jim == prakhar)

  /**
   * 4) Case classes have handy copy methods
   */
  val jim2 = jim.copy()
  val jim3 = jim.copy(age = 45)
  val jim4 = jim.copy(name = "Not Jim")
  println(jim2)
  println(jim3)
  println(jim4)

  /**
   * 5) Case Classes have companion object
   */
  val thePerson = Person
  val mary = Person("Mary", 23)

  /**
   * 6) Case classes are Serializable
   */

  /**
   * 7) Case classes have extractor patterns = Case Classses can be used in pattern matching
   */

  case object UnitedKingdom {
    def name:String = "The UK of GB and NI"
  } // Case objects cannot have companion objects else they are same as case classes


}
