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
public final class ShortestPath {
    private ShortestPath() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();
        int[][] graph = new int[n + 1][n + 1];
        int[] used = new int[n + 1];
        Arrays.fill(used, 0);
        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int dist = in.nextInt();
            graph[u][v] = dist;
            graph[v][u] = dist;
        }
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int v = -1;
            for (int j = 1; j <= n; j++) {
                if (used[j] == 0 && dist[j] < min) {
                    min = dist[j];
                    v = j;
                }
            }
            if (v == -1) {
                break;
            }
            for (int j = 1; j <= n; j++) {
                if (used[j] == 0 && graph[v][j] != Integer.MAX_VALUE) {
                    relaxation(v, j, dist, graph, parent);
                }
            }
            used[v] = 1;
        }
        if (dist[f] == Integer.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(dist[f]);
            printResult(f, parent, out);
        }
    }

    private static void printResult(int v, int[] parent, final PrintWriter out)
    {
        if (v == -1){
            return;
        }
        printResult(parent[v], parent, out);
        out.print(v + " ");
    }


    private static void relaxation(int i, int j, int[] dist, int[][] graph, int[] parent) {
        if (dist[j] > dist[i] + graph[i][j]) {
            dist[j] = dist[i] + graph[i][j];
            parent[j] = i;
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
