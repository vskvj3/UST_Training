// parent class
class Animal(val name: String) {
  def makeSound(): Unit = {
    println(s"$name makes sound")
  }
}

// subclass
class Dog(name: String) extends Animal(name) {
  override def makeSound(): Unit = {
    println(s"$name makes sound: woof")
  }
}

object Inheritance extends App {
  val animal = new Animal("Generic Name")
  val dog = new Dog("Dog")
  animal.makeSound()
  dog.makeSound()
}