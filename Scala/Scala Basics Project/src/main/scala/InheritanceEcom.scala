class Ecommerce(val name: String, val quantity: Float, val price: Float) {
  def totalPrice: Double = quantity * price
  def displayDetails(): Unit = {
    println("Category: Generic")
    println(s"Name: $name, quantity: $quantity, price: $price, totalprice:$totalPrice")
  }
}

// inheritance Electronics
class Electronics(name: String, quantity: Float, price: Float) extends Ecommerce(name, quantity, price){
  override def displayDetails(): Unit = {
    println("Category: Electronics")
    println(s"Name: $name, quantity: $quantity, price: $price, totalprice:$totalPrice")
  }
}

// Books
class Books(name: String, quantity: Float, price: Float) extends Ecommerce(name, quantity, price){
  override def displayDetails(): Unit = {
    println("Category: Books")
    println(s"Name: $name, quantity: $quantity, price: $price, totalprice:$totalPrice")
  }
}

// FootWear
class FootWear(name: String, quantity: Float, price: Float) extends Ecommerce(name, quantity, price){
  override def displayDetails(): Unit = {
    println("Category: Footwear")
    println(s"Name: $name, quantity: $quantity, price: $price, totalprice:$totalPrice")
  }
}

object InheritanceEcom extends  App {
  private val generic = new Ecommerce("Generic Name", 12, 45)
  private val phone = new Electronics("Iphone", 15, 45000)
  private val book = new Books("Classic Book", 2, 405)
  private val footwear = new FootWear("Shoes", 19, 1500)

  generic.displayDetails()
  phone.displayDetails()
  book.displayDetails()
  footwear.displayDetails()
}