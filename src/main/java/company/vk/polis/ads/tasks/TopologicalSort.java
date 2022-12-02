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
public final class TopologicalSort {
    private TopologicalSort() {
        // Should not be instantiated
    }

    private enum Colors {
        WHITE,
        GREY,
        BLACK;
    }

    private static class Graph {
        private final ArrayList<Integer>[] graph;
        private final int vertices;

        private final ArrayList<Integer> result;
        private boolean flag;
        private final Colors[] visited;

        @SuppressWarnings("unchecked")
        public Graph(int v) {
            vertices = v;
            flag = false;
            result = new ArrayList<>();
            graph = new ArrayList[vertices];
            visited = new Colors[vertices];
            for (int i = 1; i < vertices; i++) {
                graph[i] = new ArrayList<>();
                visited[i] = Colors.WHITE;
            }
        }

        private void add(int vertices, int edges) {
            graph[vertices].add(edges);
        }

        private void dfs(int source) {
            visited[source] = Colors.GREY;
            for (int neighbour : graph[source]) {
                if (visited[neighbour] == Colors.GREY) {
                    flag = true;
                }
                if (visited[neighbour] == Colors.WHITE) {
                    dfs(neighbour);
                }
            }
            visited[source] = Colors.BLACK;
            result.add(source);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numVertices = in.nextInt();
        int numEdges = in.nextInt();
        Graph graph = new Graph(numVertices + 1);
        for (int i = 0; i < numEdges; i++) {
            graph.add(in.nextInt(), in.nextInt());
        }
        for (int i = 1; i < graph.vertices; i++) {
            if (graph.flag) {
                break;
            }
            if (graph.visited[i] == Colors.WHITE) { //todo валится
                graph.dfs(i);
            }
        }
        if (graph.flag) {
            out.println("-1");
        } else {
            Collections.reverse(graph.result);
            for (Integer element : graph.result) {
                out.print(element + " ");
            }
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
