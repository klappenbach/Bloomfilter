package Bloomfilter;

/**
 * Hello world!
 *
 */
object App extends App {
  val words: List[String] = new DictionaryReader().readFile()
  val table: Bloomfilter = new BloomfilterArray(words) with Md5splitHashes
  println("Loaded " + table.size + " words.")
  println(table.exists("madman"))
  println(table.exists("madsfs"))
  println(table.exists("madman345"))
}
