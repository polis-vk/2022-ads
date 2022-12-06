package company.vk.polis.ads.graphs;

import java.io.*;
import java.util.*;

public class MinFrame {
    private MinFrame() {
        // Should not be instantiated
    }

    private static final int ROOT = 1;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countOfNodes = in.nextInt();
        int countOfEdges = in.nextInt();
        Graph graph = new Graph(countOfNodes + 1);
        for (int i = 1; i <= countOfEdges; i++) {
            graph.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        graph.dist[1] = 0;
        graph.prim();
        int minWeight = Arrays.stream(graph.dist).sum();
        out.println(minWeight);
    }

    private static class Graph {
        private final Map<Integer, ArrayList<Integer>> adjacencyList;
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

        private void prim() {
            queue.add(ROOT);
            while (!queue.isEmpty()) {
                int curNode = queue.poll();
                int index = 0;
                for (int vertex : adjacencyList.get(curNode)) {
                    if (weights.get(curNode).get(index) < dist[vertex] && vertex != roots[curNode]) {
                        dist[vertex] = weights.get(curNode).get(index);
                        roots[vertex] = curNode;
                    }
                    if (visited[vertex] == Color.WHITE) {
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
