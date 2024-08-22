object DataStructures{
  def main(args: Array[String]): Unit = {
    // collection of data types

    // List data type
    // - homogenous collection
    // - immutable
    // Ordered sequence of elements
    val ValList: List[String] = List("Apple", "BlueBerry", "Cherry", "Donuts", "Eclairs")
    println(ValList)

    ValList.foreach(
      println
    )

    val firstElement = ValList.head
    val afterFirstElement = ValList.tail
    val lastElement = ValList.last
    val secondValue = ValList(1)
    val length = ValList.length
    println("First Value: " + firstElement)
    println("After First Value: " + afterFirstElement)
    println("Last Value: " + lastElement)
    println("Second Value: " + secondValue)
    println("length: " + length)

    // append a new value to the list
    // appending value to a new list throws error due to immutability.

    val appendedList = ValList:+ "Fig"
    println("After appending: " + appendedList)


    // Empty List Declaration
    val emptyList: List[Nothing] = List()

    val newValList: List[String] = List("Fig", "Grapes", "Hazel Nut")

    // concatenation of two lists
    val FruitBasket = ValList ++ newValList

    println("After concat" + FruitBasket)

    // List transformation:
    val numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    println("MappedList: " + numList.map(_*3))
    println("Even Numbers: " + numList.filter(_%2 == 0))

    // flatmap
    val nestedList = List(
      List("Delhi", "Kochi", "Banglore", "Kolkatha"),
      List("Pune", "Mumbai", "Varkkala", "Delhi"),
      List("Trivandrum", "Kottayam", "Alappuzha", "Kulathur"),
    )

    println("Nested List: " + nestedList)
    println("Flatted List: " + nestedList.flatMap(identity))
    
    // Array data type
    // Mutable
    // Homogenous
    // Fixed Length

    // Sets
    // - no duplicates

    // Tuple
    // heterogeneous

  }
}
