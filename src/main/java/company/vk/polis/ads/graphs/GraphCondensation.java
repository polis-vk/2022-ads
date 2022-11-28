package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class GraphCondensation {
    private GraphCondensation() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfNodes = in.nextInt();
        int countOfEdges = in.nextInt();
        Graph graph = new Graph(countOfNodes + 1);
        for (int i = 1; i <= countOfEdges; i++) {
            graph.addEdge(in.nextInt(), in.nextInt());
        }
        for (int i = 1; i <= countOfNodes; i++) {
            graph.topSort(i);
        }
        Collections.reverse(graph.sortedNodesList);
        Arrays.fill(graph.visited, Graph.Color.WHITE);
        int component = 0;
        for (int node : graph.sortedNodesList) {
            if (graph.visited[node] == Graph.Color.WHITE) {
                component++;
                graph.dfs(node, component);
            }
        }
        for (int root = 1; root <= countOfNodes; root++) {
            graph.addConnections(root);
        }
        int sumEdges = graph.connections.values().stream().mapToInt(Set::size).sum();
        out.println(sumEdges);
    }

    private static class Graph {
        private final Map<Integer, ArrayList<Integer>> adjacencyList;
        private final Map<Integer, ArrayList<Integer>> adjacencyListReverse;
        private final Map<Integer, Set<Integer>> connections = new HashMap<>();
        private final List<Integer> sortedNodesList = new ArrayList<>();
        private final Color[] visited;
        private final int[] connectComponents;

        public Graph(int countOfNodes) {
            this.adjacencyList = new HashMap<>(countOfNodes);
            this.adjacencyListReverse = new HashMap<>(countOfNodes);
            this.visited = new Color[countOfNodes];
            this.connectComponents = new int[countOfNodes];
            Arrays.fill(visited, Color.WHITE);
            for (int i = 1; i < countOfNodes; i++) {
                adjacencyList.put(i, new ArrayList<>());
                adjacencyListReverse.put(i, new ArrayList<>());
            }
        }

        private enum Color {
            WHITE,
            RED,
            BLACK
        }

        private void addEdge(int firstNode, int secondNode) {
            adjacencyList.get(firstNode).add(secondNode);
            adjacencyListReverse.get(secondNode).add(firstNode);
        }

        private void addConnections(int root) {
            for (int node : adjacencyList.get(root)) {
                if (connectComponents[root] != connectComponents[node]) {
                    connections.computeIfAbsent(connectComponents[node], k -> new HashSet<>()).add(connectComponents[root]);
                }
            }
        }

        private void topSort(int root) {
            if (visited[root] != Color.BLACK) {
                visited[root] = Color.RED;
            }
            for (int node : adjacencyList.get(root)) {
                if (visited[node] == Color.WHITE) {
                    topSort(node);
                }
            }
            if (visited[root] != Color.BLACK) {
                visited[root] = Color.BLACK;
                sortedNodesList.add(root);
            }
        }

        private void dfs(int root, int component) {
            connectComponents[root] = component;
            visited[root] = Color.RED;
            for (int node : adjacencyListReverse.get(root)) {
                if (visited[node] == Color.WHITE) {
                    dfs(node, component);
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
