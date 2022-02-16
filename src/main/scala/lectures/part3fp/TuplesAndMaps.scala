package lectures.part3fp

object TuplesAndMaps extends App {
  // tuples = finite ordered "Lists"
  val aTuple = (2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1) //2
  println(aTuple.copy(_2 = "Goodbye Java"))
  println(aTuple.swap) // ("Hello, Scala", 2)

  // Maps - Keys -> Values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Prakhar", 555), ("Gaurav", 789)).withDefaultValue(-1)
  val syntacticSugarPhoneBook = Map("Prakhar" -> 123, "Gaurav" -> 789)

  // Map ops
  println(phoneBook.contains("Prakhar"))
  println(phoneBook("Prakhar"))
  println(phoneBook("Rishabh"))

  // add a pairing
  val newPairing = "Bhavya" -> 678
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // Functions on Maps
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phoneBook.view.filterKeys(x => x.startsWith("P")).toMap)

  //mapValues
  println(phoneBook.view.mapValues(number => "+91- " + number).toMap)

  // Conversions to other collection
  println(phoneBook.toList)
  println(List(("Prakhar", 555)).toMap)
  val names = List("Prakhar", "Gaurav", "Rishabh", "Bhavya", "Arihant", "Mohneesh", "Parth")
  println(names.groupBy(name => name.charAt(0)))

  /**
   * EXERCISES
   * 1. Prakhar -> 999 and PRAKHAR -> 789 ----- toLowerCase?
   * 2. Overly simplified Social Networking Site based on maps
   * Person = String
   *    - add a person to the network
   *    - remove
   *    - friend (mutual)
   *    - unfriend
   *
   *    - number of friends of a person
   *    - person with maximum friends
   *    - how many people have NO friends
   *    - if there is a social connection between 2 people (direct or not)
   */

  val newEntry = "PRAKHAR" -> 111
  val updatedPhoneBook = phoneBook + newEntry
  println(updatedPhoneBook.map(pair => pair._1.toLowerCase() -> pair._2))


  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val aFriends = network(a)
    val bFriends = network(b)
    network + (a -> (aFriends + b)) + (b -> (bFriends + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val aFriends = network(a)
    val bFriends = network(b)
    network + (a -> (aFriends - b)) + (b -> (bFriends - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    /*val friendsWithA = network.filter(pair => pair._2.contains(person))
    if(friendsWithA.isEmpty) network - person
    else {
      remove(network + (friendsWithA.head._1 -> (network(friendsWithA.head._1) - person)), person)
    }*/

    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, friends.head, person))
    }

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Gaurav"), "Prakhar")
  println(network)
  println(friend(network, "Prakhar", "Gaurav"))
  println(unfriend(network, "Prakhar", "Gaurav"))
  println(remove(friend(network, "Prakhar", "Gaurav"), "Gaurav"))

  val people = add(add(add(empty, "Prakhar"), "Gaurav"), "Rishabh")
  val prakharGaurav = friend(people, "Prakhar", "Gaurav")
  val testNet = friend(prakharGaurav, "Gaurav", "Rishabh")
  println(testNet)

  /*def nFriends(network: Map[String, Set[String]], person: String): Map[String, Int] = {
    network.map(pair => (pair._1 -> network(pair._1).toList.length)).filter(newPair => newPair._1.equals(person))
  }*/
  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  /*def maxFriends(network: Map[String, Set[String]]): Map[String, Int] = {
    val friendsWithCount = network.map(pair => (pair._1 -> network(pair._1).toList.length))
    friendsWithCount.filter(pair => friendsWithCount.view.values.max == pair._2)
  }*/
  def maxFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }


  /*def noFriends(network: Map[String, Set[String]]): Map[String, Int] = {
    val friendsWithCount = network.map(pair => (pair._1 -> network(pair._1).toList.length))
    friendsWithCount.filter(pair => pair._2 == 0)
  }*/
  def noFriends(network: Map[String, Set[String]]): Int = {
    //network.filter(pair => pair._2.isEmpty).size
    //network.count(pair => pair._2.isEmpty)
    //network.view.filterKeys(k => network(k).size == 0).size
    network.count(_._2.isEmpty)
  }

  /*def checkConnection(network: Map[String, Set[String]], person1: String, person2: String): Boolean = {
    val person1Friends = network(person1)
    val person2Friends = network(person2)
    val totalFriends: Set[String] = person1Friends ++ person2Friends
    if(totalFriends.toList.length == (person1Friends.toList.length + person2Friends.toList.length)) false
    else true
  }*/
  def checkConnection(network: Map[String, Set[String]], person1: String, person2: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(person2, Set(), network(person1) + person1)
  }

  val newTestNet = add(testNet, "Bhavya")
  println(nFriends(testNet, "Gaurav"))
  println(maxFriends(testNet))
  println(noFriends(newTestNet))
  println(checkConnection(newTestNet, "Prakhar", "Rishabh"))
  println(checkConnection(newTestNet, "Prakhar", "Bhavya"))
}
