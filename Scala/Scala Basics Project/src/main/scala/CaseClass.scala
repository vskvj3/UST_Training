/* they are special kind of class that is used for modeling immutable data structures
 * will automatically provide useful methods:
 * to string, equals, hashCode, pattern matching
 */
case class Person(name: String, age:Int)

object CaseClass extends App {

  private def describePerson(person: Any): String = person match {
    case Person(name, age) => s"Person name: $name, age: $age"
  }

  private val person = Person("John", 45)
  println(describePerson(person))
}