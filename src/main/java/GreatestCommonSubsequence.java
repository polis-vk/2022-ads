//https://www.eolymp.com/ru/submissions/11826049

import java.util.Scanner;
import java.util.stream.IntStream;

public class GreatestCommonSubsequence {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int[] x = new int[n + 1];
            IntStream.rangeClosed(1, n).forEach(i -> x[i] = in.nextInt());
            int m = in.nextInt();
            int[] y = new int[m + 1];
            IntStream.rangeClosed(1, m).forEach(i -> y[i] = in.nextInt());
            int[][] dynamicLastTwo = new int[2][m + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dynamicLastTwo[i % 2][j] = x[i] == y[j]
                            ? 1 + dynamicLastTwo[(i - 1) % 2][j - 1]
                            : Math.max(dynamicLastTwo[(i - 1) % 2][j], dynamicLastTwo[i % 2][j - 1]);
                }
            }
            System.out.println(dynamicLastTwo[n % 2][m]);
        }
    }
}