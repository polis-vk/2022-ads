package company.vk.polis.ads.part5.denisstrizhkin.rope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.eolymp.com/ru/submissions/12007040
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final Main.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] ropes = new int[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = in.nextInt();
        }

        int l = 0;
        int r = 100 * 1000 * 100 + 1;
        while (l < r - 1) {
            int mid = (l + r) >>> 1;

            int houseCount = Arrays.stream(ropes).map(length -> length / mid).sum();
            if (houseCount < k) {
                r = mid;
            } else {
                l = mid;
            }
        }

        out.println(l);
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
        final Main.FastScanner in = new Main.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}