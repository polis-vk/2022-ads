package company.vk.polis.ads;

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

// Submission link: https://www.eolymp.com/ru/submissions/11874937
public final class Ropes {
    private Ropes() {
        // Should not be instantiated
    }

    private static int countInArrayByKey(int[] a, int key) {
        int count = 0;
        for (int i : a) {
            count += i / key;
        }
        return count;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = new int[n];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            if (a[i] > max) {
                max = a[i];
            }
        }

        int l = 1;
        int r = max + 1;
        int key = 0;
        int count;
        int res = 0;

        while (l < r) {
            int mid = (l + r) >>> 1;
            key = mid;
            count = countInArrayByKey(a, key);
            if (count > k) {
                l = mid + 1;
            } else if (count < k) {
                r = mid;
            } else {
                res = key;
                l = mid + 1;
            }
        }

        if (res == 0) {
            while (key > 0 && countInArrayByKey(a, key) < k) {
                key--;
            }
            if (key > 0) {
                res = key;
            }
        }

        out.println(res);
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
