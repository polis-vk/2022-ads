package company.vk.polis.ads.part10.denisstrizhkin.mincarcass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final List<Vertex> graph = new ArrayList<>();

    private record Neighbour(Vertex vertex, int weight) {
    }

    private static class Vertex implements Comparable<Vertex> {
        int minWeight = Integer.MAX_VALUE;
        boolean wasVisited = false;
        final List<Neighbour> neighbours = new ArrayList<>();

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(minWeight, o.minWeight);
        }
    }

    private Main() {
        // Should not be instantiated
    }

    private static void runPrim() {
        Queue<Vertex> queue = new PriorityQueue<>();
        graph.get(1).minWeight = 0;
        queue.addAll(graph.subList(1, graph.size()));

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();
            u.wasVisited = true;

            for (Neighbour neighbour : u.neighbours) {
                Vertex v = neighbour.vertex;
                if (!v.wasVisited && neighbour.weight < v.minWeight) {
                    queue.remove(v);
                    v.minWeight = neighbour.weight;
                    queue.add(v);
                }
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        for (int i = 0; i <= n; i++) {
            graph.add(new Vertex());
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int w = in.nextInt();
            graph.get(x).neighbours.add(new Neighbour(graph.get(y), w));
            graph.get(y).neighbours.add(new Neighbour(graph.get(x), w));
        }

        runPrim();
        out.println(graph.subList(1, graph.size()).stream().mapToInt(v -> v.minWeight).sum());
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