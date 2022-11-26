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
            graph.addEdge(in.nextInt(), in.nextInt());
        }
        graph.dist[start] = 0;
        graph.bfs(start);
        graph.restorePath(finish, graph.dist[finish]);
        if (graph.pathList.get(graph.pathList.size() - 1) == 0) {
            out.println("-1");
            return;
        }
        out.println(graph.dist[finish]);
        graph.pathList.add(start);
        Collections.reverse(graph.pathList);
        for (Integer i : graph.pathList) {
            out.print(i + " ");
        }
    }

    private static class Graph {
        private final Map<Integer, ArrayList<Integer>> adjacencyList;
        private final List<Integer> pathList = new ArrayList<>();
        private final Queue<Integer> queue = new ArrayDeque<>();
        private final Color[] visited;
        private final int[] dist;
        private final int[] roots;

        public Graph(int countOfNodes) {
            this.adjacencyList = new HashMap<>(countOfNodes);
            this.visited = new Color[countOfNodes];
            Arrays.fill(visited, Color.WHITE);
            this.dist = new int[countOfNodes];
            this.roots = new int[countOfNodes];
            for (int i = 1; i < countOfNodes; i++) {
                adjacencyList.put(i, new ArrayList<>());
                dist[i] = Integer.MAX_VALUE;
            }
        }

        private enum Color {
            WHITE,
            RED
        }

        private void addEdge(int firstNode, int secondNode) {
            adjacencyList.get(firstNode).add(secondNode);
            adjacencyList.get(secondNode).add(firstNode);
        }

        private void restorePath(int finish, int pathLength) {
            pathList.add(finish);
            if (finish == 0) {
                return;
            }
            if (pathList.size() != pathLength) {
                restorePath(roots[finish], pathLength);
            }
        }

        private void bfs(int node) {
            queue.add(node);
            visited[node] = Color.RED;
            while (!queue.isEmpty()) {
                int curNode = queue.poll();
                for (int vertex : adjacencyList.get(curNode)) {
                    if (visited[vertex] == Color.WHITE) {
                        if (dist[curNode] + 1 < dist[vertex]) {
                            dist[vertex] = dist[curNode] + 1;
                            roots[vertex] = curNode;
                        }
                        visited[vertex] = Color.RED;
                        queue.add(vertex);
                    }
                }
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
