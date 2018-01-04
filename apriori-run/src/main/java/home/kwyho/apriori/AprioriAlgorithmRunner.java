package home.kwyho.apriori;


import java.io.File;

public class AprioriAlgorithmRunner {

    public static void helpMessage() {
        System.out.println("Arguments:");
        System.out.println("- filename: name of files with transactions");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            helpMessage();
            System.exit(1);
        }

        AprioriAlgorithm alg = new AprioriAlgorithm(new File(args[0]));
        alg.runApriori(0.15, 0.6);
        System.out.println("=== Support items ===");
        System.out.println(alg.toRetItems());
        System.out.println("=== Association rules ===");
        System.out.println(alg.associationRules());

    }
}
