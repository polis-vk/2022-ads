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
public final class MouseAndSeeds {
    private MouseAndSeeds() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int rows = in.nextInt();
        int columns = in.nextInt();
        int[][] field = new int[rows + 1][columns + 1];
        fillArray(field, rows, columns, in);
        StringBuilder result = new StringBuilder("");
        solution(field, rows, columns, result);
        out.println(result.reverse());
    }

    private static void solution(int[][] field, int rows, int columns, StringBuilder result) {
        field[0][1] = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                field[i][j] = Math.max(field[i - 1][j], field[i][j - 1]) + field[i][j];
            }
        }
        int i = rows;
        int j = columns;
        while (i + j > 2) {
            if (field[i - 1][j] > field[i][j - 1]) {
                result.append('F');
                i--;
            } else {
                result.append('R');
                j--;
            }
        }
    }

    private static void fillArray(int[][] field, int rows, int columns, final FastScanner in) {
        for (int i = 0; i <= rows; i++) {
            field[i][0] = -1;
        }
        for (int j = 0; j <= columns; j++) {
            field[0][j] = -1;
        }
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                field[rows - i + 1][j] = in.nextInt();
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
