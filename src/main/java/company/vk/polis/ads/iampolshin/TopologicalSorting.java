package company.vk.polis.ads.iampolshin;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TopologicalSorting {
    private static final int PARTLY_VISITED = 1;
    private static final int NOT_VISITED = 0;
    private static final int FULLY_VISITED = -1;

    private TopologicalSorting() {
        // Should not be instantiated
    }

    // Решение: https://www.eolymp.com/ru/submissions/12286452
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer>[] graph = new List[n + 1];
        Deque<Integer> sortedVertex = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            graph[in.nextInt()].add(in.nextInt());
        }

        int[] states = new int[n + 1];
        for (int vertex = 1; vertex <= n; vertex++) {
            if (!trySort(graph, vertex, states, sortedVertex)) {
                out.println(-1);
                return;
            }
        }

        for (int i = 0; i < n; i++) {
            out.print(sortedVertex.pop() + " ");
        }
    }

    private static boolean trySort(List<Integer>[] graph, int startVertex, int[] flags, Deque<Integer> sortedVertex) {
        if (flags[startVertex] == PARTLY_VISITED) {
            return false;
        }
        if (flags[startVertex] == FULLY_VISITED) {
            return true;
        }

        flags[startVertex] = PARTLY_VISITED;
        for (Integer neighbourVertex : graph[startVertex]) {
            if (flags[neighbourVertex] == PARTLY_VISITED) {
                return false;
            }

            if (flags[neighbourVertex] == NOT_VISITED) {
                trySort(graph, neighbourVertex, flags, sortedVertex);
            }
        }

        flags[startVertex] = FULLY_VISITED;
        sortedVertex.push(startVertex);
        return true;
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
