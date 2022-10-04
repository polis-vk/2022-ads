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
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();

        long i = 1, j = 1;
        int count = 0;
        long Cx = 1;

        while (x != count++) {
            if (Math.pow(i, 2) < Math.pow(j, 3)) {
                Cx = (long) Math.pow(i, 2);
                i++;
            } else if (Math.pow(i, 2) == Math.pow(j, 3)) {
                Cx = (long) Math.pow(i, 2);
                i++;
                j++;
            } else {
                Cx = (long) Math.pow(i, 3);
                j++;
            }
        }
        out.println(Cx);
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
