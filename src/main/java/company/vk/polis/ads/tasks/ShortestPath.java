package company.vk.polis.ads.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class ShortestPath {
    private ShortestPath() {
        // Should not be instantiated
    }

    private static class Edge {
        int source;
        int weight;

        public Edge(int source, int weight) {
            this.source = source;
            this.weight = weight;
        }
    }

    private static class Graph {
        private final List<List<Edge>> graph;
        private final int vertices;
        private final int[] visited; // массив пометок
        private final int[] ancestors; // массив предков
        private final int[] dist; // массив для пути

        Graph(int vertices) {
            this.vertices = vertices;
            graph = new ArrayList<>(vertices);
            visited = new int[vertices];
            ancestors = new int[vertices];
            dist = new int[vertices];
            for (int i = 0; i < vertices; i++) {
                graph.add(new ArrayList<>());
            }
        }

        public void add(int source, int destination, int weight) {
            graph.get(source).add(new Edge(destination, weight));
            graph.get(destination).add(new Edge(source, weight));
        }

        public boolean dijkstra(int start, int end) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            queue.add(start);
            Arrays.fill(dist, Integer.MAX_VALUE); // устанавливаем расстояние до всех вершин
            Arrays.fill(ancestors, -1);
            dist[start] = 0;

            while (!queue.isEmpty()) {
                int vert = queue.poll();
                for (Edge edge : graph.get(vert)) {
                    int weight = dist[vert] + edge.weight;
                    // шаг релаксации
                    if (dist[edge.source] > weight) {
                        dist[edge.source] = weight;
                        visited[edge.source] = vert;
                        queue.add(edge.source);
                    }
                }
            }
            return visited[end] != 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numVertices = in.nextInt();
        int numEdges = in.nextInt();
        Graph graph = new Graph(numVertices + 1);

        int start = in.nextInt(); // номера вершин между которыми нужно найти длину пути
        int finish = in.nextInt();

        int peak; // начальная вершина
        int finishPeak; // конечная вершина (peak -> weight -> finishPeak)
        int weight; // вес

        for (int i = 0; i < numEdges; i++) {
            peak = in.nextInt();
            finishPeak = in.nextInt();
            weight = in.nextInt();
            graph.add(peak, finishPeak, weight);
        }
        if (!graph.dijkstra(start, finish)) {
            out.println("-1");
        }
        // получение пути
        Stack<Integer> path = new Stack<>();
        path.add(finish);
        int currentVert = finish;
        while (graph.visited[currentVert] != 0) {
            currentVert = graph.visited[currentVert];
            path.add(currentVert);
        }
        path.add(graph.dist[finish]);
        out.println(path.pop());
        while (!path.isEmpty()) {
            out.print(path.pop() + " ");
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
