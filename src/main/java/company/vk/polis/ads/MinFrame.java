package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MinFrame {
    private static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

    }

    private static ArrayList<Edge>[] graph;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countNodes = in.nextInt();
        int countEdges = in.nextInt();
        graph = new ArrayList[countNodes];
        for (int i = 0; i < countNodes; i++) {
            graph[i] = new ArrayList<>();
        }
        int from;
        int to;
        int cost;
        for (int i = 0; i < countEdges; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            cost = in.nextInt();
            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }
        System.out.println(primMST(countNodes, countEdges));
    }

    private static int primMST(int countNodes, int countEdges) {
        boolean[] inMST = new boolean[countNodes];
        List<Integer> used = new ArrayList<>();

        inMST[0] = true;
        used.add(0);

        int edgeCount = 1;
        int minCost = 0;
        int min;
        int v = 0;
        while (edgeCount < countNodes) {
            min = Integer.MAX_VALUE;

            for (int u : used) {
                for (Edge e : graph[u]) {
                    if (e.cost < min && !inMST[e.to]) {
                        min = e.cost;
                        v = e.to;
                    }
                }
            }

            if (min < Integer.MAX_VALUE) {
                minCost += min;
                used.add(v);
                inMST[v] = true;
                edgeCount++;
            }
        }
        return minCost;
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
