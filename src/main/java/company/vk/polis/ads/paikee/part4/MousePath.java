package company.vk.polis.ads.paikee.part4;

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
public final class MousePath {
    private MousePath() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] field = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = in.nextInt();
            }
        }
        int[][] sumArray = new int[m][n];
        int i = m - 1;
        int j = 0;
        StringBuilder result = new StringBuilder();
        getMaxRoot(field, sumArray, i, j);
        while (i > 0 || j < n - 1) {
            if (j == n - 1 || i > 0 && sumArray[i - 1][j] >= sumArray[i][j + 1]) {
                result.append("F");
                i--;
            } else {
                result.append("R");
                j++;
            }
        }
        out.println(result.toString());
    }

    public static int getMaxRoot(int[][] field, int[][] sumArray, int i, int j) {
        if (i < 0 || j > field[0].length - 1) {
            return 0;
        }
        if (sumArray[i][j] != 0) {
            return sumArray[i][j];
        }
        int goFront = getMaxRoot(field, sumArray, i - 1, j);
        int goRight = getMaxRoot(field, sumArray, i, j + 1);
        sumArray[i][j] = field[i][j] + Math.max(goFront, goRight);
        return sumArray[i][j];
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