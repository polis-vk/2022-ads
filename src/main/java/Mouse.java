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
// https://www.eolymp.com/ru/submissions/11779559
public final class Mouse {
    private Mouse() {
        // Should not be instantiated
    }

    private static String getPath(int[][] field, int m, int n) {
        int row = 0;
        int col = n - 1;
        StringBuilder res = new StringBuilder();

        while (row < m - 1 && col > 0) {
            if (field[row][col - 1] >= field[row + 1][col]) {
                res.insert(0, "R");
                col--;
            } else {
                res.insert(0, "F");
                row++;
            }
        }
        while (row < m - 1) {
            res.insert(0, 'F');
            row++;
        }
        while (col > 0) {
            res.insert(0, 'R');
            col--;
        }

        return res.toString();
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] field = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }

        for (int row = m - 1; row >= 0; row--) {
            for (int col = 0; col < n; col++) {
                int leftCell = 0;
                int downCell = 0;
                if (row + 1 < m) {
                    downCell = field[row + 1][col];
                }
                if (col > 0) {
                    leftCell = field[row][col - 1];
                }
                field[row][col] = Math.max(downCell, leftCell) + field[row][col];
            }
        }
        out.println(getPath(field, m, n));
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
