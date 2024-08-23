object FileAnalysis {
    def main(args: Array[String]): Unit = {
      import scala.io.Source
      // read from file
      val source = Source.fromFile("C:\\Users\\Administrator\\Downloads\\sample.txt")
      // read all lines and create a list of lines
      val lines = source.getLines().toList

      // close the file
      source.close()

      println(lines)

      val words = lines.flatMap(_.split("\\s+")).map(_.toLowerCase)

      // print top to most frequent words
      val wordCount = words.groupBy(identity).view.mapValues(_.size).toSeq.sortBy(-_._2)

      wordCount.take(10).foreach((word, count) => println(s"$word: $count"))

      println(words)
      // find the total number words:
      println("totalwords: " + words.length)
      // find the word with maximum length and minimum length
      val wordBySize = words.sortBy(_.length)
      println("smallest word: " + wordBySize.head)
      println("largest word: " + wordBySize.last)

      // without sorting
      println("smallest word: " + words.minBy(_.length))
      println("largest word: " + words.maxBy(_.length))
      
      
    }
}
