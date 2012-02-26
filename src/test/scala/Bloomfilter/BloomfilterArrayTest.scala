package Bloomfilter

import org.scalatest.{BeforeAndAfter, FunSuite}


class BloomfilterArrayTest extends FunSuite with BeforeAndAfter {
  var bloomfilter: Bloomfilter = _
  val words = new DictionaryReader().readFile()

  before {
    bloomfilter = new BloomfilterArray(words) with Md5splitHashes
    println("\nLoaded " + bloomfilter.size + " words into Bloom filter.")
    println("fillrate: " + bloomfilter.asInstanceOf[BloomfilterArray].fillRate )
  }

  test("false positives rate for 1000 random words is under 2%") {
    val iterations = 1000
    var falsePositives = 0;
    var correctLookups = 0;
    var notFound = 0
    val dictionary = words.toSeq

    for (iteration <- 1 to iterations) {
      val randomString = util.Random.alphanumeric.take(util.Random.nextInt(6) + 1).mkString
      if (bloomfilter.exists(randomString)) {
        if (dictionary contains randomString) {
          correctLookups += 1
        }
        else falsePositives += 1
      }
      else notFound += 1
    }

    val falsePositivePercent = falsePositives * 100 / iterations.toDouble
    println("\nOut of " + iterations + " random word lookups:")
    println("*** false positives: " + falsePositives + " (" + falsePositivePercent + "%) ***")
    println("\n(correct lookups: " + correctLookups + ", not found: " + notFound + ")")
    assert(falsePositivePercent < 2, "False positives are not under 2%, it's  a staggering " + falsePositivePercent + "%!")
  }
}
