import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class FordBellman {
    private FordBellman() {
        // Should not be instantiated
    }

    private static final int NONE = 30000;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] u = new int[m + 1];
        int[] v = new int[m + 1];
        int[] weight = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            u[i] = in.nextInt();
            v[i] = in.nextInt();
            weight[i] = in.nextInt();
        }
        int[] dist = new int[n + 1];
        fordBellman(n, m, u, v, weight, dist);
        for (int i = 1; i <= n; i++) {
            out.print(dist[i] + " ");
        }
    }

    private static void fordBellman(int n, int m, int[] u, int[] v, int[] weight, int[] dist) {
        Arrays.fill(dist, NONE);
        dist[1] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dist[u[j]] < NONE && dist[v[j]] > dist[u[j]] + weight[j]) {
                    dist[v[j]] = dist[u[j]] + weight[j];
                }
            }
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