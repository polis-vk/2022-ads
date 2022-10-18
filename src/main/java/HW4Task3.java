import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW4Task3 {
    private HW4Task3() {
        // Should not be instantiated
    }

    // Наибольшая общая подпоследовательность: https://www.eolymp.com/ru/submissions/11845176
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] first = new int[n];
        for (int i = 0; i < n; i++) {
            first[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] second = new int[m];
        for (int j = 0; j < m; j++) {
            second[j] = in.nextInt();
        }
        int[][] matrix = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (first[i - 1] == second[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }
        out.println(matrix[n][m]);
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
