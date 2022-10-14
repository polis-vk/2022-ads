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
// https://www.eolymp.com/ru/submissions/11783449
public final class MaxSubsequence {
    private MaxSubsequence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int [] firstArray = new int[n];
        for (int i = 0; i < n; i++) {
            firstArray[i] = in.nextInt();
        }
        int m = in.nextInt();
        int [] secondArray = new int[m];
        for (int i = 0; i < m; i++) {
            secondArray[i] = in.nextInt();
        }

        int[][] d = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (firstArray[i - 1] == secondArray[j - 1]) {
                    d[i][j] += d[i - 1][j - 1] + 1;
                } else {
                    d[i][j] += Math.max(d[i][j - 1], d[i - 1][j]);
                }
            }
        }
        out.println(d[n][m]);
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
