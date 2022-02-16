package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  // create success and failure
  val aSuccess =  Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure!!!"))
  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No string for you!")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "Backup"

  println(Try(potentialFailure).orElse(Try(backupMethod())))

  // IF you design an API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("Valid Result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  /*
  Exercises
  */
  val hostName = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>....</html>"
      else throw new RuntimeException("Connection Interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  //Try(HttpService.getConnection(hostName, port)).flatMap(c => Try(c.get("www.google.com"))).map(s => renderHTML(s))
  for {
    c <- Try(HttpService.getConnection(hostName, port))
    page <- Try(c.get("www.google.com"))
  } yield renderHTML(page)

  val possibleConnection = HttpService.getSafeConnection(hostName, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("www.google.com"))
  possibleHTML.foreach(renderHTML)

  // shorthand version
  HttpService.getSafeConnection(hostName, port)
    .flatMap(connection => connection.getSafe("www.google.com"))
    .foreach(renderHTML)

  for {
    connection <- HttpService.getSafeConnection(hostName, port)
    html <- connection.getSafe("www.google.com")
  } renderHTML(html)
}
