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

// Submission link: https://www.eolymp.com/ru/submissions/11767418
public final class MouseAndGrains {
    private MouseAndGrains() {
        // Should not be instantiated
    }

    private static String findPath(int[][] a, int m, int n) {
        StringBuilder sb = new StringBuilder();

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (j > 0 && i < m - 1) {
                    a[i][j] = Math.max(a[i][j - 1], a[i + 1][j]) + a[i][j];
                } else if (j > 0) {
                    a[i][j] = a[i][j - 1] + a[i][j];
                } else if (i < m - 1) {
                    a[i][j] = a[i + 1][j] + a[i][j];
                }
            }
        }

        int i = 0;
        int j = n - 1;
        while (i < m - 1 && j > 0) {
            if (a[i + 1][j] > a[i][j - 1]) {
                sb.append('F');
                i++;
            } else {
                sb.append('R');
                j--;
            }
        }

        while (i < m - 1) {
            sb.append('F');
            i++;
        }

        while (j > 0) {
            sb.append('R');
            j--;
        }

        return sb.reverse().toString();
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] a = new int[m][n];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = in.nextInt();
            }
        }

        System.out.println(findPath(a, m, n));
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
