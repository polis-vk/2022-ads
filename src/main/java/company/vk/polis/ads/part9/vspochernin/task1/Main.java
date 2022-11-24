package company.vk.polis.ads.part9.vspochernin.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * <p>
 * https://www.eolymp.com/ru/submissions/12273727
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

        int a = in.nextInt();
        int b = in.nextInt();

        List<Vertex> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new Vertex(i));
        }

        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int finish = in.nextInt();

            graph.get(start).paths.add(graph.get(finish));
            graph.get(finish).paths.add(graph.get(start));
        }

        Queue<Vertex> Q = new LinkedList<>();
        Q.add(graph.get(a));

        int count = 0;
        List<Integer> result = new ArrayList<>();

        while (!Q.isEmpty()) {
            Vertex v = Q.poll();
            v.color = Color.GRAY;

            if (v.num == b) {
                Vertex cur = v;
                while (cur != null) {
                    result.add(cur.num);
                    count++;
                    cur = cur.prev;
                }

                Collections.reverse(result);
                System.out.println(count - 1);
                for (int i = 0; i < result.size() - 1; i++) {
                    System.out.print(result.get(i) + " ");
                }
                System.out.print(result.get(result.size() - 1));
                return;
            }

            for (Vertex x : v.paths) {
                if (x.color == Color.WHITE) {
                    x.prev = v;
                    x.color = Color.GRAY;
                    Q.add(x);
                }
            }
            v.color = Color.BLACK;
        }

        System.out.println(-1);
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
