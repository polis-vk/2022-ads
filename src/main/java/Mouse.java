import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Mouse {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int rows = in.nextInt();
        int cols = in.nextInt();
        int[][] field = new int[rows + 2][cols + 2];

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                field[rows - i + 1][j] = in.nextInt();
            }
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                field[i][j] = Math.max(field[i - 1][j], field[i][j - 1]) + field[i][j];
            }
        }

        int i = rows;
        int j = cols;
        StringBuilder path = new StringBuilder();
        while (i + j > 2) {
            if (field[i - 1][j] > field[i][j - 1] || j == 1) {
                path.append('F');
                i--;
                continue;
            }
            path.append('R');
            j--;
        }
        System.out.println(path.reverse());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
