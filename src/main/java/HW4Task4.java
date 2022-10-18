import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW4Task4 {
    private HW4Task4() {
        // Should not be instantiated
    }

    // Лесенка: https://www.eolymp.com/ru/submissions/11845468
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] c = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            c[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] d = new int[n + 2];
        d[0] = 0;
        int currentMax = 0;
        for (int i = 1; i < k; i++) {
            d[i] = currentMax + c[i];
            if (d[i] > currentMax) {
                currentMax = d[i];
            }
        }
        for (int i = k; i <= n + 1; i++) {
            d[i] = currentMax + c[i];
            currentMax = d[i];
            for (int j = 1; j < k; j++) {
                if (d[i - j] > currentMax) {
                    currentMax = d[i - j];
                }
            }
        }
        out.println(d[n + 1]);
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
