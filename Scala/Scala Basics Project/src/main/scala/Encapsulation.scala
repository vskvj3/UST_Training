class EmployeeDetails(private var name: String, private var salary: Int) {
  // method: setter
  def setName(empName: String): Unit = {
    if (empName.nonEmpty) name = empName
  }

  // method: setter
  def setSalary(newSalary: Int): Unit = {
    if (newSalary > 0) salary = newSalary
  }
  // getter
  def displayDetails(): Unit = {
    println(s"Name: $name, salary: $salary")
  }

  def getName: String = name

  def getSalary: Int = salary
}

object Encapsulation extends App {
  private val employeeOne = EmployeeDetails("John", 30000)
  println(s"name:${employeeOne.getName}, Salary: ${employeeOne.getSalary}")
  employeeOne.setName("Jane")
  employeeOne.setSalary(25000)
  println(s"name:${employeeOne.getName}, Salary: ${employeeOne.getSalary}")
}
