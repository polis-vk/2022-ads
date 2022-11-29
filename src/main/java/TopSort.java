import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
// https://www.eolymp.com/ru/submissions/12318057
public final class TopSort {

    private static class Graph {

        private static final int WHITE = -1;
        private static final int GRAY = 0;
        private static final int RED = 1;
        private static final int CYCLE_FOUND = 2;
        private static final int OK = 3;
        private int V;
        private ArrayList<Integer>[] adjList;

        public Graph(int v) {
            V = v;
            adjList = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        private void addEdge(int v, int w) {
            adjList[v].add(w);
        }

        private String topologicalSort() {
            int[] colors = new int[V];
            Arrays.fill(colors, WHITE);
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for (int i = 1; i < adjList.length; i++) {
                if (colors[i] == WHITE) {
                    if (DFS(i, colors, stack) == CYCLE_FOUND) {
                        return "-1";
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        private int DFS(int v, int[] colors, ArrayDeque<Integer> stack) {
            colors[v] = GRAY;

            for (Integer i : adjList[v]) {
                if (colors[i] == GRAY) {
                    return CYCLE_FOUND;
                } else if (colors[i] == WHITE) {
                    if (DFS(i, colors, stack) == CYCLE_FOUND) {
                        return CYCLE_FOUND;
                    }
                }
            }
            colors[v] = RED;
            stack.push(v);
            return OK;
        }

    }

    private TopSort() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph(n + 1);
        for (int i = 0; i < m; i++) {
            graph.addEdge(in.nextInt(), in.nextInt());
        }
        out.println(graph.topologicalSort());
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
