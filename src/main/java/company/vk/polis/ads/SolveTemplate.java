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
public final class SolveTemplate {
    private SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int m = in.nextInt();
        final int n = in.nextInt();
        int[][] temple = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temple[i][j] = in.nextInt();
            }
        }
        String[][] maxWays = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxWays[i][j] = "";
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            maxWays[i][0] = maxWays[i + 1][0] + "F";
            temple[i][0] += temple[i + 1][0];
        }
        for (int j = 1; j < n; j++) {
            maxWays[m - 1][j] = maxWays[m - 1][j - 1] + "R";
            temple[m - 1][j] += temple[m - 1][j - 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (temple[i + 1][j] > temple[i][j - 1]) {
                    temple[i][j] += temple[i + 1][j];
                    maxWays[i][j] = maxWays[i + 1][j] + "F";
                } else {
                    temple[i][j] += temple[i][j - 1];
                    maxWays[i][j] = maxWays[i][j - 1] + "R";
                }
            }
        }
        out.println(maxWays[0][n - 1]);
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
