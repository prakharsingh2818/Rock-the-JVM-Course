package lectures.part1basics

object DefaultArgs extends App {
  //using default values
  def tailRecursiveFactorial(n: Int, acc: Int = 1): Int = if(n <= 1) acc else tailRecursiveFactorial(n-1, n*acc)

  def savePicture(format:String = "jpeg", width: Int, height: Int): Unit = println("Saving picture...")
  //savePicture(1024, 720) //Won't work as leading parameters cannot be ommited
  savePicture(width = 1024, height = 720)
}
