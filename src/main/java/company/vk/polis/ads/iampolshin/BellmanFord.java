package main.java.company.vk.polis.ads.iampolshin;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class BellmanFord {
    private static final int MAX_PATH = 30_000;

    private BellmanFord() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/12373328
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] graph = new Edge[m];
        for (int i = 0; i < m; i++) {
            graph[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        int[] distances = new int[n + 1];
        Arrays.fill(distances, MAX_PATH);
        distances[1] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : graph) {
                if (distances[edge.start] == MAX_PATH) {
                    continue;
                }
                if (distances[edge.end] > distances[edge.start] + edge.weight) {
                    distances[edge.end] = distances[edge.start] + edge.weight;
                }
            }
        }

        for (int i = 1; i < distances.length; i++) {
            out.print(distances[i] + " ");
        }
    }

    private static class Edge {
        public final int start;
        public final int end;
        public final int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
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
