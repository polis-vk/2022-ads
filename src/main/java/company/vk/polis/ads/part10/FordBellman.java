package company.vk.polis.ads.part10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

public final class FordBellman {
    private FordBellman() {
        // Should not be instantiated
    }

    private static final int INF = Integer.MAX_VALUE;

    private static class Edge implements Comparable<Edge> {
        public int to;
        public int weight;

        public Edge(int to, int weight) {
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

        List<Edge>[] graph = new List[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int fromTop = in.nextInt();
            int toTop = in.nextInt();
            int length = in.nextInt();
            graph[fromTop].add(new Edge(toTop, length));
        }

        Queue<Integer> tops = new LinkedList<>();

        int[] dist = new int[graph.length];
        dist[1] = 0;
        for (int i = 2; i < dist.length; i++) {
            dist[i] = INF;
        }

        for (int i = 0; i < graph.length - 2; i++) {

            tops.offer(1);

            while (!tops.isEmpty()) {

                int top = tops.poll();

                for (Edge neighborEdge : graph[top]) {
                    if (dist[neighborEdge.to] > dist[top] + neighborEdge.weight) {
                        dist[neighborEdge.to] = dist[top] + neighborEdge.weight;
                        tops.offer(neighborEdge.to);
                    }
                }
            }
        }

        for (int i = 1; i < dist.length; i++) {
            out.print(dist[i] + " ");
        }

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