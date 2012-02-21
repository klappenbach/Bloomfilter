package Bloomfilter;

/**
 * Hello world!
 *
 */
object App extends App {
  val words: List[String] = new DictionaryReader().readFile()
  val table: BloomfilterTable = new BloomfilterTable() with Md5splitHashes
    table.insert(words)
  println(table.size)
  println(table.exists("madsfs"))
  println(table.exists("madman"))
  println(table.exists("madman345"))
}