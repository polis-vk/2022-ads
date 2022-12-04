package company.vk.polis.ads.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

// Submission link: https://www.eolymp.com/ru/submissions/12362476
public final class MinimalCarcass {
    private MinimalCarcass() {
        // Should not be instantiated
    }

    private static record Edge(int from, int to, int weight) {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int weight = in.nextInt();
            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges, Comparator.comparingInt(o -> o.weight));

        int[] tree = IntStream.range(0, n + 1).toArray();

        int weight = 0;

        for (Edge edge : edges) {
            if (tree[edge.from] == tree[edge.to]) {
                continue;
            }

            int from = tree[edge.from];
            int to = tree[edge.to];
            weight += edge.weight;

            for (int i = 1; i <= n; i++) {
                if (tree[i] == to) {
                    tree[i] = from;
                }
            }
        }

        out.println(weight);
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
