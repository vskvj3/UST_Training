case class Person(name: String, age:Int)

object CaseClass extends App {

  private def describePerson(person: Any): String = person match {
    case Person(name, age) => s"Person name: $name, age: $age"
  }

  private val person = Person("John", 45)
  println(describePerson(person))
}