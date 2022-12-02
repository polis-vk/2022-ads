package company.vk.polis.ads.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class ShortWay {
    private ShortWay() {
        // Should not be instantiated
    }

    private static class Graph {

        private final ArrayList<Integer>[] graph;
        private final int vertices;

        @SuppressWarnings("unchecked")
        public Graph(int v) {
            vertices = v;
            graph = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        private void add(int vertices, int edges) {
            graph[vertices].add(edges);
            graph[edges].add(vertices);
        }

        //-1 - not visited
        private int[] bfs(int start, int finish) {
            int[] ancestors = new int[vertices];
            Arrays.fill(ancestors, -1);
            Queue<Integer> queue = new ArrayDeque<>();
            ancestors[start] = 0;
            queue.add(start);
            while (!queue.isEmpty()) {
                int currentElement = queue.poll();
                for (int neighbour : graph[currentElement]) {
                    if (ancestors[neighbour] == -1) {
                        ancestors[neighbour] = currentElement;
                        queue.add(neighbour);
                        if (neighbour == finish) {
                            return ancestors;
                        }
                    }
                }
            }
            return ancestors;
        }

        public void printShortestDistance(int[] ancestors, int start, int finish, PrintWriter out) {
            if (ancestors == null || ancestors[finish] == -1) {
                out.println("-1");
                return;
            }
            ArrayList<Integer> shortDistance = new ArrayList<>();
            int temp = finish;
            while (temp != start) {
                shortDistance.add(temp);
                temp = ancestors[temp];
            }
            shortDistance.add(start);
            Collections.reverse(shortDistance);
            if (shortDistance.isEmpty()) {
                out.println("-1");
            }
            out.println(shortDistance.size() - 1);
            for (Integer element : shortDistance) {
                out.print(element + " ");
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numVertices = in.nextInt();
        int numEdges = in.nextInt();
        Graph graph = new Graph(numVertices + 1);
        int startPeak = in.nextInt();
        int finishPeak = in.nextInt();
        for (int i = 0; i < numEdges; i++) {
            graph.add(in.nextInt(), in.nextInt());
        }
        graph.printShortestDistance(graph.bfs(startPeak, finishPeak), startPeak, finishPeak, out);
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
