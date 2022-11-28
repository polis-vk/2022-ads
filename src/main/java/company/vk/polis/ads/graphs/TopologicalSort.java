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

// Submission link: https://www.eolymp.com/ru/submissions/12294547
public final class TopologicalSort {
    private static final Map<Integer, State> vertexState = new HashMap<>();

    private enum State {
        VISITED,
        EXITED
    }

    private TopologicalSort() {
        // Should not be instantiated
    }

    private static boolean depthFirstSearch(int current, Deque<Integer> result, Map<Integer, Set<Integer>> map) {
        vertexState.put(current, State.VISITED);
        Set<Integer> adjacentVertices = map.get(current);
        if (adjacentVertices != null) {
            for (Integer adjacentVertex : adjacentVertices) {
                if (!vertexState.containsKey(adjacentVertex)) {
                    if (!depthFirstSearch(adjacentVertex, result, map)) {
                        return false;
                    }
                } else if (vertexState.get(adjacentVertex) == State.VISITED) {
                    return false;
                }
            }
        }
        vertexState.put(current, State.EXITED);
        result.add(current);
        return true;
    }

    private static Deque<Integer> topologicalSort(Map<Integer, Set<Integer>> map, int n) {
        Deque<Integer> result = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (!vertexState.containsKey(i) && !depthFirstSearch(i, result, map)) {
                return null;
            }
        }
        return result;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            map.putIfAbsent(from, new HashSet<>());
            map.get(from).add(to);
        }
        Deque<Integer> result = topologicalSort(map, n);
        if (result == null) {
            out.println(-1);
        } else {
            while (!result.isEmpty()) {
                out.print(result.removeLast() + " ");
            }
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
