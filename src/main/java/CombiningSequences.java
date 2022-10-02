
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class CombiningSequences {
    private CombiningSequences() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        long x = in.nextInt();
        long i = 1;
        long j = 1;
        long valueSequenceA = 1;
        long valueSequenceB = 1;
        long result = 1;
        while (x > 0) {
            if (valueSequenceA == valueSequenceB) {
                result = valueSequenceA;
                i++;
                j++;
                valueSequenceA = i * i;
                valueSequenceB = j * j * j;
            } else if (valueSequenceA < valueSequenceB) {
                result = valueSequenceA;
                i++;
                valueSequenceA = i * i;
            } else {
                result = valueSequenceB;
                j++;
                valueSequenceB = j * j * j;
            }
            x--;
        }
        out.println(result);
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