package Bloomfilter

/**
 * Provides an implementation of the hash functions, to be used in a Bloom filter
 */
trait Md5splitHashes extends Bloomfilter {
  abstract override def hashes(word: String): List[String] = MD5.hash(word).grouped(8).toList
}

// MD5 checksum. From the internets...
private object MD5 {
  def hash(s: String) = {
    val m = java.security.MessageDigest.getInstance("MD5")
    val b = s.getBytes("UTF-8")
    m.update(b, 0, b.length)
    new java.math.BigInteger(1, m.digest()).toString(16)
  }
}