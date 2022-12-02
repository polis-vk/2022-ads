package company.vk.polis.ads.part9.aosandy;

import java.io.*;
import java.util.*;

/**
 * Топологическая сортировка
 */
public final class Task2 {
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
        ArrayList<Integer> ans = new ArrayList<>(n);
        Color[] colors = new Color[n];
        Arrays.fill(colors, Color.WHITE);
        for (int i = 1; i < n; i++) {
            if (colors[i] == Color.WHITE) {
                dfs(graph, i, colors, ans);
            }
        }
        Collections.reverse(ans);
        for (int x : ans) {
            out.println(x);
        }
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> graph, int i, int j) {
        graph.get(i).add(j);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, int v, Color[] colors, ArrayList<Integer> ans) {
        colors[v] = Color.GRAY;
        for (int i = 0; i < graph.get(v).size(); i++) {
            int current = graph.get(v).get(i);
            if (colors[current] == Color.WHITE) {
                dfs(graph, current, colors, ans);
            } else if (colors[current] == Color.GRAY) {
                System.out.println(-1);
                System.exit(0);
            }
        }
        colors[v] = Color.BLACK;
        ans.add(v);
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
