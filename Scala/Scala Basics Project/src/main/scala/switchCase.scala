// in scala programming switch is referred as match.
//
object switchCase {
  def main(args: Array[String]): Unit = {
    def dayOfWeek(day: Int): String = day match {
      case 1 => "Monday"
      case 2 => "Tuesday"
      case 3 => "Wednesday"
      case 4 => "Thursday"
      case 5 => "Friday"
      case 6 => "Saturday"
      case 7 => "Sunday"
      case 8 => "Invalid"
    }

    println(dayOfWeek(1))


    def stringMatch(input: String): String = input match {
      case "John" => "Hello John"
      case "Jane" => "Hello Jane"
      case _ => "Hello stranger"
    }

    println(stringMatch("Jane"))
    println(stringMatch("Tom"))

    // create a calculator using match
    def matchCalculator(a: Float, b: Float, op: Char): Any = op match {
      case '+' => a + b
      case '-' => a - b
      case '*' => a * b
      case '/' => if b != 0 then a / b else "Divide by zero"
      case '^' => math.pow(a, b)
      case '%' => a % b
      case _ => "Invalid Operator"
    }

    println(matchCalculator(6, 3, '+'))
    println(matchCalculator(6, 3, '-'))
    println(matchCalculator(6, 3, '*'))
    println(matchCalculator(6, 3, '/'))
    println(matchCalculator(6, 0, '/'))
    println(matchCalculator(2, 2, '^'))
    println(matchCalculator(6, 4, '%'))
  }
}



