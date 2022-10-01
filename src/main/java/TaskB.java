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
        // Read x
        int x = in.nextInt();
        // Create three pointers to A, B and C
        int curA = 1;
        int curB = 1;
        int curC = 0;
        // Merge algorithm
        long res = 0;
        while (curC != x) {
            long elemA = (long) curA * curA;
            long elemB = (long) curB * curB * curB;
            if (elemA < elemB) {
                res = elemA;
                curA++;
            } else if (elemA > elemB) {
                res = elemB;
                curB++;
            } else {
                res = elemA;
                curA++;
                curB++;
            }
            curC++;
        }
        out.print(res);

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
