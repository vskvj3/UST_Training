object Polymorphism extends App {
  private val shapes: List[Shape] = List(new Rectangle(10, 20), new Triangle(10, 20))
  shapes.foreach{
    shape => println(shape.area())
  }
}

class Shape {
  def area(): Double = 0.0
}

class Rectangle(height: Double, width: Double) extends Shape {
  override def area(): Double = height * width
}

class Triangle(base: Double, height: Double) extends Shape {
  override def area(): Double = base * height * 0.5
}