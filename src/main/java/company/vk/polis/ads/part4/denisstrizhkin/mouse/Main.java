package company.vk.polis.ads.part4.denisstrizhkin.mouse;

import java.io.*;
import java.util.StringTokenizer;

// https://www.eolymp.com/ru/submissions/11799084
public class Main {
    private Main() {
        // Should not be instantiated
    }

    private enum Direction {
        R("R"),
        F("F");

        private final String str;

        Direction(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }
    }

    private static int[][] field;
    private static int[][] dArr;
    private static Direction[][] path;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        field = new int[n][m];
        dArr = new int[n][m];
        path = new Direction[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                field[i][j] = in.nextInt();
            }
        }

        dArr[0][m - 1] = field[0][m - 1];

        for (int i = m - 2; i >= 0; i--) {
            dArr[0][i] = dArr[0][i + 1] + field[0][i];
            path[0][i] = Direction.R;
        }

        for (int i = 1; i < n; i++) {
            dArr[i][m - 1] = dArr[i - 1][m - 1] + field[i][m - 1];
            path[i][m - 1] = Direction.F;
        }

        for (int j = m - 2; j >= 0; j--) {
            for (int i = 1; i < n; i++) {
                if (dArr[i][j + 1] > dArr[i - 1][j]) {
                    dArr[i][j] = dArr[i][j + 1];
                    path[i][j] = Direction.R;
                } else {
                    dArr[i][j] = dArr[i - 1][j];
                    path[i][j] = Direction.F;
                }

                dArr[i][j] += field[i][j];
            }
        }

        StringBuilder sB = new StringBuilder();
        for (int j = 0, i = n - 1; j != m - 1 || i != 0; ) {
            sB.append(path[i][j].getStr());
            switch (path[i][j]) {
                case F:
                    i--;
                    break;
                case R:
                    j++;
                    break;
            }
        }
        out.println(sB);
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