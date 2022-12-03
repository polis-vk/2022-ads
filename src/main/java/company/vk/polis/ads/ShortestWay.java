package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class ShortestWay{
    private ShortestWay() {
        // Should not be instantiated
    }



    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int s = in.nextInt();
        int f = in.nextInt();

        Graph graph = new Graph(n);
        for (int i = 0; i < m; ++i) {
            int b = in.nextInt();
            int e = in.nextInt();
            int w = in.nextInt();
            graph.addEdge(b, e, w);
        }

        graph.dijkstra(s, f);
    }

    private static class Graph {
        private List<Edge>[] g;
        private int[] d;

        private int [] parent;
        private final int INF = Integer.MAX_VALUE;


        public Graph(int n) {
            g = new ArrayList[n + 1];
            for (int i = 1; i <= n; ++i) {
                g[i] = new ArrayList<>();
            }
            d = new int[n + 1];
            parent = new int[n + 1];
            Arrays.fill(d, INF);
            Arrays.fill(parent, -1);
        }

        public void addEdge(int b, int e, int w) {
            g[b].add(new Edge(e, w));
            g[e].add(new Edge(b, w));
        }

        public void dijkstra(int s, int f) {
            d[s] = 0;
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(s, 0));
            while (!pq.isEmpty()) {
                Edge e = pq.poll();
                if (e.w > d[e.v]) {
                    continue;
                }
                for (Edge ed : g[e.v]) {
                    if (d[ed.v] > d[e.v] + ed.w) {
                        d[ed.v] = d[e.v] + ed.w;
                        parent[ed.v] = e.v;
                        pq.add(new Edge(ed.v, d[ed.v]));
                    }
                }
            }

            List<Integer> ans = new LinkedList<>();
            int tmp = f;
            if (parent[tmp] == -1) {
                System.out.println(-1);
                return;
            }

            while (tmp != -1) {
                ans.add(0, tmp);
                tmp = parent[tmp];
            }

            System.out.println(d[f]);
            ans.forEach(v -> System.out.print(v + " "));
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
