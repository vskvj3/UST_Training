object nestedCaseClass {
  def main(args: Array[String]): Unit = {
    // define a case class
    case class Employee(name: String, id: Int)

    // Define case class for a Department
    case class Department(name: String, employees: List[Employee])

    // create an instance of department
    val emp1 = Employee("John", 42)
    val emp2 = Employee("Jane", 43)
    val emp3 = Employee("Bob", 54)

    val hrDepartment = Department("HR", List(emp1, emp2))
    val itDepartment = Department("IT", List(emp3))

    // pattern matching with another case class
    hrDepartment match {
      case Department(deptName, employees) =>
        println(s"Name: $deptName, ")
        employees.foreach {
          case Employee(name, id) =>
            println(s"Employee: $name, id: $id")
        }
    }

    itDepartment match {
      case Department(deptName, employees) =>
        println(s"Name: $deptName, ")
        employees.foreach {
          case Employee(name, id) =>
            println(s"Employee: $name, id: $id")
        }
    }
  }
}
