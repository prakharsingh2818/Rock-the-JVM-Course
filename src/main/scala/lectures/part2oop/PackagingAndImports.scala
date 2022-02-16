package lectures.part2oop

import playground.{PrinceCharming, Cinderella as Princess}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val princess = new Princess  //Aliasing
  val prince = new PrinceCharming

  val date = new Date
  val sqlDate = new SqlDate(2021, 1, 17) //Aliasing //We can alternatively use fully qualified name

  // default imports
  // java.lang = String, Object, Exception
  // scala = Int, Nothing, Function
  // predef = println, ???

}
