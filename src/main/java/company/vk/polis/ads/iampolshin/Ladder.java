package company.vk.polis.ads.iampolshin;

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
public final class Ladder {
    private static int[] costs;
    private static int[] steps;

    private Ladder() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/11783520
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        steps = new int[n + 2];
        for (int i = 1; i < steps.length - 1; i++) {
            steps[i] = in.nextInt();
        }

        int k = in.nextInt();
        costs = new int[n + 2];
        for (int i = 1; i < costs.length; i++) {
            costs[i] = getMaxCost(k, i);
        }
        out.println(costs[costs.length - 1]);
    }

    private static int getMaxCost(int k, int i) {
        int max = Integer.MIN_VALUE;
        int localMax;
        for (int step = 1; step <= k; step++) {
            if (i < step) {
                break;
            }
            localMax = steps[i] + costs[i - step];
            if (localMax > max) {
                max = localMax;
            }
        }
        return max;
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
