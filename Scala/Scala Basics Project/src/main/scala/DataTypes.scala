object DataTypes{
  def main(args: Array[String]): Unit = {
    // primitive data types
    val intValue: Int = 30
    var doubleValue: Double = 50.4343907583947320
    var longInteger: Long = 890450943054L
    var floatValue: Float = 45.34F
    var byteValue: Byte = 12

    var stringValue: String = "Hello Scala"
    var charValue: Char = 'h'

    val boolValueOne: Boolean = true
    val boolValueTwo: Boolean = false

    println("IntValue: " + intValue)
    println("longInteger: " + longInteger)
    println("floatValue: " + floatValue)
    println("byteValue: " + byteValue)

    println("charValue: " + charValue)
    println("stringValue: " + stringValue)

    println("boolValueOne: " + boolValueOne)
    println("boolValueTwo: " + boolValueTwo)

    charValue = 'b'

    println("charValue after change: " + charValue)

//    intValue = 121
//    println("IntValue after change: " + intValue)
  }
}
