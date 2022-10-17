import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MouseAndGrain {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[][] matrix = new int[in.nextInt()][in.nextInt()];
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        int[][] calcRices = new int[matrix.length][matrix[0].length];
        calcRices[0][0] = matrix[0][0];
        for (int i = 1; i < matrix[0].length; i++) {
            calcRices[0][i] = calcRices[0][i - 1] + matrix[0][i];
            if (i == 1) {
                for (int j = 1; j < matrix.length; j++) {
                    calcRices[j][0] = calcRices[j - 1][0] + matrix[j][0];
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                calcRices[i][j] = Math.max(calcRices[i - 1][j], calcRices[i][j - 1]) + matrix[i][j];
            }
        }

        StringBuilder str = new StringBuilder();
        getPath(matrix.length - 1, matrix[0].length - 1, str, calcRices);

        out.println(str.reverse());
    }

    private static void getPath(int i, int j, StringBuilder str, int[][] calcRices) {
        if (i == 0 && j == 0) {
            return;
        }

        if (j == 0) {
            str.append('F');
            i--;
        } else if (i == 0) {
            str.append('R');
            j--;
        } else if (calcRices[i - 1][j] > calcRices[i][j - 1]) {
            str.append('F');
            i--;
        } else {
            str.append('R');
            j--;
        }

        getPath(i, j, str, calcRices);
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
