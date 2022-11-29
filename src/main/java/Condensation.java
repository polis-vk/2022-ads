import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
// https://www.eolymp.com/ru/submissions/12318142
public final class Condensation {

    private static class Pair implements Comparable<Pair> {

        private int first;
        private int second;

        public Pair(int fisrt, int second) {
            this.first = fisrt;
            this.second = second;
        }

        @Override
        public int compareTo(Pair other) {
            if (first == other.first) {
                return second - other.second;
            }
            return first - other.first;

        }

    }

    private static class Graph {

        private static final int NOT_VISITED = -1;
        private int V;
        private ArrayList<Integer>[] adjList;
        private ArrayList<Integer>[] adjListTranspose;
        private ArrayList<Integer> traverseOrder;
        private int[] componentColor;

        public Graph(int v) {
            V = v;
            adjList = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                adjList[i] = new ArrayList<>();
            }
            adjListTranspose = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                adjListTranspose[i] = new ArrayList<>();
            }
            traverseOrder = new ArrayList<>();
            componentColor = new int[V];
            Arrays.fill(componentColor, NOT_VISITED);
        }

        private void addEdge(int v, int w) {
            adjList[v].add(w);
            adjListTranspose[w].add(v);
        }

        private void topologicalSort() {
            boolean[] visited = new boolean[V];
            for (int i = 1; i < adjList.length; i++) {
                if (!visited[i]) {
                    DFS(i, visited);
                }
            }
        }

        private void DFS(int v, boolean[] visited) {
            visited[v] = true;
            for (Integer i : adjList[v]) {
                if (!visited[i]) {
                    DFS(i, visited);
                }
            }
            traverseOrder.add(v);
        }

        private void findCondensation() {
            int curColor = 0;
            for (int i = 1; i < adjListTranspose.length; i++) {
                int v = traverseOrder.get(V - i - 1);
                if (componentColor[v] == NOT_VISITED) {
                    DFSTranspose(v, curColor++);
                }
            }
        }

        private void DFSTranspose(int v, int color) {
            componentColor[v] = color;
            for (Integer i : adjListTranspose[v]) {
                if (componentColor[i] == NOT_VISITED) {
                    DFSTranspose(i, color);
                }
            }
        }

        private int calculateCondensation() {
            TreeSet<Pair> componentsSet = new TreeSet<>();
            for (int i = 1; i < adjList.length; i++) {
                for (int j = 0; j < adjList[i].size(); j++) {
                    int to = adjList[i].get(j);
                    if (componentColor[i] != componentColor[to]) {
                        componentsSet.add(new Pair(componentColor[i], componentColor[to]));
                    }
                }
            }
            return componentsSet.size();
        }

    }

    private Condensation() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph(n + 1);
        for (int i = 0; i < m; i++) {
            graph.addEdge(in.nextInt(), in.nextInt());
        }
        graph.topologicalSort();
        graph.findCondensation();
        out.println(graph.calculateCondensation());
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