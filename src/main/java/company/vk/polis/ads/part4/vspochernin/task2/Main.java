package company.vk.polis.ads.part4.vspochernin.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * https://www.eolymp.com/ru/submissions/11761098
 *
 * @author Dmitry Schitinin
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] grains = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grains[m - 1 - i][j] = in.nextInt();
            }
        }

        int[][] result = new int[m][n];
        result[0][0] = grains[0][0];

        for (int i = 1; i < m; i++) { // Идем по левой стенке вверх.
            result[i][0] = result[i - 1][0] + grains[i][0];
        }
        for (int j = 1; j < n; j++) { // Идем по нижней стенке вправо.
            result[0][j] = result[0][j - 1] + grains[0][j];
        }

        for (int i = 1; i < m; i++) { // Заполняем все поле.
            for(int j = 1; j < n; j++) {
                result[i][j] = Math.max(result[i-1][j], result[i][j-1]) + grains[i][j];
            }
        }

        // Восстанавливаем ответ.

        StringBuilder stringBuilder = new StringBuilder();
        int i = m - 1;
        int j = n - 1;

        while (i > 0 && j > 0) {
            if (result[i][j-1] > result[i-1][j]) {
                stringBuilder.append("R");
                j--;
            } else {
                stringBuilder.append("F");
                i--;
            }
        }
        while (i > 0) {
            stringBuilder.append("F");
            i--;
        }
        while (j > 0) {
            stringBuilder.append("R");
            j--;
        }

        out.println(stringBuilder.reverse().toString());
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
