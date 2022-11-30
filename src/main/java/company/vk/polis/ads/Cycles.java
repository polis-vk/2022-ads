package company.vk.polis.ads;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Cycles {

    // https://www.eolymp.com/ru/submissions/12328044

    private enum Color {
        WHITE, GREY, BLACK
    }

    private static List<Integer> cycle;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt(); // n
        int e = in.nextInt(); // k

        List<List<Integer>> adj = new ArrayList<>(v + 1);
        initGraph(in, adj, v, e);

        List<Color> visited = new ArrayList<>(adj.size());
        initColor(visited, v);

        cycle = new LinkedList<>();

        for (int u = 0; u < adj.size(); u++) {
            if (visited.get(u) == Color.WHITE) {
                dfs(u, adj, visited, u);
            }
        }

        if (cycle.isEmpty()) {
            out.println("No");
        } else {
            int min = cycle.get(0);
            for (Integer i : cycle) {
                if (i < min) {
                    min = i;
                }
            }
            out.println("Yes");
            out.println(min);
        }
    }

    private static boolean dfs(int u, List<List<Integer>> adj, List<Color> visited, int prev) {
        boolean curInCycle = false;
        visited.set(u, Color.GREY);
        for (Integer v : adj.get(u)) {
            if (!v.equals(prev)) {
                if (visited.get(v) == Color.WHITE) {
                    if (dfs(v, adj, visited, u)) {
                        cycle.add(v);
                        if (visited.get(u) != Color.BLACK) {
                            curInCycle = true;
                        }
                    }
                } else if (visited.get(v) == Color.GREY) {
                    visited.set(v, Color.BLACK);
                    cycle.add(v);
                    curInCycle = true;
                }
            }

        }
        visited.set(u, Color.BLACK);
        return curInCycle;
    }

    private static void initColor(List<Color> visited, int v) {
        for (int i = 0; i < v + 1; i++) {
            visited.add(Color.WHITE);
        }
    }

    private static void initGraph(final FastScanner in, List<List<Integer>> adj, int v, int e) {
        for (int i = 0; i < v + 1; i++) {
            adj.add(new LinkedList<>());
        }

        for (int i = 0; i < e; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
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
