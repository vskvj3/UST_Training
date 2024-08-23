class Car(val make: String, val model: String, var year: Int) {
  def displayInfo(): Unit = {
    println(s"car info: $year, $make, $model")
  }
}

// create an object of class car
// singleton object extends App (trait) which does not needs a main function. 
object OOP extends App {
  private val car = new Car("Hyundai", "Crete", 2023)

  car.displayInfo()

  car.year = 2024

  car.displayInfo()
}