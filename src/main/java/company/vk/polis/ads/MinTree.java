package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class MinTree {
    private MinTree() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            int weight = in.nextInt();
            edges[i] = new Edge(left,right,weight);
        }
        Arrays.sort(edges);
        DSF dsf = new DSF(n);
        int result = 0;
        for (Edge edge: edges) {
            int rootLeft = dsf.findParent(edge.left);
            int rootRight = dsf.findParent(edge.right);
            if (rootLeft != rootRight) {
                dsf.union(rootLeft, rootRight);
                result += edge.weight;
            }
        }
        out.println(result);
    }

    private static class Edge implements Comparable<Edge> {
        public int left;
        public int right;
        public int weight;

        public Edge(int left, int right, int weight) {
            this.left = left;
            this.right = right;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return weight - other.weight;
        }
    }

    private static class DSF {
        private final int[] s;
        private final int[] rank;

        public DSF(int capacity) {
            s = new int[capacity + 1];
            rank = new int[capacity + 1];
            for (int i = 0; i < capacity + 1; i++) {
                s[i] = i;
            }
            Arrays.fill(rank,1);
            rank[0] = 0;
        }

        public void union(int rootLeft, int rootRight) {
            if (rank[rootLeft] < rank[rootRight]) {
                s[rootLeft] = rootRight;
            } else {
                s[rootRight] = rootLeft;
            }
            rank[rootLeft] += rank[rootLeft] == rank[rootRight] ? 1 : 0;
        }

        public int findParent(int x) {
            if (s[x] == x) {
                return x;
            }
            return s[x] = findParent(s[x]);
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

