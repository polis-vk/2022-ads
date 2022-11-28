package company.vk.polis.ads.part9.vspochernin.task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * https://www.eolymp.com/ru/submissions/12317579
 *
 * @author Dmitry Schitinin
 */
public final class Main {

    private enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    private static class Vertex {
        List<Vertex> paths;
        Color color;
        int num;
        Vertex prev;
        boolean isPartOfCycle;

        public Vertex(int num) {
            this.paths = new ArrayList<>();
            this.color = Color.WHITE;
            this.num = num;
            this.prev = null;
            this.isPartOfCycle = false;
        }
    }

    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        List<Vertex> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new Vertex(i));
        }

        for (int i = 0; i < k; i++) {
            int start = in.nextInt();
            int finish = in.nextInt();

            graph.get(start).paths.add(graph.get(finish));
            graph.get(finish).paths.add(graph.get(start));
        }

        for (Vertex v : graph) {
            if (v.num == 0 || v.color != Color.WHITE) {
                continue;
            }

            dfs(null, v);
        }

        int min = Integer.MAX_VALUE;
        for (Vertex v : graph) {
            if (v.num == 0) {
                continue;
            }

            if (v.isPartOfCycle && v.num < min) {
                min = v.num;
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            System.out.println(min);
        }
    }

    private static void dfs(Vertex cameFrom, Vertex v) {
        v.color = Color.GRAY;

        for (Vertex x : v.paths) {
            if (cameFrom == x) {
                continue;
            }

            if (x.color == Color.WHITE) {
                x.prev = v;
                dfs(v, x);
            }

            if (x.color == Color.GRAY) { // Наткнулись на цикл.
                x.prev = v;
                x.isPartOfCycle = true;
                for (Vertex prev = x.prev; !prev.isPartOfCycle; prev = prev.prev) {
                    prev.isPartOfCycle = true;
                }
            }
        }

        v.color = Color.BLACK;
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
