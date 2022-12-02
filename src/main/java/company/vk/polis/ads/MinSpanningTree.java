package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class MinSpanningTree {
    private MinSpanningTree() {
        // Should not be instantiated
    }


    private static class Graph {
        private List<Edge>[] g;
        private int[] d;
        private final int INF = 100001;
        boolean[] ost;

        public Graph(int n) {
            g = new ArrayList[n + 1];
            for (int i = 1; i <= n; ++i) {
                g[i] = new ArrayList<>();
            }
            d = new int[n + 1];
            Arrays.fill(d, INF);
            ost = new boolean[n + 1];
        }

        public void addEdge(int b, int e, int w) {
            g[b].add(new Edge(e, w));
            g[e].add(new Edge(b, w));
        }

        public void primaAlgo() {
            TreeSet<Edge> q = new TreeSet<>();
            q.add(new Edge(1, 0));
            d[1] = 0;
            while (!q.isEmpty()) {
                int v = q.pollFirst().v;
                for (Edge e : g[v]) {
                    int u = e.v;
                    int w = e.w;
                    if (!ost[u] && w < d[u]) {
                        q.remove(new Edge(u, d[u]));
                        d[u] = w;
                        q.add(new Edge(u, d[u]));
                    }
                }
                ost[v] = true;
            }

            System.out.println(Arrays.stream(Arrays.copyOfRange(d, 1, d.length)).sum());
        }

        private class Edge implements Comparable<Edge> {
            int v;
            int w;

            public Edge(int v, int w) {
                this.v = v;
                this.w = w;
            }

            @Override
            public int compareTo(Edge edge) {
                return (this.w == edge.w) ? this.v - edge.v : this.w - edge.w;
            }
        }
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Graph graph = new Graph(n);

        for (int i = 0; i < m; ++i) {
            int b = in.nextInt();
            int e = in.nextInt();
            int w = in.nextInt();
            graph.addEdge(b, e, w);
        }

        graph.primaAlgo();
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

