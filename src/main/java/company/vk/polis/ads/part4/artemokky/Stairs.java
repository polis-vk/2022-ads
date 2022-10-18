package company.vk.polis.ads.part4.artemokky;

import java.io.*;
import java.util.*;


public class Stairs {
    static void solve(FastScanner in, PrintWriter out) {
        int stairCount = in.nextInt();

        int[] stairsCost = new int[stairCount + 2];
        int[] res = new int[stairCount + 2];

        for (int i = 1; i < stairCount + 1; ++i) {
            stairsCost[i] = in.nextInt();
        }

        stairsCost[0] = 0;
        stairsCost[stairCount + 1] = 0;

        int stepLong = in.nextInt();

        for (int i = 1; i < stairCount + 2; i++) {
            res[i] = stairsCost[i] + res[i - 1];
            for (int j = 2; j <= stepLong && i - j >= 0; j++) {
                res[i] = Math.max(res[i], stairsCost[i] + res[i - j]);
            }
        }

        out.print(res[stairCount + 1]);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}