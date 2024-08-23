
// Scala Sets - A set is collection in scala which contains
// no duplicate values. Sets can be mutable or immutable,
// by default sets are immutable.

object DataStructuresThree {
  def main(args:Array[String]) : Unit = {
    import scala.collection.mutable
    val set1 = Set(1, 2, 3, 4, 5, 6, 7, 8)
    val set2 = Set("Apple","Banana","Cheery","Grapes","Tomato",
      "Oranges","Watermelon","Pineapple")

    // Basic Operations
    println(set1.contains(5))
    println(set1.size)
    println(set1 + 9)

    // Union of sets
    val set3 = Set("Grapes","Tomato","Guava","Oranges","Banana")
    val fruitBasket = set2 union set3
    println(fruitBasket)

    // Intersect : Common value between Two Sets.
    val interSet = set2 intersect set3

    // Difference
    val diffSet = set2 diff set3
    println(diffSet)

    val diffSet1 = set3 diff set2
    println(diffSet1)

    // subset check
    val isSubset = set2.subsetOf(set3)
    println(isSubset)

    // Transformation - map, filter, reduce
    println(set3.map(_.toLowerCase()).mkString(","))
    println(set1.map(_*2).mkString(","))
    println(set3.filter(_.startsWith("G")).mkString(","))

    // mutable Sets
    val set4 = mutable.Set(10, 20, 30, 40, 50, 60, 70)
    set4 += 10
    set4 ++= Set(80, 90)
    set4 --= Set(10, 20)

    // clear Sets
    set4.clear()

    // groupBy
    // create a new collection
    val fruitList = List("apple","apricot","banana","chocolate","coconut")
    val mapFruits = fruitList.groupBy(_.head)
    // add new values into map by converting it into mutable
    val newMapFruits: mutable.Map[Char, List[String]] = mutable.Map(mapFruits.toSeq: _*)
    newMapFruits ++= Map('D' -> List("Dragon Fruit"), 'E' -> List("Eclair"))
    println(newMapFruits)

    // Tuple Datatype
    // Immutable datatype
    // fixed size, heterogeneous
    val tupleOne = (1, "Hello Tuple", 3.42, 44353454354534534L, 'A')
    val firstValue = tupleOne._1
    println("fistValue in tuple: " + firstValue)

    //
    val (var1, var2, var3, var4, var5) = tupleOne
    println(var1 + var2 + var3 + var4 + var5)

    // tuple productArity
    // returns number of elements present in tuple
    println(tupleOne.productArity)

    // returns datatype of variable
    println("tupleOne datatype: " + tupleOne.getClass)

    // .copy() allows to you to copy tuple with some elements replaced
    val tupleCopy = tupleOne.copy(_4 = "Tuple")
    println("copy of tuple: " + tupleCopy)

    // foreach on tuple
    tupleOne.productIterator.foreach(println)
    tupleOne.productIterator.foreach(
      // arrow function: lambda expression
      (element => {
        println(s"Element: $element")
      })
    )
  }
}
