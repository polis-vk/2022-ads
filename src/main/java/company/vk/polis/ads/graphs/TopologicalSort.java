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


public class TopologicalSort {

    private TopologicalSort() {
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
            graph.dfs(i);
        }
        if (graph.isCyclic) {
            out.println("-1");
        } else {
            Collections.reverse(graph.sortedNodesList);
            for (Integer i : graph.sortedNodesList) {
                out.print(i + " ");
            }
        }
    }

    private static class Graph {
        private final Map<Integer, ArrayList<Integer>> adjacencyList;
        private final List<Integer> sortedNodesList = new ArrayList<>();
        private final Color[] visited;
        private boolean isCyclic;

        public Graph(int countOfNodes) {
            adjacencyList = new HashMap<>(countOfNodes);
            visited = new Color[countOfNodes];
            Arrays.fill(visited, Color.WHITE);
            for (int i = 1; i < countOfNodes; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }
        }

        private enum Color {
            WHITE,
            RED,
            BLACK
        }

        private void addEdge(int firstNode, int secondNode) {
            adjacencyList.get(firstNode).add(secondNode);
        }

        private void dfs(int root) {
            if (visited[root] != Color.BLACK) {
                visited[root] = Color.RED;
            }
            for (int node : adjacencyList.get(root)) {
                if (visited[node] == Color.RED) {
                    isCyclic = true;
                    return;
                }
                if (visited[node] == Color.WHITE) {
                    dfs(node);
                }
            }
            if (visited[root] != Color.BLACK) {
                visited[root] = Color.BLACK;
                sortedNodesList.add(root);
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
