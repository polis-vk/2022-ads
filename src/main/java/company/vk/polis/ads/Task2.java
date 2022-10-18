package company.vk.polis.ads;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    public static void main(final String[] arg) {
        Scanner scanner = new Scanner(System.in);

        int M = scanner.nextInt();
        int N = scanner.nextInt();

        int[][] seeds = new int[M][N];
        int[][] res = new int[M][N];

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                seeds[M - 1 - i][j] = scanner.nextInt();
            }
        }

        res[0][0] = seeds[0][0];

        for (int j = 1; j < N; j++) {
            res[0][j] = seeds[0][j] + res[0][j - 1];
        }

        for (int i = 1; i < M; i++) {
            res[i][0] = seeds[i][0] + res[i - 1][0];
        }

        for (int i = 1; i < M; i++) {
            for(int j = 1; j < N; j++) {
                res[i][j] = seeds[i][j] + Math.max(res[i-1][j], res[i][j-1]);
            }
        }

        int i, j;

        for (i = M - 1, j = N - 1; i > 0 && j > 0;) {

            if (res[i][j - 1] > res[i - 1][j]) {
                builder.append("R");
                j--;
            } else {
                builder.append("F");
                i--;
            }
        }

        for (; i > 0; i--) {
            builder.append("F");
        }

        for (; j > 0; j--) {
            builder.append("R");
        }

        String output = builder.reverse().toString();
        System.out.println(output);
    }
}
