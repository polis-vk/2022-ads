package part4;

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

public final class Stairs {
    private Stairs() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] steps = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            steps[i] = in.nextInt();
        }
        int k = in.nextInt();
        int result = stairCost(steps, steps.length, k);
        out.println(result);
    }

    private static int findMax(int[] maxCosts, int i, int k) {
        int max = Integer.MIN_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i - j < 0) {
                break;
            }
            if (maxCosts[i - j] > max) {
                max = maxCosts[i - j];
            }
        }
        return max;
    }

    private static int stairCost(int[] steps, int n, int k) {
        int[] maxCosts = new int[steps.length];
        maxCosts[0] = 0;
        for (int i = 1; i < n; i++) {
            maxCosts[i] = findMax(maxCosts, i, k) + steps[i];
        }
        return maxCosts[n - 1];
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
