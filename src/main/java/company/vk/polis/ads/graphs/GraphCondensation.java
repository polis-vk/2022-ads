package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

// Submission link: https://www.eolymp.com/ru/submissions/12314767
public final class GraphCondensation {
    private GraphCondensation() {
        // Should not be instantiated
    }

    private static void depthFirstSearch(int current, Deque<Integer> result, Map<Integer, Set<Integer>> map, boolean[] visited) {
        visited[current] = true;
        Set<Integer> adjacentVertices = map.get(current);
        if (adjacentVertices != null) {
            for (Integer adjacentVertex : adjacentVertices) {
                if (!visited[adjacentVertex]) {
                    depthFirstSearch(adjacentVertex, result, map, visited);
                }
            }
        }
        result.addLast(current);
    }

    private static Deque<Integer> topologicalSort(Map<Integer, Set<Integer>> map, int n, boolean[] visited) {
        Deque<Integer> result = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                depthFirstSearch(i, result, map, visited);
            }
        }
        return result;
    }

    private static void condense(Map<Integer, Set<Integer>> transposed, int current, int color, int[] colors, boolean[] visited) {
        visited[current] = true;
        colors[current] = color;
        Set<Integer> adjacentVertices = transposed.get(current);
        if (adjacentVertices != null) {
            for (Integer adjacentVertex : adjacentVertices) {
                if (!visited[adjacentVertex]) {
                    condense(transposed, adjacentVertex, color, colors, visited);
                }
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Set<Integer>> transposed = new HashMap<>();

        int n = in.nextInt();
        int m = in.nextInt();

        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();

            map.putIfAbsent(from, new HashSet<>());
            map.get(from).add(to);

            transposed.putIfAbsent(to, new HashSet<>());
            transposed.get(to).add(from);
        }

        Deque<Integer> deque = topologicalSort(map, n, visited);

        int color = 1;
        int[] colors = new int[n + 1];
        visited = new boolean[n + 1];

        while (!deque.isEmpty()) {
            int current = deque.removeLast();
            if (colors[current] == 0) {
                condense(transposed, current, color, colors, visited);
                color++;
            }
        }

        HashMap<Integer, Set<Integer>> waysBetweenAdjacentComponents = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            Set<Integer> adjacentVertices = transposed.get(i);
            if (adjacentVertices != null) {
                for (Integer adjacentVertex : adjacentVertices) {
                    if (colors[i] != colors[adjacentVertex]) {
                        waysBetweenAdjacentComponents.computeIfAbsent(colors[adjacentVertex], set -> new HashSet<>()).add(colors[i]);
                    }
                }
            }
        }

        out.println(waysBetweenAdjacentComponents.values().stream().mapToInt(Set::size).sum());
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
