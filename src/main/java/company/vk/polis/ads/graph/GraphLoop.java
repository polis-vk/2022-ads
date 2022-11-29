package company.vk.polis.ads.graph;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class GraphLoop {
    private GraphLoop() {
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
            map.get(secondVert).add(firstVert);
        }

        Map<Integer, Color> info = new HashMap<>();
        map.keySet().forEach(key -> info.put(key, Color.WHITE));
        Stack<Integer> stack = new Stack<>();

        boolean wasLoop = dfs(info, map, map.entrySet().stream().findFirst().get().getKey(), stack);
        if (wasLoop) {
            out.println("Yes");
            // ....
        } else {
            out.println("No");
        }
    }

    private static boolean dfs(Map<Integer, Color> info, Map<Integer, List<Integer>> map, int vertex, Stack<Integer> stack) {
        info.put(vertex, Color.GRAY);
        for (Integer currVertex : map.get(vertex)) {
            if (info.get(currVertex) == Color.WHITE) {
                info.put(vertex, Color.GRAY);
                stack.push(currVertex);
                if (dfs(info, map, currVertex, stack)) {
                    return true;
                }
            } else if (info.get(currVertex) == Color.GRAY) {
                return true;
            }
        }
        info.put(vertex, Color.BLACK);
        stack.pop();
        return false;
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
