package company.vk.polis.ads.paikee.part4;

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
public final class Stairs {
    private Stairs() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        n += 2;
        int[] costs = new int[n];
        for (int i = 1; i < n - 1; i++) {
            costs[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] maxCosts = new int[n];
        Arrays.fill(maxCosts, Integer.MIN_VALUE);
        maxCosts[0] = 0;
        for (int i = 0; i + 1 < n; i++) {
            for (int step = 1; step <= k; step++) {
                if (i + step < n) {
                    int nextStep = i + step;
                    maxCosts[nextStep] = Math.max(maxCosts[nextStep], maxCosts[i] + costs[nextStep]);
                }
            }
        }
        out.println(maxCosts[n - 1]);
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