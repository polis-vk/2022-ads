import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW4Task2 {
    private HW4Task2() {
        // Should not be instantiated
    }

    // Мышка и зёрнышки: https://www.eolymp.com/ru/submissions/11844712
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        long[][] field = new long[100][100];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }
        for (int i = 1; i < m; i++) {
            field[i][0] += field[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            field[0][j] += field[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                field[i][j] += Math.max(field[i - 1][j], field[i][j - 1]);
            }
        }
        n--;
        m--;
        StringBuilder result = new StringBuilder();
        while (n != 0 || m != 0) {
            field[m][n] = -1;
            if (m > 0 && n > 0) {
                if (field[m - 1][n] > field[m][n - 1]) {
                    m--;
                    result.append("F");
                } else {
                    n--;
                    result.append("R");
                }
            } else if (m == 0) {
                n--;
                result.append("R");
            } else {
                m--;
                result.append("F");
            }
        }
        out.println(result.reverse());
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
