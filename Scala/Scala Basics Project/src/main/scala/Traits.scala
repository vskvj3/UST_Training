// A trait is special type of class used to define collection for method  and
// field names that can be reused across different classes.

// Uses:
// - Code Reusability: Instead of duplicating code in multiple classes,
//  you can define common functionalities in a trait then mix it with any class

// Scala does not support multiple inheritance(class inherited from more than one class)
// Traits helps to achieve similar results
// A class can extend one class and can mix it with multiple traits.

trait Drivable {
  def drive(): Unit = println("Driving")
}

trait Flyable {
  def fly(): Unit = println("Flying")
}

class CarClass(maker: String, model: String) extends Drivable {
  override def drive(): Unit = println(s"Driving $model of $maker")
}

class AirPlane extends Flyable with Drivable {
  override def drive(): Unit = println(s"Taxiing on the runway")
  override def fly(): Unit = println(s"Airplane in flying at 30000 feet")
}

object Traits extends App {
  val car  = CarClass("test", "test")


}
