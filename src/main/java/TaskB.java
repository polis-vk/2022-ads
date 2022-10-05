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
    private TaskB() { }

    private static void solve(final FastScanner in, final PrintWriter out) {
        System.out.println(getValueSequence(in.nextInt()));
    }

    private static long getValueSequence(int distIndex) {
        long squareDigit = 1;
        long cubeDigit = 1;

        int cntOne = 1;
        int cntTwo = 1;

        long result = 0;

        for (int i = 0; i < distIndex; i++) {
            if (squareDigit == cubeDigit) {
                cntOne++;
                cntTwo++;
                result = squareDigit;
                squareDigit = (long) Math.pow(cntOne, 2);
                cubeDigit = (long) Math.pow(cntTwo, 3);
            } else if (squareDigit > cubeDigit) {
                result = cubeDigit;
                cntTwo++;
                cubeDigit = (long) Math.pow(cntTwo, 3);
            } else {
                result = squareDigit;
                cntOne++;
                squareDigit = (long) Math.pow(cntOne, 2);
            }
        }

        return result;
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