package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
//https://www.eolymp.com/ru/submissions/12316875
public final class ShortestWay {

    private ShortestWay() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertex = in.nextInt();
        int edge = in.nextInt();
        int start = in.nextInt();
        int end = in.nextInt();
        Graph graph = new Graph(vertex);

        for (int i = 1; i <= vertex; i++) {
            graph.adjacencyList[i] = new TreeSet<>();
            graph.color[i] = Color.WHITE;
        }

        for (int i = 1; i <= edge; i++) {
            int vertex1 = in.nextInt();
            int vertex2 = in.nextInt();
            graph.adjacencyList[vertex1].add(vertex2);
            graph.adjacencyList[vertex2].add(vertex1);
        }

        Map<Integer, int[]> shortestWay = graph.bfs(start, end);
        int key = shortestWay.keySet().iterator().next();
        if (key == Integer.MAX_VALUE) {
            out.println(-1);
            return;
        }
        out.println(key);

        Deque<Integer> pathRecovery = new LinkedList<>();
        int i = end;
        while (i != -1) {
            pathRecovery.addFirst(i);
            i = shortestWay.get(key)[i];
        }
        pathRecovery.forEach(e -> out.print(e + " "));
    }

    @SuppressWarnings("unchecked")
    private static class Graph {
        private final Set<Integer>[] adjacencyList;
        private final Color[] color;
        private final Deque<Integer> stack = new LinkedList<>();
        private final int[] previousVertexes;
        private final int[] distance;

        public Graph(int vertex) {
            this.adjacencyList = new Set[vertex + 1];
            this.color = new Color[vertex + 1];
            this.previousVertexes = new int[vertex + 1];
            this.distance = new int[vertex + 1];
        }

        private Map<Integer, int[]> bfs(int start, int end) {
            stack.addFirst(start);
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[start] = 0;
            Arrays.fill(previousVertexes, -1);

            while (!stack.isEmpty()) {
                int fromVertex = stack.pollFirst();
                if (fromVertex == end) {
                    continue;
                }
                color[fromVertex] = Color.GREY;
                for (int toVertex : adjacencyList[fromVertex]) {
                    if (color[toVertex] == Color.WHITE) {
                        stack.addFirst(toVertex);
                        int currentDist = distance[fromVertex] + 1;
                        if (distance[toVertex] >= currentDist) {
                            distance[toVertex] = distance[fromVertex] + 1;
                            previousVertexes[toVertex] = fromVertex;
                        }
                    }
                }
            }
            return Collections.singletonMap(distance[end], previousVertexes);
        }
    }

    private enum Color {
        WHITE,
        GREY
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
