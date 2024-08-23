object ConditionalStatements {
  def main(args:Array[String]) : Unit = {
    // if condition
    val x = 10
    if(x > 5){
      println(s"$x Greater than five")
    } else {
      println(s"$x Less than or equal to five")
    }


    // userinput
    import scala.io.StdIn._

    val userInput = readLine("Please Enter a String: ")
    println(s"You have entered $userInput")

    println("Please enter a number: ")
    val inputInt = readInt()
    print(s"You have entered $inputInt")

    val result = if inputInt % 2 == 0 then "Even" else "Odd"
    println(s" Number is $result")
  }
}