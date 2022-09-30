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
public final class TaskA {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length = in.nextInt();
        int[] input = new int[length];
        int[] count = new int[108];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            input[i] = in.nextInt();
            if (min > input[i]) {
                min = input[i];
            }
        }
        for (int i = 0; i < length; i++) {
            count[input[i] - min]++;
        }
        for (int i = 0; i < 108; ++i) {
            if (count[i] != 0) {
                out.print(i + min + " ");
                count[i]--;
                i--;
            }
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
