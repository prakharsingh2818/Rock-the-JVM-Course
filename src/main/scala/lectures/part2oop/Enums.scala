package lectures.part2oop

object Enums {
  enum Permissions {
    case READ,  WRITE, EXECUTE, NONE

    // add fields or methods

    def openDocument(): Unit =
      if(this == READ) println("Opening document")
      else println("Reading not allowed")
  }

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) //100
    case WRITE extends PermissionsWithBits(2) //010
    case EXECUTE extends PermissionsWithBits(1) //001
    case NONE extends PermissionsWithBits(0) //000
  }

  object PermissionsWithBits {
    def from(bits: Int): PermissionsWithBits = //whatever
    PermissionsWithBits.NONE
  }

  val readPermissions: Permissions = Permissions.READ

  // Standard API
  val readPermissionsOrdinal = readPermissions.ordinal //0
  val allPermissions = Permissions.values
  val writePermissions = Permissions.valueOf("WRITE")

  def main(args: Array[String]): Unit = {
    readPermissions.openDocument()
    println(readPermissionsOrdinal)
    println(allPermissions)
    println(writePermissions)
  }



}
