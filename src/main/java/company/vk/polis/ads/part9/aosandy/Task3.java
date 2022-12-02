package company.vk.polis.ads.part9.aosandy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Циклы в графе
 */
public final class Task3 {
    private enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt() + 1;
        int m = in.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            addEdge(graph, in.nextInt(), in.nextInt());
        }

        Color[] colors = new Color[n];
        Arrays.fill(colors, Color.WHITE);
        boolean[] isCycleVertex = new boolean[n];
        int[] prev = new int[n];
        for (int i = 1; i < n; i++) {
            if (colors[i] == Color.WHITE) {
                dfs(graph, i, 0, colors, isCycleVertex, prev);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (isCycleVertex[i] && i < min) {
                min = i;
            }
        }
        if (min != Integer.MAX_VALUE) {
            out.println("Yes");
            out.println(min);
        } else {
            out.println("No");
        }
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> graph, int i, int j) {
        graph.get(i).add(j);
        graph.get(j).add(i);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, int v, int vPrev, Color[] colors, boolean[] isCycleVertex, int[] prev) {
        colors[v] = Color.GRAY;
        for (int i = 0; i < graph.get(v).size(); i++) {
            int current = graph.get(v).get(i);
            if (vPrev == current) {
                continue;
            }
            if (colors[current] == Color.WHITE) {
                prev[current] = v;
                dfs(graph, current, v, colors, isCycleVertex, prev);
            }
            if (colors[current] == Color.GRAY) {
                prev[current] = v;
                isCycleVertex[current] = true;
                for (int prevVertex = prev[current]; !isCycleVertex[prevVertex]; prevVertex = prev[prevVertex]) {
                    isCycleVertex[prevVertex] = true;
                }
            }
        }
        colors[v] = Color.BLACK;
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
