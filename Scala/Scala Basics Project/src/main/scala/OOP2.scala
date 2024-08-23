class Circle(val radius: Double) {
  def area: Double = math.Pi * radius * radius
  def circumference: Double = 2 * math.Pi * radius
}
// companion objects can be used to provide factory methods
// and utility functions related to the class
object Circle {
  def apply(radius: Double): Circle = new Circle(radius)
}

object OOP2 extends App {
  private val circle = Circle(5.0)
  println(f"Area: ${circle.area}%.2f \ncircumference: ${circle.circumference}%.2f")
}
