package Bloomfilter

abstract class BloomfilterTable {
  import scala.collection.mutable.Map
  private val table = Map[String,  Boolean]()

  def hashes(word: String): List[String]

  def insert(words: List[String]): BloomfilterTable = {
    words.foreach(insert(_))
    this
  }

  def insert(word: String) = {
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
