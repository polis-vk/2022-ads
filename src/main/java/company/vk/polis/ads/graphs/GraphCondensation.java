package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
//https://www.eolymp.com/ru/submissions/12308892
public final class GraphCondensation {

    private GraphCondensation() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertex = in.nextInt();
        int edge = in.nextInt();
        Graph graph = new Graph(vertex);
        Graph transposedGraph = new Graph(vertex);

        for (int i = 1; i <= vertex; i++) {
            graph.adjacencyList[i] = new HashSet<>();
            transposedGraph.adjacencyList[i] = new HashSet<>();
            graph.color[i] = Color.WHITE;
            transposedGraph.color[i] = Color.WHITE;
        }

        for (int i = 1; i <= edge; i++) {
            int fromVertex = in.nextInt();
            int toVertex = in.nextInt();
            graph.adjacencyList[fromVertex].add(toVertex);
            transposedGraph.adjacencyList[toVertex].add(fromVertex);
        }

        //topsort
        for (int i = 1; i <= vertex; i++) {
            if (graph.color[i] == Color.WHITE) {
                dfs(graph, i);
            }
        }

        Map<Integer, Set<Integer>> componentVertexesMap = new HashMap<>();
        int currentComponent = 1;
        for(Integer curVertex: graph.stack) {
            if (graph.color[curVertex] == Color.WHITE) {
                dfsOnTransposed(graph, vertex, currentComponent++);
            }
        }
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

    private static void dfsOnTransposed(Graph transposedGraph, int vertex, int currentComponent) {
        transposedGraph.color[vertex] = Color.GREY;


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
