package company.vk.polis.ads;

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

    private static long solveTask(long n) {
        long i = 1;
        long j = 1;
        int c = 1;

        long currentPow2 = i * i;
        long currentPow3 = j * j * j;

        while (c != n) {
            c++;
            if (currentPow2 > currentPow3) {
                j++;
                currentPow3 = j * j * j;
                if (c == n) {
                    return Math.min(currentPow2, currentPow3);
                }
            } else if (currentPow2 < currentPow3) {
                i++;
                currentPow2 = i * i;
                if (c == n) {
                    return Math.min(currentPow2, currentPow3);
                }
            } else {
                i++;
                currentPow2 = i * i;
                j++;
                currentPow3 = j * j * j;
            }
        }

        return currentPow2;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        out.println(solveTask(in.nextInt()));
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
