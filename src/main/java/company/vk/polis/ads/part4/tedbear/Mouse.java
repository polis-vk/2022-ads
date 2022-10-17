package company.vk.polis.ads.part4.tedbear;

import java.io.*;
import java.util.StringTokenizer;

public class Mouse {
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] field = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == m || j == 0) {
                    field[i][j] = -1;
                } else {
                    field[i][j] = in.nextInt();
                }
            }
        }

        field[m][1] = 0;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j <= n; j++) {
                field[i][j] = Math.max(field[i + 1][j], field[i][j - 1]) + field[i][j];
            }
        }

        StringBuilder result = new StringBuilder();
        int posX = n;
        int posY = 0;

        while (posX > 1 || posY != m - 1) {
            if (field[posY + 1][posX] > field[posY][posX - 1]) {
                result.append('F');
                posY++;
            } else {
                result.append('R');
                posX--;
            }
        }

        out.write(result.reverse().toString());

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
