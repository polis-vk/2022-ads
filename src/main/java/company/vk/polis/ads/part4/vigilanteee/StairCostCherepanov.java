package company.vk.polis.ads.part4.vigilanteee;

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
public final class StairCostCherepanov {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numberOfStairs = in.nextInt() + 2;
        int[] stairCost = new int[numberOfStairs];
        for (int i = 1; i < stairCost.length - 1; i++) {
            stairCost[i] = in.nextInt();
        }
        int maxStepSize = in.nextInt();
        out.println(calcStairCost(stairCost, maxStepSize));
    }

    private static int calcStairCost(int[] stairCost, int maxStepSize) {
        int[] d = new int[stairCost.length];
        for (int i = 1; i < d.length; i++) {
            int max = Integer.MIN_VALUE;
            int previous = i - 1;
            while (previous >= Math.max(i - maxStepSize, 0)) {
                if (d[previous] > max) {
                    max = d[previous];
                }
                previous--;
            }
            d[i] = max + stairCost[i];
        }
        return d[d.length - 1];
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
