/**
 * Created by hok1 on 6/6/14.
 */

package home.kwyho.apriori

import scala.io.Source
import scala.collection.immutable.List
import scala.collection.immutable.Set
import java.io.File
import scala.collection.mutable.Map

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
  var toRetItems : Map[Set[String], Double] = Map()
  var associationRules : List[(Set[String], Set[String], Double)] = List()

  def getSupport(itemComb : Set[String]) : Double = {
    def withinTransaction(transaction : Set[String]) : Boolean = itemComb.map( x => transaction.contains(x)).reduceRight((x1, x2) => x1 && x2)
    val count = transactions.filter(withinTransaction).size
    count.toDouble / transactions.size.toDouble
  }

  def runApriori(minSupport : Double = 0.15, minConfidence : Double = 0.6) = {
    var itemCombs = itemSet.map( word => (Set(word), getSupport(Set(word)))).filter( wordSupportPair => (wordSupportPair._2 > minSupport))
    var currentLSet : Set[Set[String]] = itemCombs.map( wordSupportPair => wordSupportPair._1).toSet
    var k : Int = 2
    while (currentLSet.size > 0) {
      var currentCSet : Set[Set[String]] = currentLSet.map( wordSet => currentLSet.map(wordSet1 => wordSet | wordSet1)).reduceRight( (set1, set2) => set1 | set2).filter( wordSet => (wordSet.size==k))
      var currentItemCombs = currentCSet.map( wordSet => (wordSet, getSupport(wordSet))).filter( wordSupportPair => (wordSupportPair._2 > minSupport))
      currentLSet = currentItemCombs.map( wordSupportPair => wordSupportPair._1).toSet
      itemCombs = itemCombs | currentItemCombs
      k += 1
    }
    for (itemComb<-itemCombs) {
      toRetItems += (itemComb._1 -> itemComb._2)
    }
    calculateAssociationRule(minConfidence)
  }

  def calculateAssociationRule(minConfidence : Double = 0.6) = {
    for (item<-toRetItems.keys) {
      item.subsets.filter( wordSet => (wordSet.size<item.size & wordSet.size>0)).foreach( subset => {associationRules = associationRules :+ (subset, item diff subset, toRetItems(item).toDouble/toRetItems(subset).toDouble)})
    }
    associationRules = associationRules.filter( rule => rule._3>minConfidence)
  }
}





