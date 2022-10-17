package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//Наибольшая общая подпоследовательность

public class LCS {
    private LCS() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] seqN = new int[n];
        for (int i = 0; i < n; i++) {
            seqN[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] seqM = new int[m];
        for (int i = 0; i < m; i++) {
            seqM[i] = in.nextInt();
        }
        int[][] d = new int[n + 1][m + 1];
        out.println(lcs(seqN, seqM, d));
    }

    private static int lcs(int[] a, int[] b, int[][] d) {
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                if (a[i - 1] == b[j - 1]) {
                    d[i][j] = Math.max(d[i][j], d[i - 1][j - 1] + 1);
                }
            }
        }
        return d[a.length][b.length];
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
