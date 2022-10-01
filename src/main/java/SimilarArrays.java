import java.util.HashSet;
import java.util.Set;

public class SimilarArrays {
    public void checkArraysOnSimilar(int[] firstArr, int[] secondArr) {
        long firstSum = 0;
        Set<Integer> firstNumbers = new HashSet<>();
        for (int number : firstArr) {
            if (!firstNumbers.contains(number)) {
                firstNumbers.add(number);
                firstSum += number;
            }
        }
        int secondSum = 0;
        Set<Integer> secondNumbers = new HashSet<>();
        for (int number : secondArr) {
            if (!secondNumbers.contains(number)) {
                secondNumbers.add(number);
                secondSum += number;
            }
        }
        System.out.println(firstSum == secondSum ? "YES" : "NO");
    }

}
