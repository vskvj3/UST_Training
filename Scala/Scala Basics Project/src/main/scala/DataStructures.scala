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
    // identity is a predefined function A=>A
    println("Flatted List: " + nestedList.flatten)




    println("Sum of even: " + numList.filter(_%2 == 0).reduce(_+_))
    println("Sum of even: " + numList.filter(_ % 2 == 0).sum)

    println("Length of fruits: " + FruitBasket.map(_.length))
    println("Fruits Starts with A: " + FruitBasket.map(_.startsWith("A")))
    println("Fruits Starts with \"A\": " + FruitBasket.map(_.startsWith("A")))
    println("Fruits in uppercase: " + FruitBasket.map(_.toUpperCase))

    // sort the list by word len
    println(FruitBasket.sortBy(_.length))
    println(FruitBasket.sortWith(_.length < _.length))
    println(nestedList.flatten.sortBy(_.length))

    // print list of cities that begins with letter
    val flattenedCities = nestedList.flatten
    println(flattenedCities)

    flattenedCities.foreach(
      city =>
        if city.startsWith("K") then {
          println(city)
        }
    )

    println("starts with K: " + flattenedCities.filter(_.startsWith("K")))

    // subset and slicing
    println(flattenedCities.take(5))
    println(flattenedCities.takeRight(5))
    println(flattenedCities.drop(1))
    println(flattenedCities.dropRight(5))
    // slicing the list using start index and position
    println(flattenedCities.slice(1, 6))
    // slicing using random index position
    val indices = List(0, 2, 4, 5, 7, 9)
    println(indices.flatMap(index => flattenedCities.lift(index)))
    // lift is to that retrieves an element at specified index
    println(indices.map(index => flattenedCities(index)))

    println(flattenedCities)

    val populations = List(16787941, 676000, 8436675, 14910352, 3124458, 12478447, 21908, 957730, 619000, 174164, 30500)
    val pairedList = flattenedCities.zip(populations)
    println(pairedList)

    // Array data type
    // Mutable
    // Homogenous
    // Fixed Length
    // Index of sequences
    // provides fast access and modification of elements
    // - making them use full for performance critical application
    val emptyArray = Array[Integer]()
    val arrayOne = Array(1, 2, 3, 4, 5, 6)
    val firstVal = Array(0)
    arrayOne(0) = 10
    arrayOne.foreach(print)
    // print length
    println(arrayOne.length)

    // adding or removing values
    // array are of fixed length
    // arrayOne += 1 : will give an err
    // arrayOne -= 3 : will give an err

    import scala.collection.mutable.ArrayBuffer
    val arrayBuffer = ArrayBuffer(1, 2, 3, 4, 5)
    val twoDArray = Array(Array(1, 2, 3, 4, 5), Array(1, 2, 3, 4, 5))
    val twoDArrayBuffer = Array(ArrayBuffer(1, 2, 3, 4, 5), ArrayBuffer(1, 2, 3, 4, 5))
    val copyOfArrayOne = ArrayBuffer(arrayOne*)
    println("Copy of arrayOne:" + copyOfArrayOne)
    println(arrayBuffer)

    arrayBuffer -= 3
    println(arrayBuffer)

    arrayBuffer += 4
    println(arrayBuffer)

    // map and filter of array buffer
    println(arrayBuffer.map(_*3))
    println(arrayBuffer.filter(_%2 == 0))

    println(twoDArray.flatten.mkString(", "))
    println(twoDArrayBuffer.flatten.mkString(", "))

    val newArray = arrayBuffer.toArray
    println(newArray.mkString("[", ", ", "]"))
    // slicing
    println(arrayOne.take(3).mkString("Array(", ", ", ")"))
    println(arrayOne.takeRight(2).mkString("Array(", ", ", ")"))
    println(arrayOne.drop(1).mkString("Array(", ", ", ")"))
    println(arrayOne.dropRight(2).mkString("Array(", ", ", ")"))
    // slicing the list using start index and position
    println(arrayOne.slice(1, 2).mkString("Array(", ", ", ")"))
    println(arrayOne.reverse.mkString("Array(", ", ", ")"))
    println(arrayOne.sorted.mkString("Array(", ", ", ")"))


    // Sets
    // - no duplicates

    // Tuple
    // heterogeneous

  }
}
