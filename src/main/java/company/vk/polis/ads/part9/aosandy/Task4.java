package company.vk.polis.ads.part9.aosandy;

import java.io.*;
import java.util.*;

/**
 * Конденсация графа
 */
public final class Task4 {
    private static ArrayList<ArrayList<Integer>> graph, graphT;
    private static int[] color;
    private static boolean[] used;
    private static List<Integer> top = new ArrayList<>();
    private static TreeSet<Pair> s = new TreeSet<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt() + 1;
        int m = in.nextInt();

        graph = new ArrayList<>(n);
        graphT = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            graphT.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            addEdge(in.nextInt(), in.nextInt());
        }

        used = new boolean[n];
        for(int i = 1; i < n; i++) {
            if (!used[i]) {
                dfs1(i);
            }
        }

        color = new int[n];
        Arrays.fill(color, -1);
        int c = 0;
        for (int i = 1; i < n; i++) {
            int v = top.get(n - i - 1);
            if (color[v] == -1) {
                dfs2(v, c++);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                int current = graph.get(i).get(j);
                if (color[i] != color[current]) {
                    s.add(new Pair(color[i], color[current]));
                }
            }
        }

        out.println(s.size());
    }

    private static void addEdge(int i, int j) {
        graph.get(i).add(j);
        graphT.get(j).add(i);
    }

    private static void dfs1(int v) {
        used[v] = true;
        for (int i = 0; i < graph.get(v).size(); i++) {
            int current = graph.get(v).get(i);
            if (!used[current]) {
                dfs1(current);
            }
        }
        top.add(v);
    }

    private static void dfs2(int v, int c) {
        color[v] = c;
        for (int i = 0; i < graphT.get(v).size(); i++) {
            int current = graphT.get(v).get(i);
            if (color[current] == -1) {
                dfs2(current, c);
            }
        }
    }

    private static class Pair implements Comparable<Pair> {
        private final int first;
        private final int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            if (first == o.first) {
                return second - o.second;
            }
            return first - o.first;
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
