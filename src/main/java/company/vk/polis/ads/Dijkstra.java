package company.vk.polis.ads;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class Dijkstra {

    // https://www.eolymp.com/ru/submissions/12379233

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexes = in.nextInt();
        int edges = in.nextInt();

        int s = in.nextInt();
        int f = in.nextInt();

        int[] p = new int[vertexes + 1];

        List<List<Edge>> adj = new ArrayList<>(vertexes + 1);
        initGraph(in, adj, vertexes, edges);

        dijkstra(adj, vertexes, s, f, p);
    }

    private static void dijkstra(List<List<Edge>> adj, int vertexes, int s, int f, int[] p) {
        boolean[] visited = new boolean[vertexes + 1];
        int[] d = new int[vertexes + 1];

        for (int i = 0; i < vertexes + 1; i++) {
            d[i] = Integer.MAX_VALUE;
            p[i] = -1;
        }
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.add(new Edge(s, 0));
        d[s] = 0;

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
                int w = d[u] + v.dist;
                if (w < d[v.to]) {
                    d[v.to] = w;
                    p[v.to] = u;
                }
                q.add(new Edge(v.to, d[v.to]));
            }
        }
        printResult(d, p, s, f);
    }

    private static void printResult(int[] d, int[] p, int s, int f) {
        if (d[f] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(d[f]);
        Deque<Integer> path = new ArrayDeque<>();
        for (int i = f; i != s; i = p[i]) {
            path.addFirst(i);
        }

        System.out.println(s);
        for (Integer i : path) {
            System.out.println(i);
        }
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
