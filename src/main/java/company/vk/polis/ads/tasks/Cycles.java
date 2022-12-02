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
public final class Cycles {
    private Cycles() {
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

        private final Deque<Integer> result;
        private int minResult;
        private boolean flag;
        private final Colors[] visited;
        private final boolean[] inCycle;

        @SuppressWarnings("unchecked")
        public Graph(int v) {
            vertices = v;
            flag = false;
            result = new ArrayDeque<>();
            graph = new ArrayList[vertices];
            visited = new Colors[vertices];
            inCycle = new boolean[vertices];
            for (int i = 1; i < vertices; i++) {
                graph[i] = new ArrayList<>();
                visited[i] = Colors.WHITE;
            }
        }

        private void add(int vertices, int edges) {
            graph[vertices].add(edges);
            graph[edges].add(vertices);
        }

        private void dfs(int source, int prev) {
            visited[source] = Colors.GREY;
            result.push(source);
            for (int neighbour : graph[source]) {
                if (neighbour == prev) {
                    continue;
                }
                if (visited[neighbour] == Colors.WHITE) {
                    dfs(neighbour, source);
                }
                if (visited[neighbour] == Colors.GREY) {
                    inCycle[neighbour] = true;
                    for (Integer element : result) {
                        if (element.equals(neighbour) || inCycle[element]) {
                            break;
                        }
                        inCycle[element] = true;
                    }
                }
            }
            visited[source] = Colors.BLACK;
            result.remove();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numVertices = in.nextInt();
        int numEdges = in.nextInt();
        Graph graph = new Graph(numVertices + 1);
        for (int i = 1; i <= numEdges; i++) {
            graph.add(in.nextInt(), in.nextInt());
        }
        for (int i = 1; i <= numVertices; i++) {
            graph.dfs(i, 1);
        }
        for (int i = 1; i < graph.vertices; i++) {
            if (graph.inCycle[i]) {
                graph.flag = true;
                graph.minResult = i;
                break;
            }
        }
        if (graph.flag) {
            out.println("Yes");
            out.println(graph.minResult);
        } else {
            out.println("No");
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
