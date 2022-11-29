package company.vk.polis.ads.graph;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TopologicalSort {
    private TopologicalSort() {
        // Should not be instantiated
    }

    private enum Color {
        WHITE, GRAY, BLACK
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        if (!in.reader.ready()) {
            out.println(-1);
            return;
        }

        int vertNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        Map<Integer, List<Integer>> map = new HashMap<>();

        while (in.reader.ready()) {
            int firstVert = in.nextInt();
            int secondVert = in.nextInt();
            if (!map.containsKey(firstVert)) {
                map.put(firstVert, new ArrayList<>());
            }
            if (!map.containsKey(secondVert)) {
                map.put(secondVert, new ArrayList<>());
            }
            map.get(firstVert).add(secondVert);
        }

        if (findLoop(map)) {
            out.println(-1);
            return;
        }

        Map<Integer, Boolean> visited = new HashMap<>();
        map.keySet().forEach(key -> visited.put(key, false));
        Deque<Integer> answer = new ArrayDeque<>();
        map.forEach((key, value) -> {
                    if (!visited.get(key)) {
                        dfs(visited, answer, map, key);
                    }
                }
        );

        out.print(answer.pollLast());
        while (!answer.isEmpty()) {
            out.print(" " + answer.pollLast());
        }
    }

    private static boolean findLoop(Map<Integer, List<Integer>> map) {
        Map<Integer, Color> loopVisited = new HashMap<>();
        map.keySet().forEach(key -> loopVisited.put(key, Color.WHITE));
        return findLoop(loopVisited, map, map.entrySet().stream().findFirst().get().getKey());
    }

    private static boolean findLoop(Map<Integer, Color> visited, Map<Integer, List<Integer>> map, int vertex) {
        visited.put(vertex, Color.GRAY);
        for (Integer currVertex : map.get(vertex)) {
            if (visited.get(currVertex) == Color.WHITE) {
                visited.put(vertex, Color.GRAY);
                if (findLoop(visited, map, currVertex)) {
                    return true;
                }
            } else if (visited.get(currVertex) == Color.GRAY) {
                return true;
            }
        }
        visited.put(vertex, Color.BLACK);
        return false;
    }

    private static void dfs(Map<Integer, Boolean> visited, Deque<Integer> answer, Map<Integer, List<Integer>> map, int vertex) {
        visited.put(vertex, true);
        map.get(vertex).forEach(currVertex -> {
                    if (!visited.get(currVertex)) {
                        dfs(visited, answer, map, currVertex);
                    }
                }
        );
        answer.add(vertex);
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
