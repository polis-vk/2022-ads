package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class FordBellman{
    private FordBellman() {
        // Should not be instantiated
    }


    private static final int INF = 1_000_000_000;
    private static void bellman(List<Edge> edges, int n) {
        int [] d = new int[n + 1];
        Arrays.fill(d, INF);

        d[1] = 0;
        for (int i = 1; i < n; ++i) {
            edges.forEach(e -> {
              if (d[e.v] < INF) {
                  d[e.u] = Math.min(d[e.u], d[e.v] + e.w);
              }
            });
        }

        for (int i = 1; i <= n; ++i) {
            if (d[i] == INF) {
                System.out.print(30000 + " ");
            } else {
                System.out.print(d[i] + " ");
            }
        }

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<Edge> edges = new ArrayList<>(m);
        for (int i = 0; i < m; ++i) {
            int v = in.nextInt();
            int u = in.nextInt();
            int w = in.nextInt();
            edges.add(new Edge(v, u, w));
        }

        bellman(edges, n);
    }

    private static class Edge  {
        int v;
        int u;
        int w;

        public Edge(int v, int u, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
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

