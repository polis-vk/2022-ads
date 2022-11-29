package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

public final class TopologicalSort {

    private TopologicalSort() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertex = in.nextInt();
        int edge = in.nextInt();
        Graph graph = new Graph(vertex);

        for (int i = 1; i <= vertex; i++) {
            graph.adjacencyList[i] = new HashSet<>();
            graph.color[i] = Color.WHITE;
        }

        for (int i = 1; i <= edge; i++) {
            int fromVertex = in.nextInt();
            int toVertex = in.nextInt();
            graph.adjacencyList[fromVertex].add(toVertex);
        }

        for (int i = 1; i <= vertex; i++) {
            if (graph.color[i] == Color.WHITE && !graph.isCyclic) {
                graph.dfs(i);
            }
        }

        if (graph.isCyclic) {
            out.println(-1);
        } else {
            while (!graph.stack.isEmpty()) {
                out.print(graph.stack.pollFirst() + " ");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static class Graph {
        private final Set<Integer>[] adjacencyList;
        private final Color[] color;
        private final Deque<Integer> stack = new LinkedList<>();
        private boolean isCyclic;

        public Graph(int vertex) {
            this.adjacencyList = new Set[vertex + 1];
            this.color = new Color[vertex + 1];
        }

        private void dfs(int vertex) {
            if (isCyclic) {
                return;
            }
            color[vertex] = Color.GREY;
            for (Integer toVertex : adjacencyList[vertex]) {
                if (color[toVertex] == Color.WHITE) {
                    dfs(toVertex);
                } else if (color[toVertex] == Color.GREY) {
                    isCyclic = true;
                    return;
                }
            }
            color[vertex] = Color.BLACK;
            stack.addFirst(vertex);
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
