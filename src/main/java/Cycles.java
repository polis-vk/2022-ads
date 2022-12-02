import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
// https://www.eolymp.com/ru/submissions/12345896
public final class Cycles {

    private static class Graph {

        private static final int CYCLE_NOT_FOUND = Integer.MAX_VALUE;
        private static final int WHITE = -1;
        private static final int GRAY = 0;
        private static final int DARK_GRAY = 1;
        private static final int RED = 2;
        private int minInCycle = CYCLE_NOT_FOUND;
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
            adjList[w].add(v);
        }

        private void DFS() {
            int[] colors = new int[V];
            int[] parents = new int[V];
            Arrays.fill(colors, WHITE);
            Arrays.fill(parents, -1);
            for (int i = 1; i < adjList.length; i++) {
                if (colors[i] == WHITE) {
                    colors[i] = GRAY;
                    DFSUtils(i, colors, parents);
                }
            }
        }

        private void DFSUtils(int curNode, int[] colors, int[] parents) {
            for (Integer v : adjList[curNode]) {
                if (colors[v] == GRAY && v != parents[curNode]) {
                    int cyclePos = curNode;
                    while (cyclePos != v && colors[cyclePos] != DARK_GRAY) {
                        if (cyclePos < minInCycle) {
                            minInCycle = cyclePos;
                        }
                        colors[cyclePos] = DARK_GRAY;
                        cyclePos = parents[cyclePos];
                    }
                    if (v < minInCycle) {
                        minInCycle = v;
                    }
                } else if (colors[v] == WHITE) {
                    colors[v] = GRAY;
                    parents[v] = curNode;
                    DFSUtils(v, colors, parents);
                }
            }
            colors[curNode] = RED;
        }

    }

    private Cycles() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph(n + 1);
        for (int i = 0; i < m; i++) {
            graph.addEdge(in.nextInt(), in.nextInt());
        }
        graph.DFS();
        if (graph.minInCycle == Graph.CYCLE_NOT_FOUND) {
            out.println("No");
        } else {
            out.println("Yes");
            out.println(graph.minInCycle);
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
