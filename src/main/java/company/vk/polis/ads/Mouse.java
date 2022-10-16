package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Mouse {
    private Mouse() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        m++;
        n++;
        int[][] array = new int[m][n];
        for (int i = 0; i < m - 1; i++) {
            for (int j = 1; j < n; j++) {
                array[i][j] = in.nextInt();
            }
        }
        System.out.println(findPath(array, m, n));
    }

    private static String findPath(int[][] map, int m, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (i != m - 2 || j != 1) {
                    map[i][j] += Math.max(map[i + 1][j], map[i][j - 1]);
                }
            }
        }
        int i = 0;
        int j = n-1;
        while (i != m-2 && j != 1) {
            if (map[i + 1][j] > map[i][j - 1]) {
                stringBuilder.append('F');
                i = i + 1;
            } else {
                stringBuilder.append('R');
                j = j - 1;
            }
        }
        while (i != m-2) {
            stringBuilder.append('F');
            i = i + 1;
        }
        while (j != 1) {
            stringBuilder.append('R');
            j = j - 1;
        }
        return stringBuilder.reverse().toString();
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
