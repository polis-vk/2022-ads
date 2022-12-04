package main.java.company.vk.polis.ads.iampolshin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class MST {
    private MST() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/12374062
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] graph = new Edge[m];
        for (int i = 0; i < m; i++) {
            graph[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        Arrays.sort(graph);

        int[] treeId = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            treeId[i] = i;
        }

        int newId;
        int oldId;
        int weight = 0;
        for (Edge edge : graph) {
            if (treeId[edge.start] == treeId[edge.end]) {
                continue;
            }
            weight += edge.weight;
            newId = treeId[edge.start];
            oldId = treeId[edge.end];
            for (int i = 0; i <= n; i++) {
                if (treeId[i] == oldId) {
                    treeId[i] = newId;
                }
            }
        }
        out.print(weight);
    }

    private static class Edge implements Comparable<Edge> {
        public final int start;
        public final int end;
        public final int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
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
