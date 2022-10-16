package company.vk.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class Ratatouille {

    // https://www.eolymp.com/ru/submissions/11811767

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] field = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }

        int[][] path = new int[m][n];
        int[][] weights = new int[m][n];
        weights[0][n - 1] = field[0][n - 1];

        for (int i = 1; i < m; i++) {
            weights[i][n - 1] = weights[i - 1][n - 1] + field[i][n - 1];
            path[i][n - 1] = 0; // 0 - up, 1 - right;

        }
        for (int j = n - 2; j >= 0; j--) {
            weights[0][j] = weights[0][j + 1] + field[0][j];
            path[0][j] = 1; // 0 - up, 1 - right;
        }

        for (int i = 1; i < m; i++) {
            for (int j = n - 2; j >= 0; j--) {
                if (weights[i][j + 1] > weights[i - 1][j]) {
                    weights[i][j] = weights[i][j + 1] + field[i][j];
                    path[i][j] = 1; // 0 - up, 1 - right;
                } else {
                    weights[i][j] = weights[i - 1][j] + field[i][j];
                    path[i][j] = 0; // 0 - up, 1 - right;
                }
            }
        }
        out.println(direction(path, m, n));
    }

    private static StringBuilder direction(int[][] path, int m, int n) {
        StringBuilder direction = new StringBuilder();

        int i = m - 1;
        int j = 0;
        while (i != 0 || j != n - 1) {
            if (path[i][j] == 0) {
                direction.append("F");
                i--;
            } else {
                direction.append("R");
                j++;
            }
        }
        return direction;
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
