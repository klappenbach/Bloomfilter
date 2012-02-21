package Bloomfilter

abstract class BloomfilterMap extends Bloomfilter {
  import scala.collection.mutable.Map
  private val table = Map[String,  Boolean]()

  def insert(words: List[String]): BloomfilterMap = {
    words.foreach(insert(_))
    this
  }

  def insert(word: String): BloomfilterMap = {
    for (hash <- hashes(word)) table += (hash -> true)
    this
  }
  
  def exists(word: String): Boolean = {
    hashes(word).forall(table.get(_) match {
      case Some(flag) => flag
      case _ => false
    })
  }

  def size = table.size
  
}
