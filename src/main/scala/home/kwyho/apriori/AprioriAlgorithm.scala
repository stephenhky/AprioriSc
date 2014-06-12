/**
 * Created by hok1 on 6/6/14.
 */

package home.kwyho.apriori

import scala.io.Source

class AprioriAlgorithm(inputFileName: String) {
  //val transactions : List[Set[String]]
  //val itemSet : Set[String]
  Source.fromFile(inputFileName).foreach {
    print
  }
}





