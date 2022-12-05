package company.vk.polis.ads.part10.vspochernin.task2;

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
 *
 * https://www.eolymp.com/ru/submissions/12383101
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
            this.dist = Integer.MAX_VALUE;
        }
    }

    private Main() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int s = in.nextInt();
        int f = in.nextInt();

        List<Vertex> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new Vertex(i));
        }

        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int finish = in.nextInt();
            int weight = in.nextInt();

            graph.get(start).paths.add(new Path(graph.get(finish), weight));
            graph.get(finish).paths.add(new Path(graph.get(start), weight));
        }

        graph.get(s).dist = 0;
        List<Vertex> Q = new ArrayList<>(graph);

        while (!Q.isEmpty()) {
            int minIndex = 0;
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < Q.size(); i++) {
                if (Q.get(i).dist < minDist) {
                    minIndex = i;
                    minDist = Q.get(i).dist;
                }
            }
            Vertex u = Q.remove(minIndex);

            for (Path p : u.paths) {
                long alt = u.dist + p.weight; // Long, чтобы на всякий защититься от переполнения.
                Vertex v = p.to;
                if (alt < v.dist) {
                    v.dist = (int) alt;
                    v.prev = u;
                }
            }
        }

        List<Vertex> result = new ArrayList<>();
        Vertex cur = graph.get(f);
        while (cur != null) {
            result.add(cur);
            cur = cur.prev;
        }
        Collections.reverse(result);

        System.out.println(graph.get(f).dist);
        for (int i = 0; i < result.size() - 1; i++) {
            System.out.print(result.get(i).num + " ");
        }
        System.out.println(result.get(result.size() - 1).num);
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
