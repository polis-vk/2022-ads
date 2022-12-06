package company.vk.polis.ads.part10.denisstrizhkin.mincarcass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final List<Vertex> graph = new ArrayList<>();
    private static int[] used;
    private static final List<HashMap<Integer, Integer>> weights = new ArrayList<>();

    private static class Vertex implements Comparable<Vertex> {
        int minWeight = Integer.MAX_VALUE;
        final List<Vertex> neighbours = new ArrayList<>();
        final int number;

        public Vertex(int number) {
            this.number = number;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(minWeight, o.minWeight);
        }
    }

    private Main() {
        // Should not be instantiated
    }

    private static int[] getPrev(int source) {
        int[] prev = new int[graph.size()];
        Arrays.fill(prev, -1);
        Queue<Vertex> queue = new PriorityQueue<>();

        for (Vertex v : graph) {
            v.minWeight = Integer.MAX_VALUE;
        }
        graph.get(source).minWeight = 0;
        queue.addAll(graph);

        while (!queue.isEmpty()) {
            Vertex u = queue.poll();
            used[u.number] = 1;

            for (Vertex v : u.neighbours) {
                if (used[v.number] == 0) {
                    int weight = weights.get(u.number).get(v.number);
                    if (weight < v.minWeight) {
                        queue.remove(v);
                        v.minWeight = weight;
                        queue.add(v);
                        prev[v.number] = u.number;
                    }
                }
            }
        }

        return prev;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        used = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new Vertex(i));
            weights.add(new HashMap<>());
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int w = in.nextInt();
            graph.get(x).neighbours.add(graph.get(y));
            graph.get(y).neighbours.add(graph.get(x));
            weights.get(x).put(y, w);
            weights.get(y).put(x, w);
        }

        int[] prev = getPrev(3);
        int sum = 0;
        for (int i = 1; i < graph.size(); i++) {
            if (prev[i] != -1) {
                sum += weights.get(prev[i]).get(i);
            }
        }
        out.println(sum);
        out.println(Arrays.toString(prev));
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