package company.vk.polis.ads.part4.aosandy;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Мышки и зернышки
 */
public final class Task2 {
    static int[][] field;
    static int[][] maxField;
    static StringBuilder answer;
    static int m;
    static int n;

    private static void solve(final FastScanner in, final PrintWriter out) {
        m = in.nextInt();
        n = in.nextInt();
        field = new int[m][n];
        maxField = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }

        maxField[m - 1][0] = field[m - 1][0];

        for (int r = m - 1; r >= 0; r--) {
            for (int i = r, j = 0; j < n && i < m; i++, j++) {
                compareCells(i, j);
            }
        }
        for (int c = 1; c < n; c++) {
            for (int i = 0, j = c; j < n && i < m; i++, j++) {
                compareCells(i, j);
            }
        }

        answer = new StringBuilder();
        getAnswer(0, n - 1);
        out.println(answer.reverse());
    }

    private static void compareCells(int i, int j) {
        if (i + 1 < m && j - 1 >= 0) {
            if (maxField[i + 1][j] > maxField[i][j - 1]) {
                maxField[i][j] = maxField[i + 1][j] + field[i][j];
            } else {
                maxField[i][j] = maxField[i][j - 1] + field[i][j];
            }
        } else if (i + 1 < m) {
            maxField[i][j] = maxField[i + 1][j] + field[i][j];
        } else if (j - 1 >= 0) {
            maxField[i][j] = maxField[i][j - 1] + field[i][j];
        }
    }

    private static void getAnswer(int i, int j) {
        if (i < m - 1 && j > 0) {
            if (maxField[i + 1][j] > maxField[i][j - 1]) {
                answer.append('F');
                getAnswer( i + 1, j);
            } else {
                answer.append('R');
                getAnswer(i, j - 1);
            }
        } else if (i < m - 1) {
            answer.append('F');
            getAnswer(i + 1, j);
        } else if (j > 0) {
            answer.append('R');
            getAnswer(i, j - 1);
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
