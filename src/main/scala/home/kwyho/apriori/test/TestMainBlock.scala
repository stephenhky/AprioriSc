package home.kwyho.apriori.test

import home.kwyho.apriori.AprioriAlgorithm
import java.io.File

object TestMainBlock {
  def main(args: Array[String]) = {
    val alg = new AprioriAlgorithm(new File("INTEGRATED-DATASET.csv"))
    alg.runApriori()
    println("===Support Items===")
    alg.toRetItems.foreach(println)
    println("===Association Rules===")
    alg.associationRules.foreach(println)
  }
}