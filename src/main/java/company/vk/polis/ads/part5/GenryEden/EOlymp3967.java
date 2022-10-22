package company.vk.polis.ads.part5.GenryEden;

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
public final class EOlymp3967 {
    private EOlymp3967() {
        // Should not be instantiated
    }
    // submission url: https://www.eolymp.com/ru/submissions/11896402
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] lengths = new int[n];
        int l = 0;
        int r = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            lengths[i] = in.nextInt();
        }

        while (l != r - 1) {
            int mid = (l + r) >>> 1;
            int count = getCount(lengths, mid);
            if (count < k) {
                r = mid;
            } else {
                l = mid;
            }
        }
        out.println(l);
    }

    private static int getCount(int[] array, int val) {
        int ans = 0;
        for (int c: array) {
            ans += c / val;
        }
        return ans;
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
