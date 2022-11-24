package company.vk.polis.ads.part9.vspochernin.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * https://www.eolymp.com/ru/submissions/12273572
 *
 * @author Dmitry Schitinin
 */
public final class Main {

    private static List<Integer> toplogSort = new ArrayList<>();
    private static Map<Integer, Integer> components = new HashMap<>();

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
        List<Vertex> transposedGraph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new Vertex(i));
            transposedGraph.add(new Vertex(i));
        }

        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int finish = in.nextInt();

            graph.get(start).paths.add(graph.get(finish));
            transposedGraph.get(finish).paths.add(transposedGraph.get(start));
        }

        for (Vertex v : graph) {
            if (v.num == 0 || v.color != Color.WHITE) {
                continue;
            }

            dfs1(v);
        }

        Collections.reverse(toplogSort);

        int curGroupNumber = 0;
        for (Integer vertexNumber : toplogSort) {
            Vertex v = transposedGraph.get(vertexNumber);
            if (v.num == 0 || v.color != Color.WHITE) {
                continue;
            }

            curGroupNumber++;
            dfs2(v, curGroupNumber);
        }

        Set<String> uniqueRoutes = new HashSet<>();
        for (Vertex v : graph) {
            for (Vertex x : v.paths) {
                if (!components.get(v.num).equals(components.get(x.num))) {
                    uniqueRoutes.add(components.get(v.num).toString() + " " + components.get(x.num).toString());
                }
            }
        }

        out.println(uniqueRoutes.size());
    }

    private static void dfs1(Vertex v) {
        v.color = Color.GRAY;

        for (Vertex x : v.paths) {
            if (x.color == Color.WHITE) {
                dfs1(x);
            } else if (x.color == Color.GRAY) {
                // Циклы игнорируем.
            }
        }

        toplogSort.add(v.num);
    }

    private static void dfs2(Vertex v, int groupNumber) {
        components.put(v.num, groupNumber);

        for (Vertex x : v.paths) {
            if (!components.containsKey(x.num)) {
                dfs2(x, groupNumber);
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
