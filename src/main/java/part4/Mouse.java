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
final class Mouse {

    private static final char STEP_FORWARD = 'F';
    private static final char STEP_RIGHT = 'R';

    private Mouse() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] countOfSeeds = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                countOfSeeds[i][j] = in.nextInt();
            }
        }
        int i, j;
        for (i = m - 1; i >= 0; --i) {
            for (j = 0; j < n; ++j) {
                countOfSeeds[i][j] += Math.max(i == m - 1 ? 0 : countOfSeeds[i + 1][j], j == 0 ? 0 : countOfSeeds[i][j - 1]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        i = 0;
        j = n - 1;
        while (i < m - 1 && j > 0) {
            if (countOfSeeds[i + 1][j] > countOfSeeds[i][j - 1]) {
                stringBuilder.append(STEP_FORWARD);
                ++i;
            } else {
                stringBuilder.append(STEP_RIGHT);
                --j;
            }
        }
        while (i < m - 1) {
            stringBuilder.append(STEP_FORWARD);
            ++i;
        }
        while (j > 0) {
            stringBuilder.append(STEP_RIGHT);
            --j;
        }
        out.println(stringBuilder.reverse());
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
