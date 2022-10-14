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
// https://www.eolymp.com/ru/submissions/11771167
public final class Stairs {
    private Stairs() {
        // Should not be instantiated
    }

    private static int findMax(int[] stairsCost, int[] d, int stairIndex, int step) {
        int maxValue = Integer.MIN_VALUE;
        int downStep = step;
        for (int i = stairIndex - 1; i >= 0 && downStep > 0; i--) {
            if (maxValue < d[i]) {
                maxValue = d[i];
            }
            downStep--;
        }
        return maxValue;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stairsCost = new int[n + 2];
        stairsCost[0] = stairsCost[n - 1] = 0;
        for (int i = 1; i < n + 1; i++) {
            stairsCost[i] = in.nextInt();
        }
        int step = in.nextInt();

        int[] d = new int[n + 2];
        d[0] = 0;
        d[1] = d[0] + stairsCost[1];
        for (int i = 2; i < d.length; i++) {
            d[i] = findMax(stairsCost, d, i, step) + stairsCost[i];
        }
        out.println(d[d.length - 1]);
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
