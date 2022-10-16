package company.vk.polis.ads.part4.tedbear;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Stairs {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int lengthOfStairs = in.nextInt() + 2;
        int[] costs = new int[lengthOfStairs];

        for (int i = 1; i < lengthOfStairs - 1; i++) {
            costs[i] = in.nextInt();
        }

        int k = in.nextInt();

        out.write(new StairsBottomUp(costs, k).solveStairs() + "");
    }

    private static class StairsBottomUp {
        private final int[] costs;
        private final int maxStep;

        public StairsBottomUp(int[] costs, int maxStep) {
            this.costs = costs;
            this.maxStep = maxStep;
        }

        public long solveStairs() {
            int numSteps = costs.length;
            long[] maxCosts = new long[numSteps];

            Arrays.fill(maxCosts, Long.MIN_VALUE);
            maxCosts[0] = 0L;

            for (int currentStep = 1; currentStep < numSteps; currentStep++) {
                for (int i = 0; i < maxStep; i++) {
                    if (currentStep - (maxStep - i) >= 0) {
                        int prevStep = currentStep - (maxStep - i);
                        maxCosts[currentStep] = Math.max(maxCosts[currentStep], maxCosts[prevStep] + costs[currentStep]);
                    }
                }

                int prevStep = currentStep - 1;
                maxCosts[currentStep] = Math.max(maxCosts[currentStep], maxCosts[prevStep] + costs[currentStep]);
            }

            return maxCosts[numSteps - 1];
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
