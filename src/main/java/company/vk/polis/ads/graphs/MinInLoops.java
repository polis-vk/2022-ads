package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

// Submission link: https://www.eolymp.com/ru/submissions/12310155
public final class MinInLoops {
    private static final Map<Integer, State> vertexState = new HashMap<>();
    private static final Set<Integer> inLoop = new HashSet<>();
    private static int min = Integer.MAX_VALUE;

    private enum State {
        VISITED,
        EXITED
    }

    private static record Way(Way prev, int current) {
    }

    private MinInLoops() {
        // Should not be instantiated
    }

    private static void walkLoopAndFindMin(Way way, int end) {
        Way current = way;
        inLoop.add(end);
        if (end < min) {
            min = end;
        }
        while (current.current != end) {
            if (inLoop.contains(current.current)) {
                return;
            }
            if (current.current < min) {
                min = current.current;
            }
            inLoop.add(current.current);
            current = current.prev;
        }
    }

    private static void solveRecursively(int current, Way way, Map<Integer, Set<Integer>> map) {
        Way currentWay = new Way(way, current);
        vertexState.put(current, State.VISITED);
        Set<Integer> adjacentVertexes = map.get(current);
        if (adjacentVertexes != null) {
            for (Integer adjacentVertex : adjacentVertexes) {
                if (!(currentWay.prev != null && adjacentVertex.equals(currentWay.prev.current))) {
                    State currentState = vertexState.getOrDefault(adjacentVertex, null);
                    if (currentState == State.VISITED) {
                        walkLoopAndFindMin(currentWay, adjacentVertex);
                    } else if (currentState != State.EXITED) {
                        solveRecursively(adjacentVertex, currentWay, map);
                    }
                }
            }
        }
        vertexState.put(current, State.EXITED);
    }

    /*
    * 8 9
    * 1 8
    * 1 2
    * 2 8
    * 7 1
    * 7 3
    * 3 4
    * 4 5
    * 7 5
    * 4 6
    * Output: 1
    * */

    private static void solve(final FastScanner in, final PrintWriter out) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            map.putIfAbsent(from, new HashSet<>());
            map.get(from).add(to);
            map.putIfAbsent(to, new HashSet<>());
            map.get(to).add(from);
        }
        for (int i = 1; i < n; i++) {
            if (!vertexState.containsKey(i)) {
                solveRecursively(i, new Way(null, Integer.MAX_VALUE), map);
            }
        }
        if (min == Integer.MAX_VALUE) {
            out.println("No");
        } else {
            out.println("Yes");
            out.println(min);
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
