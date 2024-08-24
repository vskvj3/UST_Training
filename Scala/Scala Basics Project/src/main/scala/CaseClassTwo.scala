/* they are special kind of class that is used for modeling immutable data
 * structures
 * will automatically provide useful methods:
 * to string, equals, hashCode, pattern matching
 */
// create case class

case class  Employee(name: String, age: Int)

object CaseClassTwo {
  def main(args: Array[String]): Unit = {
    // create an instance
    val obj: Employee = Employee("John", 35)
    println(obj.name)
    println(obj.age)

    obj.match {
      case Employee(name, age) => println(s"Name: $name, Age: $age")
    }

    val OlderObj = obj.copy("Jane", 25)

    OlderObj.match {
      case Employee(name, age) => println(s"Name: $name, Age: $age")
    }

  }
}
