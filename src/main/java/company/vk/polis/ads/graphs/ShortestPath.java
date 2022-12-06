package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class ShortestPath {
    private ShortestPath() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfNodes = in.nextInt();
        int countOfEdges = in.nextInt();
        int start = in.nextInt();
        int finish = in.nextInt();
        Graph graph = new Graph(countOfNodes + 1);
        for (int i = 1; i <= countOfEdges; i++) {
            graph.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        graph.dist[start] = 0;
        graph.dijkstra(start);
        graph.restorePath(start, finish);
        if (graph.pathList.get(graph.pathList.size() - 1) == 0) {
            out.println("-1");
            return;
        }
        out.println(graph.dist[finish]);
        Collections.reverse(graph.pathList);
        for (Integer i : graph.pathList) {
            out.print(i + " ");
        }
    }

    private static class Graph {
        private final Map<Integer, ArrayList<Integer>> adjacencyList;
        private final List<Integer> pathList = new ArrayList<>();
        private final List<List<Integer>> weights;
        private final Queue<Integer> queue = new ArrayDeque<>();
        private final Color[] visited;
        private final int[] dist;
        private final int[] roots;

        public Graph(int countOfNodes) {
            adjacencyList = new HashMap<>(countOfNodes);
            weights = new ArrayList<>(countOfNodes);
            visited = new Color[countOfNodes];
            Arrays.fill(visited, Color.WHITE);
            dist = new int[countOfNodes];
            roots = new int[countOfNodes];
            weights.add(new ArrayList<>());
            for (int i = 1; i < countOfNodes; i++) {
                weights.add(new ArrayList<>());
                adjacencyList.put(i, new ArrayList<>());
                dist[i] = Integer.MAX_VALUE;
            }
        }

        private enum Color {
            WHITE,
            RED
        }

        private void addEdge(int firstNode, int secondNode, int weight) {
            adjacencyList.get(firstNode).add(secondNode);
            weights.get(firstNode).add(weight);
            adjacencyList.get(secondNode).add(firstNode);
            weights.get(secondNode).add(weight);
        }

        private void restorePath(int start, int finish) {
            pathList.add(finish);
            if (finish != start) {
                restorePath(start, roots[finish]);
            }
        }

        private void dijkstra(int node) {
            queue.add(node);
            while (!queue.isEmpty()) {
                int curNode = queue.poll();
                int index = 0;
                for (int vertex : adjacencyList.get(curNode)) {
                    if (dist[curNode] + weights.get(curNode).get(index) < dist[vertex]) {
                        dist[vertex] = dist[curNode] + weights.get(curNode).get(index);
                        roots[vertex] = curNode;
                    }
                    if (visited[curNode] == Color.WHITE) {
                        queue.add(vertex);
                    }
                    index++;
                }
                visited[curNode] = Color.RED;
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
