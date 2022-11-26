package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Cycles {
    private static final int ROOT = 1;

    private Cycles() {
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
            graph.dfs(i, ROOT);
        }
        graph.cycleSearch();
        if (graph.isCyclic) {
            out.println("Yes");
            out.println(graph.minNode);
        } else {
            out.println("No");
        }
    }

    private static class Graph {
        private final Map<Integer, ArrayList<Integer>> adjacencyList;
        private final Deque<Integer> stack = new ArrayDeque<>();
        private final int countOfNodes;
        private final Color[] visited;
        private final boolean[] isCycle;
        private boolean isCyclic = false;
        int minNode;

        public Graph(int countOfNodes) {
            this.adjacencyList = new HashMap<>(countOfNodes);
            this.visited = new Color[countOfNodes];
            Arrays.fill(visited, Color.WHITE);
            this.countOfNodes = countOfNodes;
            this.isCycle = new boolean[countOfNodes];
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
            adjacencyList.get(secondNode).add(firstNode);
        }

        private void cycleSearch() {
            for (int i = 1; i < countOfNodes; i++) {
                if (isCycle[i]) {
                    isCyclic = true;
                    minNode = i;
                    break;
                }
            }
        }

        private void dfs(int root, int from) {
            visited[root] = Color.RED;
            stack.push(root);
            for (int node : adjacencyList.get(root)) {
                if (node == from) {
                    continue;
                }
                if (visited[node] == Color.RED) {
                    isCycle[node] = true;
                    for (Integer head : stack) {
                        if (head.equals(node) || isCycle[head]) {
                            break;
                        }
                        isCycle[head] = true;
                    }
                }
                if (visited[node] == Color.WHITE) {
                    dfs(node, root);
                }
            }
            stack.remove();
            visited[root] = Color.BLACK;
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
