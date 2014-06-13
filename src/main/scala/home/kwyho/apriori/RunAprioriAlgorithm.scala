package home.kwyho.apriori

import java.io.File

object RunAprioriAlgorithm {
  val helpmsg : String =
    """
    Arguments:
    - filename: name of files with transactions
    """.stripMargin

  def main(args: Array[String]) = {
    if (args.size != 1) {
      println(helpmsg)
      System.exit(1)
    }
    val alg = new AprioriAlgorithm(new File(args(0)))
    alg.runApriori()
    println("===Support Items===")
    alg.toRetItems.foreach(println)
    println("===Association Rules===")
    alg.associationRules.foreach(println)
  }
}