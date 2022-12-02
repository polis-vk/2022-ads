package company.vk.polis.ads.part9.tedbear;

import java.io.*;
import java.util.*;

public class TopSort {
    //https://www.eolymp.com/ru/problems/1948
    static int numberOfVertices;
    static int numberOfEdges;
    static int flag = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        numberOfVertices = in.nextInt();
        numberOfEdges = in.nextInt();

        Graph graph = new Graph(numberOfVertices);

        for (int i = 0; i < numberOfEdges; i++) {
            graph.addEdge(in.nextInt(), in.nextInt());
        }

        topSort(graph, numberOfVertices);
    }

    static void dfs(Graph graph, int vertex, int[] visited, Stack<Integer> stack) {
        visited[vertex] = 1;
        Integer i;

        for (Integer integer : graph.adjLists[vertex]) {
            i = integer;
            if (visited[i] == 1) {
                flag = 1;
            }
            if (visited[i] == 0) {
                dfs(graph, i, visited, stack);
            }
        }

        visited[vertex] = 2;
        stack.push(vertex);
    }

    static void topSort(Graph graph, int numberOfVertices) {
        Stack<Integer> stack = new Stack<>();
        int[] visited = new int[numberOfVertices + 1];

        for (int i = 1; i <= numberOfVertices; i++) {
            visited[i] = 0;
        }

        for (int i = 1; i <= numberOfVertices; i++) {
            if (visited[i] == 0) {
                dfs(graph, i, visited, stack);
            }
        }

        if (flag == 1) {
            System.out.println("-1");
        } else {
            while (!stack.empty()) {
                System.out.print(stack.pop() + " ");
            }
        }
    }

    static class Graph {
        private final ArrayList<Integer>[] adjLists;

        Graph(int numVertices) {
            adjLists = new ArrayList[numVertices + 1];

            for (int i = 0; i <= numVertices; i++) {
                adjLists[i] = new ArrayList<>();
            }
        }

        void addEdge(int src, int dest) {
            if (!adjLists[src].contains(dest)) {
                adjLists[src].add(dest);
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
