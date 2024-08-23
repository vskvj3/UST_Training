object Functions {
  def main(args: Array[String]): Unit = {
    import scala.io.StdIn._
    // function is block of code which must do a certain task that can be reused
    // function takes username as input and prints greetings

    // this function takes one parameter
    def greetings(username: String): Unit = {
      println(s"Greetings $username")
    }

    greetings("visakh")

    def isPrime(num: Int): Boolean = {
      return true
    }

    println(isPrime(5))
    //
    //    // create a basic function that takes more than one parameter
    //    def addTwoNumbers(a: Int, b: Int): Int = {
    //      a + b
    //    }
    //
    //    print("Enter a: ")
    //    val a = readInt()
    //    print("Enter b: ")
    //    val b = readInt()
    //    println("Sum: " + addTwoNumbers(a, b))
    //
    //    val a1 = readLine("Enter a: ").toInt
    //    val b1 = readLine("Enter b: ").toInt
    //    println("Sum: " + addTwoNumbers(a1, b1))

    // 1. Calculator
    // 2. create a scala function calculator
    def calculator(a: Int, b: Int, operation: String): Any = {
      if (operation == "+") {
        return a + b
      } else if (operation == "-") {
        return a - b
      } else if (operation == "*") {
        return a * b
      } else if (operation == "/") {
        return a / b
      }
    }

    //    var a = readLine("Enter a: ").toInt
    //    var b = readLine("Enter b: ").toInt
    //    var op = readLine("Enter op: ")
    //    print(calculator(a, b, op))
    //

    // default parameters
    def divide(a: Float = 1, b: Float = 1): Float = {
      a / b
    }
    println("Result: " + divide(10, 5))
    println("Result: " + divide(10))
    println("Result: " + divide())

    // lambda functions or anonymous functions
    val add = (a: Int, b: Int) => a + b
    println(add(1, 2))

    // higher order functions
    // functions passed as parameters passed as input to another function
    // takes another function as parameter
    def applyFunction(f: (Int, Int) => Int, value1: Int, value2: Int): Int = {
      f(value1, value2)
    }

    println(applyFunction(add, 5, 6))

    // write a function to remove duplicate entries from the list
    import scala.collection.mutable
//    def removeDuplicates2(values: List[Any]): List[Any] = {
//      var set: mutable.Set[Any] = mutable.Set()
//      var result: List[Any] = List()
//      for (value <- values) {
//        if !set.contains(value){
//          set += value
//          result += 1
//        }
//      }
//    }
    def removeDuplicates(values: List[Any]): List[Any] = {
      values.distinct
    }

    println(removeDuplicates(List("A", 1, "B", 1, "C", "B")))

    def isPalindrome(str: String): Boolean = {
      val cased = str.toLowerCase()
      cased.reverse == cased
    }

    println(isPalindrome("Malayalam"))
    println(isPalindrome("get"))

    def factorial(n: Int): Int = {
      if (n == 0) 1
      else n * factorial(n - 1)
    }
    println(factorial(5))

    def fibonacci(n: Int): Int = {
      if (n <= 1) 1
      else fibonacci(n - 2) + fibonacci(n - 1)
    }
    print(fibonacci(9))
    
    
  }
}