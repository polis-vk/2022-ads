import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class MinFrame {
    private MinFrame() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int dist = in.nextInt();
            edges[i] = new Edge(u, v, dist);
        }
        kruscalAlgorithm(n, edges, out);
    }

    private static void kruscalAlgorithm(int n, Edge[] edges, final PrintWriter out) {
        int sum = 0;
        Arrays.sort(edges);
        int[] parents = new int[n + 1];
        int[] size = new int[n + 1];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            size[i] = 1;
        }
        int edgedCounter = 0;
        int edgedTaken = 1;
        while (edgedTaken <= n - 1) {
            Edge edge = edges[edgedCounter];
            if (!isCycle(edge.u, edge.v, parents)) {
                merge(findParent(edge.u, parents), findParent(edge.v, parents), parents, size);
                sum = sum + edge.dist;
                edgedTaken++;
            }
            edgedCounter++;
        }
        out.println(sum);
    }

    private static boolean isCycle(int u, int v, int[] parents) {
        return findParent(u, parents) == findParent(v, parents);
    }

    private static void merge(int u, int v, int[] parents, int[] size) {
        u = findParent(u, parents);
        v = findParent(v, parents);
        if (size[u] > size[v]) {
            parents[v] = u;
            size[u] = size[u] + size[v];
        } else {
            parents[u] = v;
            size[v] = size[v] + size[u];
        }
    }


    private static int findParent(int u, int[] parents) {
        if (parents[u] == u) {
            return u;
        } else {
            parents[u] = findParent(parents[u], parents);
            return parents[u];
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

    private static class Edge implements Comparable<Edge> {
        private int u;
        private int v;
        private int dist;

        public Edge(int u, int v, int dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "u=" + u +
                    ", v=" + v +
                    ", dist=" + dist +
                    '}';
        }
    }

}