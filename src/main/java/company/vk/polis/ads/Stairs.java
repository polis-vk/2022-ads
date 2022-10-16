package company.vk.polis.ads;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Stairs {

    // https://www.eolymp.com/ru/submissions/11790252

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        int[] costs = new int[n + 2];
        costs[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            costs[i] = in.nextInt();
        }
        int k = in.nextInt();

        long[] maxCosts = new long[n + 2];

        Arrays.fill(maxCosts, Long.MIN_VALUE);
        maxCosts[0] = 0L;


        for (int curStep = 1; curStep < maxCosts.length; curStep++) {
            for (int j = 1; j <= k; j++) {
                if (curStep - j >= 0) {
                    int prevStep = curStep - j;
                    maxCosts[curStep] = Math.max(maxCosts[curStep], maxCosts[prevStep] + costs[curStep]);
                }
            }
        }
        out.println(maxCosts[maxCosts.length - 1]);
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
