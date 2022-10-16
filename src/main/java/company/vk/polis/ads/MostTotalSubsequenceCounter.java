package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class MostTotalSubsequenceCounter {
    private MostTotalSubsequenceCounter() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size1 = in.nextInt();
        int[] array1 = new int[size1];
        for (int i = 0; i < size1; i++) {
            array1[i] = in.nextInt();
        }
        int size2 = in.nextInt();
        int[] array2 = new int[size2];
        for (int i = 0; i < size2; i++) {
            array2[i] = in.nextInt();
        }
        System.out.println(countMostTotalSubsequence(array1, array2, size1, size2));
    }

    private static int countMostTotalSubsequence(int[] a, int[] b, int m, int n) {
        int[][] comparassionTable = new int[2][n + 1];
        int i;
        for (i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (a[i - 1] != b[j - 1]) {
                    comparassionTable[i % 2][j] = Math.max(comparassionTable[(i + 1) % 2][j], comparassionTable[i % 2][j - 1]);
                } else if (a[i - 1] == b[j - 1]) {
                    comparassionTable[i % 2][j] = comparassionTable[(i + 1) % 2][j - 1] + 1;
                }
            }
        }
        return comparassionTable[(i + 1) % 2][n];
    }

    private static final class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
