package company.vk.polis.ads.workshop;

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
public final class Ropes {
    private Ropes() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            arr[i] = in.nextInt();
            sum += arr[i];
        }
        long l = 1;
        long r = ((sum / k) + 1);
        if (r == l) {
            out.println(0);
            return;
        }
        long houseCount = 0;
        while (l + 1 < r) {
            long mid = (l + r) >> 1;
            houseCount = 0;
            for (var length : arr) {
                houseCount += length / mid;
            }
            if (houseCount < k) {
                r = mid;
            } else {
                l = mid;
            }
        }

        houseCount = 0;
        for (var length : arr) {
            houseCount += length / r;
        }
        out.println((houseCount == k) ? r : l);
    }

    // 200 201
    // 12 11
    // 13 12
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

