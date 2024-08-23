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

    val food_list = List("Idli", "Vada", "Dosa", "Uttapam", "Biriyani", "Chocolate", "Curd")
    for (food <- food_list){
      println(food)
    }

    println(food_list.filter(_.startsWith("C")))

    // altering records in for loop using if condition
    import scala.collection.mutable.ArrayBuffer
    val evenBuffer: ArrayBuffer[Int] = ArrayBuffer()
    for (m <- 1 to 20 if m % 2 == 0){
      evenBuffer += m
    }

    println(evenBuffer)

    // write a scala program to show collection of first prime numbers between 1 to 100
    val primeBuffer: ArrayBuffer[Int] = ArrayBuffer()
    for (m <- 2 to 100){
      var Prime: Boolean = true
      for (n <- primeBuffer){
        if (m % n == 0) {
          Prime = false
        }
      }
      if (Prime) {
        primeBuffer += m
      }
    }

    println(primeBuffer)

    // using soe
    val isPrime = Array.fill(101)(true)
    isPrime(0) = false
    isPrime(1) = false

    for (i <- 2 to math.sqrt(100).toInt){
      if (isPrime(i)) {
        for (j <- i * i to 100 by i){
          isPrime(j) = false
        }
      }
    }

    println((2 to 100).filter(isPrime))

    // fibonacci series using while
    val fibonacciBuffer: ArrayBuffer[Int] = ArrayBuffer()
    fibonacciBuffer += 0
    fibonacciBuffer += 1
    var i = 2
    while (fibonacciBuffer(i-1) < 100){
      fibonacciBuffer += (fibonacciBuffer(i- 1) + fibonacciBuffer(i - 2))
      i += 1
    }
    println("fibonacci: " + fibonacciBuffer)

    // find the first prime greater than given number
    import scala.io.StdIn._

    print("Enter a number: ")
    val userInput = readInt()
    var m = userInput
    var lastPrime = 0
    while (lastPrime <= userInput) {
      var isPrime: Boolean = true
      var n = 2
      while (n < math.sqrt(m)) {
        if (m % n == 0) isPrime = false
        n += 1
      }
      if (isPrime) lastPrime = m
      m += 1
    }

    println("Prime grater: " + lastPrime)
  }
}