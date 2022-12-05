package company.vk.polis.ads.part10.vspochernin.task1;

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
 * https://www.eolymp.com/ru/submissions/12383217
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
        boolean isVisited;

        public Vertex(int num) {
            this.paths = new ArrayList<>();
            this.num = num;
            this.prev = null;
            this.dist = Integer.MAX_VALUE;
            this.isVisited = false;
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
            graph.get(finish).paths.add(new Path(graph.get(start), weight));
        }

        graph.get(1).dist = 0;
        List<Vertex> Q = new ArrayList<>();
        for (int i = 1; i < graph.size(); i++) {
            Q.add(graph.get(i));
        }

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
            u.isVisited = true;

            for (Path p : u.paths) {
                if (p.to.isVisited) {
                    continue;
                }
                long alt = p.weight; // Long, чтобы на всякий защититься от переполнения.
                Vertex v = p.to;
                if (alt < v.dist) {
                    v.dist = (int) alt;
                    v.prev = u;
                }
            }
        }

        long result = 0;
        for (Vertex v : graph) {
            if (v.num == 0) {
                continue;
            }
            result += v.dist;
        }

        System.out.println(result);
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
