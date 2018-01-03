package home.kwyho.apriori.test;

import home.kwyho.apriori.AprioriAlgorithm;
import org.junit.Assert;
import org.junit.Test;
import scala.collection.immutable.Set;
import scala.collection.mutable.Map;

import java.io.File;

public class AprioriTest {

    @Test
    public void fakeDataTest() {
        AprioriAlgorithm alg = new AprioriAlgorithm(new File(this.getClass().getResource("./aptest.csv").getPath()));
        alg.runApriori(0.15, 0.6);

        // supports
        Map<Set<String>, Double> supports = alg.toRetItems();
        Set<String> set1 = (Set<String>)(new Set.Set1<String>("a"));
        Assert.assertEquals(0.428571428571, supports.get(set1).get(), 1.0E-03);
        Set<String> set2 = (Set<String>)(new Set.Set2<String>("b", "d"));
        Assert.assertEquals(0.571428571429, supports.get(set2).get(), 1.0E-03);
        Set<String> set3 = (Set<String>)(new Set.Set3<String>("a", "b", "d"));
        Assert.assertEquals(0.285714285714, supports.get(set3).get(), 1.0E-03);

        // association rules
        System.out.println(alg.associationRules());
    }
}
