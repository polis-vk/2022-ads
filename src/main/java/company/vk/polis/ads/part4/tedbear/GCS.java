package company.vk.polis.ads.part4.tedbear;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GCS {
    static int[] firstArray, secondArray ;
    static int[][] d;
    static int firstSize, secondSize;

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        firstSize = in.nextInt();
        firstArray = new int[firstSize + 1];

        for (int i = 1; i <= firstSize; i++) {
            firstArray[i] = in.nextInt();
        }

        secondSize = in.nextInt();
        secondArray = new int[secondSize + 1];

        for (int i = 1; i <= secondSize; i++) {
            secondArray[i] = in.nextInt();
        }

        d = new int[firstSize + 1][secondSize + 1];

        for(int i = 0; i <= firstSize; i++) {
            Arrays.fill(d[i], -1);
        }

        out.write(gcs(firstSize, secondSize) + "");
    }

    public static int gcs(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (d[m][n] != -1) {
            return d[m][n];
        }
        if (firstArray[m] == secondArray[n]) {
            return d[m][n] = 1 + gcs(m - 1, n - 1);
        } else {
            return d[m][n] = Math.max(gcs(m, n - 1), gcs(m - 1, n));
        }
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
