object DataStructuresTwo {
  def main(args: Array[String]): Unit = {
    // Scala collection: Map.
    // Collection of key value pairs.
    // Can be mutable or immutable, by default map is immutable.
    val fruitBasket = Map(
      "Apple" -> 20,
      "Blueberry" -> 290,
      "Cherry" -> 100,
      "Donuts" -> 20,
      "Eclairs" -> 100
    )

    println("Do we have Donuts: " + fruitBasket.contains("Donuts"))
    println("fruitBasket size: " + fruitBasket.size)

    val keys = fruitBasket.keys
    val values = fruitBasket.values
    println(keys)
    println(values)
    println(fruitBasket.head)
    println(fruitBasket.tail)
    println(fruitBasket.last)
    val newFruitBasket = fruitBasket.removed("Donut")
    println("new basket: " + newFruitBasket)

    import scala.collection.mutable
    val mutableFruitBasket = mutable.Map(
      "Apple" -> 20,
      "Blueberry" -> 290,
      "Cherry" -> 100,
      "Donuts" -> 20,
      "Eclairs" -> 100
    )

    val donutCount = mutableFruitBasket("Donuts")

    mutableFruitBasket.remove("Donuts")

    println("mutable after removal: " + mutableFruitBasket)

    // update the value
    mutableFruitBasket("Apple") += 20
  }
}
