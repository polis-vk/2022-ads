package part4;

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
public final class MaxSubsequence {
    private MaxSubsequence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] secondArray = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            secondArray[i] = in.nextInt();
        }
        int[][] subsequence = new int[2][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (array[i] == secondArray[j]) {
                    subsequence[i % 2][j] = 1 + subsequence[(i - 1) % 2][j - 1];
                } else {
                    subsequence[i % 2][j] = Math.max(subsequence[(i - 1) % 2][j], subsequence[i % 2][j - 1]);
                }
            }
        }
        out.println(subsequence[n % 2][m]);
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
