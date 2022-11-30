package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
//https://www.eolymp.com/ru/submissions/12336236
public final class GraphCondensation {

    private GraphCondensation() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexes = in.nextInt();
        int edges = in.nextInt();
        Graph graph = new Graph(vertexes);
        Graph transposedGraph = new Graph(vertexes);

        for (int i = 1; i <= vertexes; i++) {
            graph.adjacencyList[i] = new HashSet<>();
            transposedGraph.adjacencyList[i] = new HashSet<>();
            graph.color[i] = Color.WHITE;
            transposedGraph.color[i] = Color.WHITE;
        }

        for (int i = 1; i <= edges; i++) {
            int fromVertex = in.nextInt();
            int toVertex = in.nextInt();
            graph.adjacencyList[fromVertex].add(toVertex);
            transposedGraph.adjacencyList[toVertex].add(fromVertex);
        }

        topSort(graph, vertexes);

        int[] belongsComponent = new int[vertexes + 1];
        int currentComponent = 1;
        for (Integer curVertex : graph.stack) {
            if (transposedGraph.color[curVertex] == Color.WHITE) {
                dfsOnTransposed(transposedGraph, curVertex, belongsComponent, currentComponent++);
            }
        }

        @SuppressWarnings("unchecked")
        Set<Integer>[] condensedGraphEdges = new Set[currentComponent];
        for (int i = 1; i <= vertexes; i++) {
            for (Integer to : graph.adjacencyList[i]) {
                if (belongsComponent[to] != belongsComponent[i]) {
                    if (condensedGraphEdges[belongsComponent[to]] == null) {
                        condensedGraphEdges[belongsComponent[to]] = new HashSet<>();
                    }
                    condensedGraphEdges[belongsComponent[to]].add(belongsComponent[i]);
                }
            }
        }
        out.println(Arrays.stream(condensedGraphEdges).filter(Objects::nonNull).mapToLong(Collection::size).sum());
    }

    @SuppressWarnings("unchecked")
    private static class Graph {
        private final Set<Integer>[] adjacencyList;
        private final Color[] color;
        private final Deque<Integer> stack = new LinkedList<>();

        public Graph(int vertex) {
            this.adjacencyList = new Set[vertex + 1];
            this.color = new Color[vertex + 1];
        }
    }

    private static void topSort(Graph graph, int vertex) {
        for (int i = 1; i <= vertex; i++) {
            if (graph.color[i] == Color.WHITE) {
                dfs(graph, i);
            }
        }
    }

    private static void dfs(Graph graph, int vertex) {
        graph.color[vertex] = Color.GREY;
        for (Integer toVertex : graph.adjacencyList[vertex]) {
            if (graph.color[toVertex] == Color.WHITE) {
                dfs(graph, toVertex);
            }
        }
        graph.color[vertex] = Color.BLACK;
        graph.stack.addFirst(vertex);
    }

    private static void dfsOnTransposed(Graph graph, int vertex, int[] belongsComponent, int currentComponent) {
        graph.color[vertex] = Color.GREY;
        belongsComponent[vertex] = currentComponent;
        for (Integer toVertex : graph.adjacencyList[vertex]) {
            if (graph.color[toVertex] == Color.WHITE) {
                dfsOnTransposed(graph, toVertex, belongsComponent, currentComponent);
            }
        }
    }

    private enum Color {
        WHITE,
        GREY,
        BLACK
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
