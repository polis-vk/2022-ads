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
public final class Mouse {
    private Mouse() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j] = in.nextInt();
            }
        }

        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                if (i == m - 1) {
                    if (j != 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
                } else {
                    dp[i][j] += (j == 0) ? dp[i + 1][j] : Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }



        StringBuilder ans = new StringBuilder();
        int i = 0;
        int j = n - 1;
        while (i != m - 1 && j != 0) {
            if (dp[i + 1][j] > dp[i][j - 1]) {
                ans.insert(0, 'F');
                i++;
            } else {
                ans.insert(0, 'R');
                j--;
            }
        }

        while (i++ != m - 1) {
            System.out.print('F');
        }

        while(j-- != 0) {
            System.out.print('R');
        }

        System.out.println(ans);
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