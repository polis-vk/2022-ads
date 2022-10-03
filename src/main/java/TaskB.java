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
public final class TaskB {
    private TaskB() {
        // Should not be instantiated

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        long x = in.nextInt();
        long Ai;
        long Bi;
        long Cx = 1;
        int i = 1;
        int j = 1;

        for (; x > 0; x--) {
            Ai = (long) i * i;
            Bi = (long) j * j * j;
            if (Ai < Bi) {
                Cx = Ai;
                i++;
            } else if (Ai > Bi) {
                Cx = Bi;
                j++;
            } else {
                Cx = Ai;
                i++;
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