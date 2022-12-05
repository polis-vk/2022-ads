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
    private static void solve(final FastScanner in, final PrintWriter out) {
        int countNodes = in.nextInt();
        int countEdges = in.nextInt();
        int[][] graph = new int[3][countEdges];
        for (int i = 0; i < countEdges; i++) {
            graph[0][i] = in.nextInt() - 1;
            graph[1][i] = in.nextInt() - 1;
            graph[2][i] = in.nextInt();
        }
        System.out.println(primMST(graph, countNodes, countEdges));
    }

    private static int primMST(int[][] graph, int countNodes, int countEdges) {
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
                for (int j = 0; j < countEdges; j++) {
                    if (graph[0][j] == u) {
                        if (graph[2][j] < min && !inMST[graph[1][j]]) {
                            min = graph[2][j];
                            v = graph[1][j];
                        }
                    } else if (graph[1][j] == u) {
                        if (graph[2][j] < min && !inMST[graph[0][j]]) {
                            min = graph[2][j];
                            v = graph[0][j];
                        }
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
