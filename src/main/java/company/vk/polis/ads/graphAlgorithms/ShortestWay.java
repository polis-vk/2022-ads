package company.vk.polis.ads.graphAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

//https://www.eolymp.com/ru/submissions/12376387
public final class ShortestWay {

    private ShortestWay() {
        // Should not be instantiated
    }

    @SuppressWarnings("unchecked")
    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertices = in.nextInt();
        int edges = in.nextInt();
        int start = in.nextInt();
        int end = in.nextInt();
        Set<Edge>[] graph = new Set[vertices + 1];
        for (int i = 1; i <= vertices; i++) {
            graph[i] = new HashSet<>();
        }
        for (int i = 1; i <= edges; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int weight = in.nextInt();
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }


        Map<Integer, int[]> result = dijkstra(graph, start, end, vertices);
        int key = result.keySet().iterator().next();
        if (key == Integer.MAX_VALUE) {
            out.println(-1);
            return;
        }
        out.println(key);
        int[] value = result.get(key);
        Deque<Integer> restorePath = new LinkedList<>();
        int prevVertex = value[end];
        restorePath.addFirst(end);
        while (prevVertex != 0) {
            restorePath.addFirst(prevVertex);
            prevVertex = value[prevVertex];
        }
        restorePath.forEach(e -> out.print(e + " "));
    }

    private static Map<Integer, int[]> dijkstra(Set<Edge>[] graph, int start, int end, int vertices) {
        int[] dist = new int[vertices + 1];
        int[] prev = new int[vertices + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (Edge edge : graph[vertex]) {
                int distance = dist[vertex] + edge.weight;
                if (distance < dist[edge.to]) {
                    queue.add(edge.to);
                    dist[edge.to] = distance;
                    prev[edge.to] = vertex;
                }
            }
        }
        return Collections.singletonMap(dist[end], prev);
    }

    private static final class Edge implements Comparable<Edge> {
        private final int to;
        private final int weight;

        private Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge otherEdge) {
            return Integer.compare(this.weight, otherEdge.weight);
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
