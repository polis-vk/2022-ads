package company.vk.polis.ads;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BellmanFord {

    // https://www.eolymp.com/ru/submissions/12382335

    private static final int SOURCE = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexes = in.nextInt();
        int edges = in.nextInt();
        List<Edge> edgeList = new ArrayList<>();
        initGraph(in, edgeList, edges);
        bellmanFord(edgeList, vertexes);
    }

    private static void bellmanFord(List<Edge> edgeList, int vertexes) {
        int[] p = new int[vertexes];
        int[] d = new int[vertexes];

        for (int i = 0; i < vertexes; i++) {
            d[i] = Integer.MAX_VALUE;
            p[i] = -1;
        }
        d[SOURCE] = 0;

        for (int i = 0; i < vertexes; i++) {
            for(Edge e : edgeList) {
                if (d[e.from] != Integer.MAX_VALUE) {
                    if (d[e.to] == Integer.MAX_VALUE || d[e.from] + e.weight < d[e.to]) {
                        d[e.to] = d[e.from] + e.weight;
                        p[e.to] = e.from;
                    }
                }
            }
        }
        printResult(d, vertexes);
    }

    private static void printResult(int[] d, int vertexes) {
        for (int i = 0; i < vertexes; i++) {
            System.out.print((d[i] == Integer.MAX_VALUE ? 30000 : d[i]) + " ");
        }
    }

    private static void initGraph(FastScanner in, List<Edge> edgeList, int edges) {
        for (int i = 0; i < edges; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int weight = in.nextInt();
            edgeList.add(new Edge(u, v, weight));
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int from;
        public int to;
        public int weight;

        public Edge(int u, int v, int weight) {
            this.from = u;
            this.to = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(@NotNull Edge o) {
            return weight - o.weight;
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
