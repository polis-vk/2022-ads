package company.vk.polis.ads.paikeee.part9;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TopologicalSort {

    private enum Color { WHITE, GREY, BLACK }
    private static ArrayDeque<Integer> result;

    private TopologicalSort() {
        // Should not be instantiated
    }

    private static class Vertex {

        int value;
        Color color;
        final ArrayList<Vertex> edges;

        Vertex(int value) {
            this.value = value;
            this.color = Color.WHITE;
            edges = new ArrayList<>();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Vertex> graph = new ArrayList<>(n + 1);
        result = new ArrayDeque<>(n);
        for (int i = 0; i < n + 1; i++) {
            graph.add(new Vertex(i));
        }
        for (int i = 0; i < m; i++) {
            int begin = in.nextInt();
            int end = in.nextInt();
            graph.get(begin).edges.add(graph.get(end));
        }
        for (int i = 1; i < n + 1; i++) {
            Vertex v = graph.get(i);
            if (v.color == Color.WHITE) {
                dfs(v);
            }
        }
        if (result != null) {
            result.forEach(it -> out.print(it + " "));
        } else {
            out.println(-1);
        }
    }

    private static void dfs(Vertex v) {
        if (result == null) {
            return;
        }
        v.color = Color.GREY;
        for (Vertex child : v.edges) {
            if (child.color == Color.WHITE) {
                dfs(child);
            } else if (child.color == Color.GREY) {
                result = null;
            }
        }
        v.color = Color.BLACK;
        if (result != null) {
            result.push(v.value);
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
