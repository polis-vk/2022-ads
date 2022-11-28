package company.vk.polis.ads.iampolshin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class ShortestPath {
    private ShortestPath() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/12316763
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        Set<Integer>[] graph = new Set[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<>();
        }

        int startVertex = in.nextInt();
        int endVertex = in.nextInt();
        int firstVertex;
        int secondVertex;
        for (int i = 1; i <= m; i++) {
            firstVertex = in.nextInt();
            secondVertex = in.nextInt();
            graph[firstVertex].add(secondVertex);
            graph[secondVertex].add(firstVertex);
        }

        int[] distances = new int[n + 1];
        int[] previous = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        if (!hasPath(graph, startVertex, endVertex, distances, previous)) {
            out.println(-1);
            return;
        }

        List<Integer> restoredPath = restorePath(endVertex, previous);
        out.println(distances[endVertex]);
        for (int i = restoredPath.size() - 1; i >= 0; i--) {
            out.print(restoredPath.get(i) + " ");
        }
    }

    private static List<Integer> restorePath(int endVertex, int[] previous) {
        List<Integer> path = new ArrayList<>();
        path.add(endVertex);

        int currVertex = endVertex;
        while (previous[currVertex] != 0) {
            currVertex = previous[currVertex];
            path.add(currVertex);
        }
        return path;
    }

    private static boolean hasPath(Set<Integer>[] graph, int startVertex, int endVertex, int[] distances, int[] previous) {
        distances[startVertex] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        int currVertex;
        while (!queue.isEmpty()) {
            currVertex = queue.poll();
            for (Integer neighbourVertex : graph[currVertex]) {
                if (distances[neighbourVertex] == Integer.MAX_VALUE) {
                    queue.add(neighbourVertex);
                    previous[neighbourVertex] = currVertex;
                    distances[neighbourVertex] = distances[currVertex] + 1;
                }
            }
        }
        return previous[endVertex] != 0;
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

