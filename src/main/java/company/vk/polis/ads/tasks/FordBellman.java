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
public final class FordBellman {
    private FordBellman() {
        // Should not be instantiated
    }

    private static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class Graph {
        private final List<List<Edge>> graph;
        private final Edge[] edge;
        private final int vertices;
        private final int[] dist; // массив для пути

        Graph(int vertices, int edges) {
            this.vertices = vertices;
            graph = new ArrayList<>(vertices);
            edge = new Edge[edges];
            dist = new int[vertices];
            for (int i = 0; i < edges; i++) {
                edge[i] = new Edge(0, 0, 0);
            }
            for (int i = 0; i < vertices; i++) {
                graph.add(new ArrayList<>());
            }
        }

        public void add(int i, int source, int destination, int weight) {
            edge[i] = new Edge(source, destination, weight);
        }

        public int[] fordBellman(int start) {
            Arrays.fill(dist, 30000);
            dist[start] = 0;
            for (int i = 0; i < vertices; i++) {
                // шаг релаксации
                for (Edge edge : edge) {
                    if (dist[edge.source] != 30000 && dist[edge.source] + edge.weight < dist[edge.destination]) {
                        dist[edge.destination] = dist[edge.source] + edge.weight;
                    }
                }
            }
            return dist;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numVertices = in.nextInt();
        int numEdges = in.nextInt();
        Graph graph = new Graph(numVertices + 1, numEdges + 1);

        int startVert = 1;
        for (int i = 0; i < numEdges; i++) {
            graph.add(i, in.nextInt(), in.nextInt(), in.nextInt());
        }
        int[] result = graph.fordBellman(startVert);
        for (int i = 1; i < result.length; i++) {
            out.print(result[i] + " ");
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
