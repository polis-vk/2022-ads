package company.vk.polis.ads.part4.nikitry;

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
public final class SubSequence {


    private static void solve(final FastScanner in, final PrintWriter out) {

        int[] sequence1 = new int[in.nextInt()];
        for (int i = 0; i < sequence1.length; i++) {
            sequence1[i] = in.nextInt();
        }

        int[] sequence2 = new int[in.nextInt()];
        for (int i = 0; i < sequence2.length; i++) {
            sequence2[i] = in.nextInt();
        }

        int[][] lcs = new int[sequence1.length + 1][sequence2.length + 1];

        for (int i = 0; i < lcs.length; i++) {
            lcs[i][0] = 0;
        }

        for (int i = 0; i < lcs[0].length; i++) {
            lcs[0][i] = 0;
        }

        for (int i = 1; i < lcs.length; i++) {
            for (int j = 1; j < lcs[0].length; j++) {
                if (sequence1[i - 1] == sequence2[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    continue;
                }
                lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            }
        }

        int result = lcs[lcs.length - 1][lcs[0].length - 1];
        out.write(result + "\n");
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
