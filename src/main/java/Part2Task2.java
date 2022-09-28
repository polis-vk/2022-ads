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
public final class Part2Task2 {
    private Part2Task2() {
        // Should not be instantiated
    }

    private static long getA(long i) {
        return i * i;
    }

    private static long getB(long i) {
        return i * i * i;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();
        int a = 1;
        int b = 1;
        int count = 0;
        long result = 0;
        while (count != x) {
            if (getA(a) < getB(b)) {
                result = getA(a);
                a++;
                count++;
            } else if (getA(a) == getB(b)) {
                result = getA(a);
                a++;
                b++;
                count++;
            } else {
                result = getB(b);
                b++;
                count++;
            }
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
