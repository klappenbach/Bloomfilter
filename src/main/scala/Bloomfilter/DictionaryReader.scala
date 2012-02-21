package Bloomfilter

/**
 * Reads a dictionary file with a word on each line and returns it as a List of Words.
 */
class DictionaryReader {

  def readFile(path: String = "wordlist.txt"): List[String] = {
    import scala.io.Source
    val iterator = for (line <- Source.fromFile(path).getLines())
    yield line.trim()
    iterator.toList
  }
}
