/**
 * Created by hok1 on 6/6/14.
 */

package home.kwyho.apriori

import scala.io.Source
import scala.collection.immutable.List
import scala.collection.immutable.Set
import java.io.File
import scala.collection.mutable.Map
import scala.util.control.Breaks._

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
    val count = transactions.filter(transaction => itemComb.subsetOf(transaction)).size
    count.toDouble / transactions.size.toDouble
  }

def runApriori(minSupport : Double = 0.15, minConfidence : Double = 0.6) = {
  var itemCombs : Set[(Set[String], Double)] = Set()
  var currentCSet : Set[Set[String]] = itemSet.map( word => Set(word) )
  var k : Int = 2
  breakable {
    while (true) {
      val currentItemCombs : Set[(Set[String], Double)] = currentCSet.map( wordSet => (wordSet, getSupport(wordSet)))
                                        .filter( wordSetSupportPair => (wordSetSupportPair._2 > minSupport))
      val currentLSet = currentItemCombs.map( wordSetSupportPair => wordSetSupportPair._1).toSet
      if (currentLSet.isEmpty) break
      currentCSet = currentLSet.map( wordSet => currentLSet.map(wordSet1 => wordSet | wordSet1))
                                                          .reduceRight( (set1, set2) => set1 | set2)
                                                          .filter( wordSet => (wordSet.size==k))
      itemCombs = itemCombs | currentItemCombs
      k += 1
    }
  }
  for (itemComb<-itemCombs) {
    toRetItems += (itemComb._1 -> itemComb._2)
  }
  calculateAssociationRule(minConfidence)
}

  def calculateAssociationRule(minConfidence : Double = 0.6) = {
    toRetItems.keys.foreach(item =>
      item.subsets.filter( wordSet => (wordSet.size<item.size & wordSet.size>0))
          .foreach( subset => {associationRules = associationRules :+ (subset, item diff subset,
                                                                       toRetItems(item).toDouble/toRetItems(subset).toDouble)
                              }
                  )
    )
    associationRules = associationRules.filter( rule => rule._3>minConfidence)
  }
}
