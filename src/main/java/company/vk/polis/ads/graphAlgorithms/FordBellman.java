package company.vk.polis.ads.graphAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

//https://www.eolymp.com/ru/submissions/12377375
public final class FordBellman {

    private FordBellman() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int start = 1;
        int vertices = in.nextInt();
        int edges = in.nextInt();
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 1; i <= edges; i++) {
            edgeList.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
        }


        Arrays.stream(fordBellman(edgeList, start, vertices)).skip(1).forEachOrdered(d ->
                out.print(((d == Integer.MAX_VALUE) ? 30000 : d) + " "));
    }

    private static int[] fordBellman(List<Edge> edgeList, int start, int vertices) {
        int[] dist = new int[vertices + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        for (int i = 0; i < vertices - 1; i++) {
            boolean wasRelaxation = false;
            for (Edge edge : edgeList) {
                if (dist[edge.from] == Integer.MAX_VALUE) {
                    continue;
                }
                if (dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                    wasRelaxation = true;
                }
            }
            if (!wasRelaxation) {
                break;
            }
        }
        return dist;
    }

    private static final class Edge implements Comparable<Edge> {
        private final int from;
        private final int to;
        private final int weight;

        private Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge otherEdge) {
            return Integer.compare(this.weight, otherEdge.weight);
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
