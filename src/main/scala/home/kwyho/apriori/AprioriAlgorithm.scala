/**
 * Created by hok1 on 6/6/14.
 */

package home.kwyho.apriori

import scala.io.Source
import scala.collection.immutable.List
import scala.collection.immutable.Set
import scala.collection.mutable.HashTable
import java.io.File
import scala.collection.mutable.HashMap

class AprioriAlgorithm(inputFile: File) {
  var transactions : List[Set[String]] = List()
  var itemSet : Set[String] = Set()
  for (line<-Source.fromFile(inputFile).getLines()) {
    val elementSet = line.trim.split(',').toSet
    if (elementSet.size > 0) {
      transactions = transactions :+ elementSet
      itemSet = itemSet ++ elementSet
    }
  }
  var toRetItems : Map[String, Double] = Map()
  var associationRules : List[(String, String, Double)] = List()

  def getSupport(itemComb : Set[String]) = {
    var count : Int = 0
    for (transaction <- transactions) {
      if (itemComb.map( x => transaction.contains(x)).reduceRight((x1, x2) => x1 && x2)) {
        count += 1
      }
    }
    count.toDouble / transactions.size.toDouble
  }
}





