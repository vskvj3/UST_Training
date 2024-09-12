import scala.io.Source
import java.io.PrintWriter
import scala.io.StdIn

object StoreToFile {
  def main(args: Array[String]): Unit = {
    val sourcePath = "C:/Files/Customer1.csv"
    val destinationPath = "C:/Files/output.txt"
    val source = Source.fromFile(sourcePath)

    val content = try {
//      source.getLines().mkString("\n")
    StdIn.readLine()

    } finally {
      source.close()
    }

    val writer = PrintWriter(destinationPath)
    try{
      writer.write(content)
    }
    finally {
      writer.close()
    }
  }
}
