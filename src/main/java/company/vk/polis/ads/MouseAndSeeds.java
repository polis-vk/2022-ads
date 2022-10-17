package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//Мышка и зернышки

public class MouseAndSeeds {
    private MouseAndSeeds() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] field = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0 && j - 1 >= 0) {
                    field[i][j] += Math.max(field[i - 1][j], field[i][j - 1]);
                } else if (i - 1 >= 0) {
                    field[i][j] += field[i - 1][j];
                } else if (j - 1 >= 0) {
                    field[i][j] += field[i][j - 1];
                }
            }
        }

        StringBuilder path = new StringBuilder();
        restorePath(field, m - 1, n - 1, path);
        out.println(path.reverse());
    }

    private static void restorePath(int[][] field, int i, int j, StringBuilder path) {
        if (i - 1 < 0 || j - 1 < 0) {
            if (i - 1 >= 0) {
                path.append("F");
                restorePath(field, i - 1, j, path);
            } else if (j - 1 >= 0) {
                path.append("R");
                restorePath(field, i, j - 1, path);
            }
        } else {
            if (field[i - 1][j] > field[i][j - 1]) {
                path.append("F");
                restorePath(field, i - 1, j, path);
            } else {
                path.append("R");
                restorePath(field, i, j - 1, path);
            }
        }
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
