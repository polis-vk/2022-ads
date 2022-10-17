package company.vk.polis.ads.part4.aosandy;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Наибольшая общая подпоследовательность
 */
public final class Task3 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt() + 1;
        int[] a = new int[n];
        for (int i = 1; i < n; i++) {
            a[i] = in.nextInt();
        }
        int m = in.nextInt() + 1;
        int[] b = new int[m];
        for (int i = 1; i < m; i++) {
            b[i] = in.nextInt();
        }
        int[][] maxCommon = new int[n][m];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    maxCommon[i][j] = maxCommon[i - 1][j - 1] + 1;
                } else {
                    maxCommon[i][j] = Math.max(maxCommon[i - 1][j], maxCommon[i][j - 1]);
                }
            }
        }

        out.println(maxCommon[n - 1][m - 1]);
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
