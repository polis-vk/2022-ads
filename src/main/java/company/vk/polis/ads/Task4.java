package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    public static void main(final String[] arg) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] stairs = new int[N + 2];
        for (int i = 0; i < N; i++) {
            stairs[i + 1] = scanner.nextInt();
        }

        int K = scanner.nextInt();
        int[] maxs = new int[K];

        for (int i = 1; i < N + 2; i++) {

            int max = maxs[0];
            for (int j = 1; j < maxs.length; j++) {
                if (maxs[j] > max) {
                    max = maxs[j];
                }
            }

            for (int j = 0; j < K - 1; j++) {
                maxs[j] = maxs[j + 1];
            }

            maxs[K - 1] = stairs[i] + max;
        }

        System.out.println(maxs[K - 1]);
    }
}
