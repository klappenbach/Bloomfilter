package Bloomfilter

import org.scalatest.{BeforeAndAfter, FunSuite}


class BloomfilterTableTest extends FunSuite with BeforeAndAfter {
  var bloomfilter: BloomfilterTable = _
  val words = new DictionaryReader().readFile()

  before {
    bloomfilter = new BloomfilterTable with Md5splitHashes
    bloomfilter.insert(words)
  }

  test("false positives rate is under 2%") {
    val iterations = 1000
    var falsePositives = 0;
    var correctLookups = 0;
    var notFound = 0
    val wordsSeq = words.toSeq
    for (iteration <- 1 to iterations) {
      val randomString = util.Random.alphanumeric.take(5).mkString
      val exists: Boolean = bloomfilter.exists(randomString)
      if(exists){
        if(wordsSeq contains randomString) correctLookups += 1
        else falsePositives += 1
      }
      else notFound += 1
    }
    println("false positives: " + falsePositives)
    println("correct lookups: " + correctLookups)
    println("not found: " + notFound)
    val falsePositivePercent = falsePositives * 100 / iterations
    assert(falsePositivePercent < 2, "False positives are not under 2%, it's  a staggering " + falsePositivePercent + "%!")
  }
}
