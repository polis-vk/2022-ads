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
// https://www.eolymp.com/ru/submissions/11822345
public final class MouseAndSeeds {

    private static void solve(final FastScanner in, final PrintWriter out) {

        int rows = in.nextInt() + 1;
        int columns = in.nextInt() + 1;
        int[][] field = new int[rows][columns];
        for (int i = 0; i < field.length; i++) {
            field[i][0] = -1;
        }
        for (int i = 0; i < field[0].length; i++) {
            field[0][i] = -1;
        }
        for (int i = field.length - 1; i >= 1; i--) {
            for (int j = 1; j < field[0].length; j++) {
                field[i][j] = in.nextInt();
            }
        }

        for (int i = 2; i < field.length; i++) {
            field[i][1] = field[i][1] + field[i - 1][1];
        }
        for (int i = 2; i < field.length; i++) {
            field[1][i] = field[1][i] + field[1][i - 1];
        }

        for (int i = 2; i < field.length; i++) {
            for (int j = 2; j < field[0].length; j++) {
                field[i][j] = Math.max(field[i - 1][j], field[i][j - 1]) + field[i][j];
            }
        }

        StringBuilder path = new StringBuilder();
        int index1 = field.length - 1;
        int index2 = field[0].length - 1;
        while (index1 + index2 > 2) {
            if (field[index1 - 1][index2] > field[index1][index2 - 1]) {
                path.append('F');
                index1--;
            } else {
                path.append('R');
                index2--;
            }
        }
        out.println(path.reverse());
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