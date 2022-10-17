package company.vk.polis.ads.part4.aosandy;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Лесенка
 */
public final class Task4 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stair = new int[n + 2];
        for (int i = 1; i < n + 1; i++) {
            stair[i] = in.nextInt();
        }
        int maxStep = in.nextInt();
        int[] d = new int[n + 2];

        d[0] = 0;
        d[n + 1] = 0;
        for (int i = 1; i < n + 2; i++) {
            int max = Integer.MIN_VALUE;
            int start = Math.max(0, i - maxStep);
            for (int j = start; j < i; j++) {
                if (d[j] > max) {
                    max = d[j];
                }
            }
            d[i] = max + stair[i];
        }
        out.println(d[n + 1]);
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
