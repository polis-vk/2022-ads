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
//https://www.eolymp.com/ru/submissions/12321487
public final class Cycles {
    private static int minCycledVertex = Integer.MAX_VALUE;

    private Cycles() {
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
            int vertex1 = in.nextInt();
            int vertex2 = in.nextInt();
            graph.adjacencyList[vertex1].add(vertex2);
            graph.adjacencyList[vertex2].add(vertex1);
        }

        for (int i = 1; i <= vertex; i++) {
            if (graph.color[i] == Color.WHITE) {
                graph.dfs(i, -1);
            }
        }

        if (minCycledVertex == Integer.MAX_VALUE) {
            out.println("No");
        } else {
            out.println("Yes");
            out.println(minCycledVertex);
        }
    }

    @SuppressWarnings("unchecked")
    private static class Graph {
        private final Set<Integer>[] adjacencyList;
        private final Color[] color;
        private final Deque<Integer> stack = new LinkedList<>();
        private final boolean[] inCycle;

        public Graph(int vertex) {
            this.adjacencyList = new Set[vertex + 1];
            this.color = new Color[vertex + 1];
            this.inCycle = new boolean[vertex + 1];
        }

        private void dfs(int vertex, int prevVertex) {
            color[vertex] = Color.GREY;
            stack.addFirst(vertex);
            for (Integer toVertex : adjacencyList[vertex]) {
                if (toVertex == prevVertex) {
                    continue;
                }
                if (color[toVertex] == Color.WHITE) {
                    dfs(toVertex, vertex);
                } else if (color[toVertex] == Color.GREY) {
                    minCycledVertex = Math.min(minCycledVertex, toVertex);
                    inCycle[toVertex] = true;
                    for (int v : stack) {
                        if (v == toVertex || inCycle[v]) {
                            break;
                        }
                        inCycle[v] = true;
                        if (minCycledVertex > v) {
                            minCycledVertex = v;
                        }
                    }
                }
            }
            stack.pollFirst();
            color[vertex] = Color.BLACK;
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
