package Bloomfilter

class BloomfilterArray extends Bloomfilter {


  private val table = new Array[Boolean](460000*5)

  override def hashes(word: String) = List(word.hashCode() + "")

  def insert(words: List[String]): BloomfilterArray = {
    words.foreach(insert(_))
    this
  }

  private def insert(word: String): BloomfilterArray = {
    for (hash <- hashes(word)) table(getIndex(hash)) = true
    this
  }

  def exists(word: String): Boolean = {
    hashes(word).forall((hash: String) => table(getIndex(hash)) )
  }

  def getIndex(hash: String) = {
    
    val i = math.abs(hash.toInt) % table.size
    if(i < 0) println("hash string: " + hash + " hashcode: " + hash.toInt + " table.size: " + table.size)
    i
  }
  
  def size = table.size

}
