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
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        long a = 1;
        long b = 1;
        long c;
        int length = in.nextInt();
        while (length > 1) {
            c = a * a - b * b * b;
            if (c == 0) {
                a++;
                b++;
            } else if (c < 0) {
                a++;
            } else {
                b++;
            }
            length--;
        }
        if (a * a < b * b * b) {
            out.println(a * a);
        } else {
            out.println(b * b * b);
        }
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
