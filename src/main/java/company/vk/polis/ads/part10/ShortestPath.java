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

public final class ShortestPath {
    private ShortestPath() {
        // Should not be instantiated
    }

    private static final int INF = Integer.MAX_VALUE;

    private static class Edge implements Comparable<Edge> {
        int to;
        int weight;

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

        int S = in.nextInt();
        int F = in.nextInt();

        List<Edge>[] graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int weight = in.nextInt();

            graph[start].add(new Edge(start, weight));
            graph[end].add(new Edge(end, weight));
        }

        int[] parent = new int[N + 1];
        int[] dist = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            dist[i] = INF;
        }

        int from = S;
        int to = F;

        Queue<Integer> tops = new LinkedList<>();
        dist[from] = 0;
        tops.offer(from);

        while (!tops.isEmpty()) {

            int top = tops.poll();

            for (Edge edge : graph[top]) {
                if (dist[edge.to] > dist[top] + edge.weight) {
                    parent[edge.to] = top;
                    dist[edge.to] = dist[top] + edge.weight;
                    tops.offer(edge.to);
                }
            }
        }

        out.println(dist[to]);

        Deque<Integer> deque = new LinkedList<>();

        while (to != 0) {
            deque.addFirst(to);
            to = parent[to];
        }

        while (!deque.isEmpty()) {
            System.out.print(deque.poll() + " ");
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