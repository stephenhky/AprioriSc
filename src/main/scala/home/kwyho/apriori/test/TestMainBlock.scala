package home.kwyho.apriori.test

import home.kwyho.apriori.AprioriAlgorithm
import java.io.File

object TestMainBlock {
  def main(args: Array[String]) = {
    val alg = new AprioriAlgorithm(new File("INTEGRATED-DATASET.csv"))
    println("Hello, World!")
    print(alg.itemSet)
  }
}