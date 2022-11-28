package company.vk.polis.ads.iampolshin;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Cycles {
    private static final int PARTLY_VISITED = 1;
    private static final int NOT_VISITED = 0;
    private static final int FULLY_VISITED = -1;

    private Cycles() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/12309356
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer>[] graph = new List[n + 1];
        Set<Integer> cycleVertices = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int firstVertex;
        int secondVertex;
        for (int i = 1; i <= m; i++) {
            firstVertex = in.nextInt();
            secondVertex = in.nextInt();
            graph[firstVertex].add(secondVertex);
            graph[secondVertex].add(firstVertex);
        }

        int[] states = new int[n + 1];
        int[] previous = new int[n + 1];
        for (int vertex = 1; vertex <= n; vertex++) {
            if (states[vertex] == NOT_VISITED) {
                dfs(graph, vertex, states, cycleVertices, previous);
            }
        }

        if (cycleVertices.isEmpty()) {
            out.println("No");
            return;
        }

        out.println("Yes");
        out.println(Collections.min(cycleVertices));
    }

    private static void dfs(List<Integer>[] graph, int startVertex, int[] flags, Set<Integer> cycleVertices,
                            int[] previous) {
        if (flags[startVertex] == FULLY_VISITED) {
            return;
        }

        flags[startVertex] = PARTLY_VISITED;
        for (Integer neighbourVertex : graph[startVertex]) {
            if (flags[neighbourVertex] == NOT_VISITED) {
                previous[neighbourVertex] = startVertex;
                dfs(graph, neighbourVertex, flags, cycleVertices, previous);
            } else if (!neighbourVertex.equals(previous[startVertex]) && flags[neighbourVertex] != FULLY_VISITED) {
                int cycleStart = neighbourVertex;
                int cycleEnd = startVertex;
                while (cycleStart != cycleEnd && !cycleVertices.contains(cycleStart)) {
                    cycleVertices.add(cycleEnd);
                    cycleEnd = previous[cycleEnd];
                }
                cycleVertices.add(cycleEnd);
            }
        }
        flags[startVertex] = FULLY_VISITED;
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
