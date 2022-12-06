package company.vk.polis.ads.part10.denisstrizhkin.fordbellman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private final static int MAX_DIST = 30_000;
    private static int[][] edges;

    private Main() {
        // Should not be instantiated
    }

    private static int[] solve(int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, MAX_DIST);
        dist[1] = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean relaxFlag = false;
            for (int[] edge : edges) {
                if (dist[edge[0]] < MAX_DIST) {
                    if (dist[edge[1]] > dist[edge[0]] + edge[2]) {
                        dist[edge[1]] = dist[edge[0]] + edge[2];
                        relaxFlag = true;
                    }
                }
            }
            if (!relaxFlag) {
                break;
            }
        }

        return dist;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
            edges[i][2] = in.nextInt();
        }

        int[] dist = solve(n);
        for (int i = 1; i <= n; i++) {
            out.print(dist[i] + " ");
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