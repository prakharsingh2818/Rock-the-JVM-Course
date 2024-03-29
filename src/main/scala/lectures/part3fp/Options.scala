package lectures.part3fp

import scala.util.Random

object Options extends App {
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // unsafe APIs
  def unsafeMethod(): String = null
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  // Design unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterChainedResult)

  // Functions on Option
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // Unsafe

  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  /**
   * 1.
   */
  val config: Map[String, String] = Map(
    "host" -> "127.0.0.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if(random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  val host = config.get("host")
  val port = config.get("port")
  //println(Connection(host.get, port.get).get.connect)

  /*
    if(h != null) {
      if(p != null) {
        return Connection(h, p)
    return null
      }
    }
  */
  val connection = host.flatMap(h => (port.flatMap(p => Connection(h, p))))
  /*
    if(c != null)
      return c.connect
    return null
  */

  val connectionStatus = connection.map(c => c.connect)

  //if(connectionStatus == null) println(None) else println(Some(connectionStatus.get))
  println(connectionStatus)

  /*
    if(connectionStatus!= null)
      println(connectionStatus)
  */
  connectionStatus.foreach(println)

  val connectionStatus2 = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  connectionStatus2.foreach(println)
}
