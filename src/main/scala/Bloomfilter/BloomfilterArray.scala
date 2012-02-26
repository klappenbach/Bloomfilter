package Bloomfilter

class BloomfilterArray(private val words: List[String]) extends Bloomfilter(words) {

  private val bitsPerWord = 10
  private val table = new Array[Boolean](words.size * bitsPerWord)
  insert(words)

  override def hashes(word: String) = List(word.hashCode() + "")

  private def insert(words: List[String]) {
    words.foreach(insert(_))
  }

  private def insert(word: String) {
    for (hash <- hashes(word)) table(getIndex(hash)) = true
  }

  def exists(word: String) = hashes(word).forall((hash: String) => table(getIndex(hash)) )

  private def getIndex(hash: String) = math.abs(hash.hashCode) % table.size

  def fillRate: Double = table.count(b => b)/table.size.toDouble

  def size = words.size

}
