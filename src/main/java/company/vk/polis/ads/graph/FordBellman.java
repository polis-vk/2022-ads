package company.vk.polis.ads.graph;

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

// Submission link: https://www.eolymp.com/ru/submissions/12359996
public final class FordBellman {
    private static final int INFINITY = 30000;

    private FordBellman() {
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

        int[] distance = new int[n + 1];

        Arrays.fill(distance, INFINITY);

        distance[1] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.from] != INFINITY) {
                    int dist = distance[edge.from] + edge.weight;
                    if (dist < distance[edge.to]) {
                        distance[edge.to] = dist;
                    }
                }
            }
        }

        Arrays.stream(distance)
                .skip(1)
                .forEach(value -> out.print(value + " "));
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
