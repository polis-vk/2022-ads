package company.vk.polis.ads.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[vertNumber];
        Color[] visited = new Color[vertNumber];

        for (int i = 0; i < vertNumber; i++) {
            graph[i] = new ArrayList<>();
            visited[i] = Color.WHITE;
        }

        for (int i = 0; i < edgesNumber; i++) {
            int firstVert = in.nextInt();
            int secondVert = in.nextInt();
            graph[firstVert - 1].add(secondVert - 1);
        }

        Deque<Integer> answer = new ArrayDeque<>();
        for (int i = 0; i < vertNumber; i++) {
            if (visited[i] == Color.WHITE) {
                if (dfs(visited, answer, graph, i)) {
                    out.println(-1);
                    return;
                }
            }
        }

        out.println(answer.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static boolean dfs(Color[] visited, Deque<Integer> answer, List<Integer>[] graph, int vertex) {
        visited[vertex] = Color.GRAY;
        for (Integer currVertex : graph[vertex]) {
            Color visitedCurrState = visited[currVertex];
            if (visitedCurrState == Color.WHITE && dfs(visited, answer, graph, currVertex)
                    || visitedCurrState == Color.GRAY) {
                return true;
            }
        }
        visited[vertex] = Color.BLACK;
        answer.addFirst(vertex + 1);
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
