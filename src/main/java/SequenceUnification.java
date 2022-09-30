import java.io.*;
import java.util.StringTokenizer;

public class SequenceUnification {
    private SequenceUnification() {
        // Should not be instantiated
    }

    private static void solve(final SequenceUnification.FastScanner in, final PrintWriter out) {
        int x = in.nextInt();

        int k = 0;
        long i = 0, j = 0;
        long Cx = i * i;
        i++;
        while (k < x) {
            long A = i * i;
            long B = j * j * j;

            if (A <= B) {
                if (Cx != A) {
                    Cx = A;
                    k++;
                }
                i++;
            } else {
                if (Cx != B) {
                    Cx = B;
                    k++;
                }
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
        final SequenceUnification.FastScanner in = new SequenceUnification.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
