/**
 * Created by hok1 on 6/6/14.
 */

import scala.io.Source
import scala.collection.immutable.List
import scala.collection.Set

class AprioriAlgorithm(inputFileName: String) {
  //val transactions : List[Set[String]]
  //val itemSet : Set[String]
  Source.fromFile(inputFileName).foreach {
    print
  }
}





