import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LongestCommonSubSequence {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] first = new int[in.nextInt() + 1];
        for (int i = 1; i < first.length; i++) {
            first[i] = in.nextInt();
        }

        int[] second = new int[in.nextInt() + 1];
        for (int i = 1; i < second.length; i++) {
            second[i] = in.nextInt();
        }

        int[][] matrix = new int[first.length][second.length];

        for (int i = 1; i < first.length; i++) {
            for (int j = 1; j < second.length; j++) {
                if (first[i] == second[j]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }

        out.println(matrix[first.length - 1][second.length - 1]);
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
