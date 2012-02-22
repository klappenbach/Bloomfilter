package Bloomfilter

trait Md5splitHashes extends Bloomfilter {
  abstract override def hashes(word: String): List[String] = MD5.hash(word).grouped(5).toList
}

object MD5 {
  def hash(s: String) = {
    val m = java.security.MessageDigest.getInstance("MD5")
    val b = s.getBytes("UTF-8")
    m.update(b, 0, b.length)
    new java.math.BigInteger(1, m.digest()).toString(16)
  }
}