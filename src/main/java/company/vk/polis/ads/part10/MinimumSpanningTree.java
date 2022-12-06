package company.vk.polis.ads.part10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import java.util.Queue;
import java.util.PriorityQueue;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

public final class MinimumSpanningTree {
    private MinimumSpanningTree() {
        // Should not be instantiated
    }

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return weight - e.weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        int N = in.nextInt();
        int M = in.nextInt();

        Queue<Edge> edges = new PriorityQueue<>(M);

        for (int i = 0; i < M; i++) {
            edges.add(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }

        int weight = 0;

        int[] s = new int[N];
        for (int i = 0; i < N; i++) {
            s[i] = i;
        }

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();

            if (s[edge.from] != s[edge.to]) {

                int f = s[edge.from];
                int t = s[edge.to];

                for (int i = 0; i < N; i++) {
                    if (s[i] == t) {
                        s[i] = f;
                    }
                }

                weight += edge.weight;
            }
        }

        out.println(weight);

        // Write me
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