package company.vk.polis.ads.part4.nikitry;

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

    private static int max(int[] array, int from, int to) {
        int max = Integer.MIN_VALUE;
        for (int i = from; i <= to; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private static int maxSumCostSteps(int[] stairs, int maxStep) {
        int[] costOfSteps = new int[stairs.length];
        for (int i = 1; i < costOfSteps.length; i++) {
            int from = Math.max(0, i - maxStep);
            costOfSteps[i] = max(costOfSteps, from, i - 1) + stairs[i];
        }
        return costOfSteps[costOfSteps.length - 1];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] stairs = new int[in.nextInt() + 2];
        for (int i = 0; i < stairs.length; i++) {
            if (i == 0 || i == stairs.length - 1) {
                stairs[i] = 0;
                continue;
            }
            stairs[i] = in.nextInt();
        }
        int maxStep = in.nextInt();

        out.write(maxSumCostSteps(stairs, maxStep) + "\n");
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
