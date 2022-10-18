package company.vk.polis.ads.part4.vigilanteee;

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
public final class MouseCherepanov {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] floor = new int[m + 2][n + 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                floor[m - i + 1][j] = in.nextInt();
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                floor[i][j] = Math.max(floor[i - 1][j], floor[i][j - 1]) + floor[i][j];
            }
        }
        int i = m;
        int j = n;
        StringBuilder answer = new StringBuilder();
        while (i != 1 || j != 1) {
            if (i == 1) {
                answer.append('R');
                j--;
                continue;
            }
            if (j == 1) {
                answer.append('F');
                i--;
                continue;
            }
            if (floor[i - 1][j] > floor[i][j - 1]) {
                answer.append('F');
                i--;
            } else {
                answer.append('R');
                j--;
            }
        }
        out.println(answer.reverse());
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
