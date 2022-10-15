package company.vk.polis.ads.part4.denisstrizhkin.longestsequence;

import java.io.*;
import java.util.StringTokenizer;

// https://www.eolymp.com/ru/submissions/11801639
public class Main {
    private Main() {
        // Should not be instantiated
    }

    private static int[][] dArr;
    private static int[] seq1;
    private static int[] seq2;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        seq1 = new int[n];
        for (int i = 0; i < n; i++) {
            seq1[i] = in.nextInt();
        }

        n = in.nextInt();
        seq2 = new int[n];
        for (int i = 0; i < n; i++) {
            seq2[i] = in.nextInt();
        }

        dArr = new int[seq1.length + 1][seq2.length + 1];
        for (int i = 1; i <= seq1.length; i++) {
            for (int j = 1; j <= seq2.length; j++) {
                if (seq1[i - 1] == seq2[j - 1]) {
                    dArr[i][j] = dArr[i - 1][j - 1] + 1;
                } else {
                    dArr[i][j] = Math.max(dArr[i][j - 1], dArr[i - 1][j]);
                }
            }
        }

        out.println(dArr[seq1.length][seq2.length]);
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