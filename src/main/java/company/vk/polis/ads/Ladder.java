package company.vk.polis.ads;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Ladder {
    private Ladder() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int [] arr = new int[n + 2];
        arr[0] = 0;
        arr[n + 1] = 0;
        for (int i = 1; i < n + 1; ++i) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        int [] dp= new int[n + 2];
        for (int i = 1; i < n + 2; ++i) {
            dp[i] = arr[i] + dp[i - 1];
            for(int j = 2; j <= k; ++j) {
                if (i - j >= 0) {
                    dp[i] = Math.max(dp[i], arr[i] + dp[i - j]);
                } else {
                    break;
                }
            }
        }

        out.print(dp[n + 1]);
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

