package company.vk.polis.ads;

import java.util.Scanner;

public class MaxCommonSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        int[] first = new int[n];
        for (int i = 0; i < n; i++) {
            first[i] = in.nextInt();
        }
        final int m = in.nextInt();
        int[] second = new int[m];
        for (int i = 0; i < m; i++) {
            second[i] = in.nextInt();
        }
        int[][] maxLengths = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (first[i - 1] == second[j - 1]) {
                    maxLengths[i][j] = maxLengths[i - 1][j - 1] + 1;
                } else {
                    maxLengths[i][j] = Math.max(maxLengths[i - 1][j], maxLengths[i][j - 1]);
                }
            }
        }
        System.out.println(maxLengths[n][m]);
    }
}
