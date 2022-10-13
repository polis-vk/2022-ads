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

// Submission link: https://www.eolymp.com/ru/submissions/11766676
public final class Stairs {
    private Stairs() {
        // Should not be instantiated
    }

    private static int maxInRange(int[] a, int from, int to) {
        int max = a[from];
        for (int i = from + 1; i < to; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        return max;
    }

    private static int countStairs(int[] a, int k) {
        int[] d = new int[a.length];
        int j;

        for (int i = 1; i < d.length; i++) {
            j = Math.max(0, i - k);
            d[i] = maxInRange(d, j, i) + a[i];
        }

        return d[d.length - 1];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n + 2];

        for (int i = 1; i < n + 1; i++) {
            a[i] = in.nextInt();
        }

        int k = in.nextInt();

        System.out.println(countStairs(a, k));
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
