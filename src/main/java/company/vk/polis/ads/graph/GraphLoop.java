package company.vk.polis.ads.graph;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 * <p>
 *
 * https://www.eolymp.com/ru/submissions/12355255
 */
public final class GraphLoop {
    private GraphLoop() {
        // Should not be instantiated
    }

    private enum Color {
        WHITE, GRAY, BLACK
    }

    private static boolean wasLoop;

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        if (!in.reader.ready()) {
            out.println(-1);
            return;
        }

        int vertexNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[vertexNumber];
        Color[] visited = new Color[vertexNumber];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < vertexNumber; i++) {
            graph[i] = new ArrayList<>();
            visited[i] = Color.WHITE;
        }

        for (int i = 0; i < edgesNumber; i++) {
            int firstVert = in.nextInt();
            int secondVert = in.nextInt();
            graph[firstVert - 1].add(secondVert - 1);
            graph[secondVert - 1].add(firstVert - 1);
        }

        for (int currVertex = 0; currVertex < vertexNumber; currVertex++) {
            if (visited[currVertex] == Color.WHITE) {
                Stack<Integer> stack = new Stack<>();
                dfs(visited, graph, stack, null, currVertex);
                if (wasLoop) {
                    while (!stack.isEmpty()) {
                        int stackVertex = stack.pop();
                        if (stackVertex < min) {
                            min = stackVertex;
                        }
                    }
                    wasLoop = false;
                }
            }
        }

        out.println((min < Integer.MAX_VALUE) ? "Yes\n" + (min + 1) : "No");
    }

    private static void dfs(Color[] visited, List<Integer>[] graph, Stack<Integer> stack, Integer fromVertex, Integer toVertex) {
        visited[toVertex] = Color.GRAY;
        for (Integer currVertex : graph[toVertex]) {
            if (Objects.equals(fromVertex, currVertex)) {
                continue;
            }
            stack.push(currVertex);
            Color visitedCurrState = visited[currVertex];
            if (visitedCurrState == Color.WHITE) {
                dfs(visited, graph, stack, toVertex, currVertex);
            } else if (visitedCurrState == Color.GRAY) {
                wasLoop = true;
            }
        }
        visited[toVertex] = Color.BLACK;
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
