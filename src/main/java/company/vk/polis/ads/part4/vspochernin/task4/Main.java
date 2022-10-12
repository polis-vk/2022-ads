package company.vk.polis.ads.part4.vspochernin.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * https://www.eolymp.com/ru/submissions/11760777
 *
 * @author Dmitry Schitinin
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        int[] stairs = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            stairs[i] = in.nextInt();
        }

        int k = in.nextInt();

        int[] costs = new int[stairs.length];
        for (int i = 1; i < stairs.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j < 0) { // Если вышли за границы массива.
                    break;
                }
                int cur = costs[i - j];
                if (cur > max) {
                    max = cur;
                }
            }
            costs[i] = stairs[i] + max;
        }

        out.println(costs[costs.length - 1]);
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
