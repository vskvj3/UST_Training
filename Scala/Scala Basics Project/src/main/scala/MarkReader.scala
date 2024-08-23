object MarkReader {
  def main(args: Array[String]): Unit = {
    import scala.io.StdIn._

    print("Enter the mark: ")
    val mark = readInt()

    var grade = 'F'

    if (mark >= 90) {
      grade = 'A'
    } else if(mark >= 80) {
      grade = 'B'
    } else if(mark >= 70) {
      grade = 'C'
    } else if(mark >= 60) {
      grade = 'D'
    } else if(mark >= 50) {
      grade = 'E'
    }

    println(s"you got $grade")

    val grade_alt = if mark >= 90 then 'A' else if mark >= 80 then 'B' else if mark >= 70 then 'C' else if mark >= 60 then 'D' else if mark >= 50 then 'E' else 'F'

    println(s"you got $grade_alt")

  }
}