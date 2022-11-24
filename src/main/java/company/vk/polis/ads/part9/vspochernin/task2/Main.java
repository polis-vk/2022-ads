package company.vk.polis.ads.part9.vspochernin.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * <p>
 * https://www.eolymp.com/ru/submissions/12273762
 *
 * @author Dmitry Schitinin
 */
public final class Main {

    private static List<Integer> result = new ArrayList<>();

    private enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    private static class Vertex {
        List<Vertex> paths;
        Color color;
        int num;

        public Vertex(int num) {
            this.paths = new ArrayList<>();
            this.color = Color.WHITE;
            this.num = num;
        }
    }

    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<Vertex> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new Vertex(i));
        }

        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int finish = in.nextInt();

            graph.get(start).paths.add(graph.get(finish));
        }

        for (Vertex v : graph) {
            if (v.num == 0 || v.color != Color.WHITE) {
                continue;
            }

            dfs(v);
        }

        if (result != null) {
            Collections.reverse(result);
            for (int i = 0; i < result.size() - 1; i++) {
                System.out.print(result.get(i) + " ");
            }
            System.out.println(result.get(result.size() - 1));
        }
    }

    private static void dfs(Vertex v) {
        if (result == null) {
            return;
        }
        v.color = Color.GRAY;

        for (Vertex x : v.paths) {
            if (x.color == Color.WHITE) {
                dfs(x);
            } else if (x.color == Color.GRAY) {
                System.out.println(-1);
                result = null;
            }
        }

        v.color = Color.BLACK;
        if (result != null) {
            result.add(v.num);
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
