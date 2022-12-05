package company.vk.polis.ads;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class MST {

    private static final int SOURCE = 1;
    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexes = in.nextInt();
        int edges = in.nextInt();

        int[] p = new int[vertexes + 1];

        List<List<Edge>> adj = new ArrayList<>(vertexes + 1);
        initGraph(in, adj, vertexes, edges);

        out.println(prim(adj, vertexes, p));
    }

    private static int prim(List<List<Edge>> adj, int vertexes, int[] p) {
        boolean[] visited = new boolean[vertexes + 1];
        int[] d = new int[vertexes + 1];

        for (int i = 0; i < vertexes + 1; i++) {
            d[i] = Integer.MAX_VALUE;
            p[i] = -1;
        }
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.add(new Edge(SOURCE, 0));
        d[SOURCE] = 0;

        while (!q.isEmpty()) {
            Edge e = q.poll();
            if (e == null) {
                break;
            }
            int u = e.to;
            if (visited[u]) {
                continue;
            }
            visited[u] = true;

            for (Edge v : adj.get(u)) {
                int w = v.dist;
                if (w < d[v.to]) {
                    d[v.to] = w;
                    p[v.to] = u;
                }
                q.add(new Edge(v.to, d[v.to]));
            }
        }
        return getResult(d, vertexes);
    }

    private static int getResult(int[] d, int vertexes) {
        int result = 0;
        for (int i = 1; i < vertexes + 1; i++) {
            result += d[i];
        }
        return result;
    }

    private static void initGraph(FastScanner in, List<List<Edge>> adj, int vertexes, int edges) {
        for (int i = 0; i < vertexes + 1; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int weight = in.nextInt();
            adj.get(u).add(new Edge(v, weight));
            adj.get(v).add(new Edge(u, weight));
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int to;
        public int dist;

        public Edge(int node, int weight) {
            this.to = node;
            this.dist = weight;
        }

        @Override
        public int compareTo(@NotNull Edge o) {
            return dist - o.dist;
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
