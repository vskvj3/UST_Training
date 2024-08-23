object Loops {
  def main(args: Array[String]): Unit = {
    // simple while loop
    var variable = 1
    while (variable < 10){
      println(variable)
      variable += 1
    }

    // for loop
    val fruits = List("Apples", "Oranges", "Grapes", "Bananas")
    for (fruit <- fruits){
      println(fruit)
    }

    for (i <- 1 to 10 by 2){
      println(i)
    }

  }
}