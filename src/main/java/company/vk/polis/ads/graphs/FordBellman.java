package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


public class FordBellman {
    private FordBellman() {
        // Should not be instantiated
    }

    private static final int MAX_PATH = 30_000;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfNodes = in.nextInt();
        int countOfEdges = in.nextInt();
        Graph graph = new Graph(countOfNodes + 1);
        for (int i = 1; i <= countOfEdges; i++) {
            graph.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        graph.dist[1] = 0;
        for (int i = 1; i < countOfNodes; i++) {
            for (int j = 1; j <= countOfNodes; j++) {
                graph.fordBellman(j);
            }
        }
        for (int j = 1; j <= countOfNodes; j++) {
            out.print(graph.dist[j] + " ");
        }
    }

    private static class Graph {
        private final Map<Integer, ArrayList<Integer>> adjacencyList;
        private final List<List<Integer>> weights;
        private final int[] dist;

        public Graph(int countOfNodes) {
            adjacencyList = new HashMap<>(countOfNodes);
            weights = new ArrayList<>(countOfNodes);
            dist = new int[countOfNodes];
            weights.add(new ArrayList<>());
            for (int i = 1; i < countOfNodes; i++) {
                weights.add(new ArrayList<>());
                adjacencyList.put(i, new ArrayList<>());
                dist[i] = MAX_PATH;
            }
        }

        private void addEdge(int firstNode, int secondNode, int weight) {
            adjacencyList.get(firstNode).add(secondNode);
            weights.get(firstNode).add(weight);
        }

        private void fordBellman(int node) {
            int index = 0;
            for (int vertex : adjacencyList.get(node)) {
                if (dist[node] == MAX_PATH) {
                    continue;
                }
                if (dist[node] + weights.get(node).get(index) < dist[vertex]) {
                    dist[vertex] = dist[node] + weights.get(node).get(index);
                }
                index++;
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
