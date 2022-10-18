package company.vk.polis.ads.task2;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.eolymp.com/ru/submissions/11836871
 * @author Dmitry Schitinin
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] array = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                array[i][j] = in.nextInt();
            }
        }
        for(int i = m - 2;  i >= 0; i--) {
            array[i][0] += array[i + 1][0];
        }
        for(int i = 1;  i < n; i++) {
            array[m - 1][i] += array[m - 1][i - 1];
        }

        for(int i = m - 2 ; i >= 0 ; i--) {
            for(int j = 1; j < n;  j++) {
                array[i][j] += Math.max(array[i + 1][j], array[i][j - 1]);
            }
        }
        int i = 0, j = n - 1;
        StringBuilder answer = new StringBuilder();
        for(int k = 1; k <= m + n - 2; k++) {
            if(i == m - 1) {
                j -= 1;
                answer.append('R');
                continue;
            }
            if(j == 0) {
                i += 1;
                answer.append('F');
                continue;
            }
            if(array[i + 1][j] > array[i][j - 1]) {
                i += 1;
                answer.append('F');
            }
            else {
                j -= 1;
                answer.append('R');
            }
        }
        answer.reverse();

        out.println(answer.toString());
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
