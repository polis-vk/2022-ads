package company.vk.polis.ads.iampolshin;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Condensation {
    private Condensation() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/12317835
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer>[] graph = new List[n + 1];
        List<Integer>[] transposedGraph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            transposedGraph[i] = new ArrayList<>();
        }

        int firstVertex;
        int secondVertex;
        for (int i = 1; i <= m; i++) {
            firstVertex = in.nextInt();
            secondVertex = in.nextInt();
            graph[firstVertex].add(secondVertex);
            transposedGraph[secondVertex].add(firstVertex);
        }

        Deque<Integer> sortedVertex = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        for (int vertex = 1; vertex <= n; vertex++) {
            if (!visited[vertex]) {
                sort(graph, vertex, visited, sortedVertex);
            }
        }

        Arrays.fill(visited, false);
        int[] components = new int[n + 1];
        int componentId = 0;
        int currVertex;
        for (int i = 0; i < n; i++) {
            currVertex = sortedVertex.pop();
            if (!visited[currVertex]) {
                dfs(transposedGraph, currVertex, visited, components, ++componentId);
            }
        }

        Set<Integer>[] condensationGraph = new HashSet[componentId];
        for (int i = 0; i < componentId; i++) {
            condensationGraph[i] = new HashSet<>();
        }

        for (int vertex = 1; vertex <= n; vertex++) {
            for (int neighbourVertex : graph[vertex]) {
                if (components[vertex] != components[neighbourVertex]) {
                    condensationGraph[components[vertex]].add(components[neighbourVertex]);
                }
            }
        }
        int edgesCount = IntStream.range(0, componentId)
                .map(i -> condensationGraph[i].size())
                .sum();
        out.println(edgesCount);
    }

    private static void sort(List<Integer>[] graph, int startVertex, boolean[] visited, Deque<Integer> sortedVertex) {
        visited[startVertex] = true;
        for (int neighbourVertex : graph[startVertex]) {
            if (!visited[neighbourVertex]) {
                sort(graph, neighbourVertex, visited, sortedVertex);
            }
        }
        sortedVertex.push(startVertex);
    }

    private static void dfs(List<Integer>[] graph, int startVertex, boolean[] visited, int[] components,
                            int componentId) {
        visited[startVertex] = true;
        components[startVertex] = componentId;
        for (int neighbourVertex : graph[startVertex]) {
            if (!visited[neighbourVertex]) {
                dfs(graph, neighbourVertex, visited, components, componentId);
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
