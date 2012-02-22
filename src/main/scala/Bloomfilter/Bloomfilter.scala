package Bloomfilter


trait Bloomfilter {
    def hashes(word: String): List[String]

    def insert(words: List[String]): Bloomfilter

    def exists(word: String): Boolean

    def size: Int
}
