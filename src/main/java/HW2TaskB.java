import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW2TaskB {
    private HW2TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int x = in.nextInt();
        long a = 1;
        long b = 1;
        int aIndex = 1;
        int bIndex = 1;
        long c = 1;
        for (int i = 1; i <= x; i++) {
            if (a == b) {
                c = a;
                aIndex++;
                bIndex++;
                a = (long) Math.pow(aIndex, 2);
                b = (long) Math.pow(bIndex, 3);
            } else if (a < b) {
                c = a;
                aIndex++;
                a = (long) Math.pow(aIndex, 2);
            } else {
                c = b;
                bIndex++;
                b = (long) Math.pow(bIndex, 3);
            }
        }
        out.println(c);
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
