package company.vk.polis.ads;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    public static void main(final String[] arg) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scanner.nextInt();
        }

        int M = scanner.nextInt();

        int[] B = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            B[i] = scanner.nextInt();
        }

        int[][] res = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {

                res[i][j] = A[i] != B[j] ?
                        Math.max(res[i - 1][j], res[i][j - 1]) : res[i - 1][j - 1] + 1;
            }
        }

        System.out.println(res[N][M]);
    }
}
