package Bloomfilter

/**
 * A Bloom filter uses a set of hash functions (hashes) to create a bitmap of all the words in the given dictionary (words)
 * @param words The dictionary of words from which the Bloom filter will be built
 */
abstract class Bloomfilter(words: List[String]) {

  /**
   * @param word The word to compute hash values for
   * @return The hash functions to use when storing and looking words in the Bloom filter
   */
  def hashes(word: String): List[String]

  /**
   * @param word the word to lookup
   * @return true if the word exist in the Bloom filter, but also true for some small percentage of false positives. False always means the word does not exist in the Bloom filter.
   */
  def exists(word: String): Boolean

  /**
   * @return the number of words in the Bloom filter
   */
  def size: Int
}
