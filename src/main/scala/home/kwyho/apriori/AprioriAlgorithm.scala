/**
 * Created by hok1 on 6/6/14.
 */

package home.kwyho.apriori

import scala.io.Source
import scala.collection.immutable.List
import scala.collection.immutable.Set
import java.io.File

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
}





