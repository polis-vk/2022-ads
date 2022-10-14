package company.vk.polis.ads.iampolshin;

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
public final class MouseAndSeeds {
    private MouseAndSeeds() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/11785582
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] costs = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                costs[i][j] = in.nextInt();
            }
        }

        int[][] solutionsTable = new int[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(costs[i], 0, solutionsTable[i], 0, n);
        }

        for (int j = 1; j < n; j++) {
            solutionsTable[m - 1][j] += solutionsTable[m - 1][j - 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            solutionsTable[i][0] += solutionsTable[i + 1][0];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                solutionsTable[i][j] = Math.max(solutionsTable[i + 1][j], solutionsTable[i][j - 1]) + costs[i][j];
            }
        }
        StringBuilder restoredPath = new StringBuilder();
        restorePath(restoredPath, solutionsTable);
        out.println(restoredPath);
    }

    private static void restorePath(StringBuilder path, int[][] solutionsTable) {
        int i = 0;
        int j = solutionsTable[0].length - 1;
        while (i < solutionsTable.length - 1 && j > 0) {
            if (solutionsTable[i][j - 1] >= solutionsTable[i + 1][j]) {
                path.append("R");
                j--;
            } else {
                path.append("F");
                i++;
            }
        }
        while (i < solutionsTable.length - 1) {
            path.append("F");
            i++;
        }
        while (j > 0) {
            path.append("R");
            j--;
        }
        path.reverse();
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

