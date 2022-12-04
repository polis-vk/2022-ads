package company.vk.polis.ads.graphAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

//https://www.eolymp.com/ru/submissions/12374450
public final class MinOstov {

    private MinOstov() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertices = in.nextInt();
        int edges = in.nextInt();
        Edge[] edgesArray = new Edge[edges];
        for (int i = 0; i < edges; i++) {
            edgesArray[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        out.println(kruskal(edgesArray, vertices));
    }

    private static int kruskal(Edge[] edges, int vertCount) {
        DSF dsf = new DSF(vertCount);
        AtomicInteger minOstovWeight = new AtomicInteger(0);
        Arrays.stream(edges).sorted().forEachOrdered(edge -> {
            if (dsf.union(edge.from, edge.to)) {
                minOstovWeight.accumulateAndGet(edge.weight, Integer::sum);
            }
        });
        return minOstovWeight.get();
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

    private static class DSF {
        private final int[] S;
        private final int[] rank;

        private DSF(int size) {
            this.S = new int[size + 1];
            this.rank = new int[size + 1];
            for (int i = 0; i < size + 1; i++) {
                S[i] = i;
            }
        }

        private boolean union(int u, int v) {
            int setU = find(u);
            int setV = find(v);
            if (setU == setV) {
                return false;
            }

            if (rank[setU] < rank[setV]) {
                S[setU] = setV;
            } else {
                S[setV] = setU;
                if (rank[setU] == rank[setV]) {
                    rank[setU]++;
                }
            }
            return true;
        }

        private int find(int x) {
            return (S[x] == x) ? x : (S[x] = find(S[x]));
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
