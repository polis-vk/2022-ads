package company.vk.polis.ads;

import java.io.*;
import java.util.*;

public class SCC {

    // https://www.eolymp.com/ru/submissions/12348035

    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt(); // n
        int e = in.nextInt(); // m

        List<List<Integer>> adj = new ArrayList<>(v + 1);
        List<List<Integer>> adjR = new ArrayList<>(v + 1);
        initGraph(in, adj, adjR, v, e);

        List<Boolean> visited = new ArrayList<>(adj.size());
        initVisited(visited, v);

        List<Integer> order = new ArrayList<>(adj.size());
        for (int i = 1; i < v + 1; ++i) {
            if (!visited.get(i)) {
                dfs1(adj, i, visited, order);
            }
        }

        initVisited(visited, v);
        int[] scc = new int[v + 1];
        int component = 0;
        Collections.reverse(order);

        for (int i = 0; i < v; ++i) {
            int u = order.get(i);
            if (!visited.get(u)) {
                dfs2(adjR, u, ++component, visited, scc);
            }
        }

        Set<Edge> metaGraph = new HashSet<>();
        for (int i = 1; i < v + 1; ++i) {
            for (int j : adj.get(i)) {
                if (!Objects.equals(scc[i], scc[j])) {
                    metaGraph.add(new Edge(scc[i], scc[j]));
                }
            }
        }
        out.print(metaGraph.size());
    }

    private static void dfs1(List<List<Integer>> adj, int v, List<Boolean> visited, List<Integer> order) {
        visited.set(v, true);
        for (int i : adj.get(v)) {
            if (!visited.get(i)) {
                dfs1(adj, i, visited, order);
            }
        }
        order.add(v);
    }

    private static void dfs2(List<List<Integer>> adjR, int u, int c, List<Boolean> visited, int[] scc) {
        visited.set(u, true);
        scc[u] = c;
        for (int i : adjR.get(u)) {
            if (!visited.get(i)) {
                dfs2(adjR, i, c, visited, scc);
            }
        }
    }

    private static void initGraph(final FastScanner in, List<List<Integer>> adj, List<List<Integer>> adjR, int v, int e) {
        for (int i = 0; i < v + 1; i++) {
            adj.add(new ArrayList<>());
            adjR.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            adj.get(v1).add(v2);
            adjR.get(v2).add(v1);
        }
    }

    private static void initVisited(List<Boolean> visited, int v) {
        visited.clear();
        for (int i = 0; i < v + 1; i++) {
            visited.add(false);
        }
    }

    private static class Edge {
        int a, b;

        Edge(int a, int b) {
            this.a = Math.max(a, b);
            this.b = this.a == a ? b : a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge edge = (Edge) o;
            return a == edge.a && b == edge.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
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