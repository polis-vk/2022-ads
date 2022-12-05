package company.vk.polis.ads.part10.vspochernin.task3;

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
 * <p>
 * https://www.eolymp.com/ru/submissions/12383247
 *
 * @author Dmitry Schitinin
 */
public final class Main {

    private static class Path {
        Vertex to;
        int weight;

        public Path(Vertex to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class Vertex {
        List<Path> paths;
        int num;
        Vertex prev;
        int dist;

        public Vertex(int num) {
            this.paths = new ArrayList<>();
            this.num = num;
            this.prev = null;
            this.dist = 30000;
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
            int weight = in.nextInt();

            graph.get(start).paths.add(new Path(graph.get(finish), weight));
        }

        graph.get(1).dist = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Vertex u : graph) {
                if (u.num == 0) {
                    continue;
                }
                for (Path p : u.paths) {
                    Vertex v = p.to;
                    if (u.dist + p.weight < v.dist) {
                        v.dist = u.dist + p.weight;
                        v.prev = u;
                    }
                }
            }
        }

        for (int i = 1; i < n; i++) {
            System.out.print(graph.get(i).dist + " ");
        }
        System.out.println(graph.get(graph.size() - 1).dist);
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
