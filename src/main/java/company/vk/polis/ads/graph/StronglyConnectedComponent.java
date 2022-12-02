package company.vk.polis.ads.graph;

import java.io.*;
import java.util.*;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 *
 * https://www.eolymp.com/ru/submissions/12353834
 */
public final class StronglyConnectedComponent {
    private StronglyConnectedComponent() {
        // Should not be instantiated
    }

    private enum Color {
        WHITE, GRAY, BLACK
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[vertexNumber];
        int[] sccId = new int[vertexNumber];
        Color[] visited = new Color[vertexNumber];

        for (int i = 0; i < vertexNumber; i++) {
            graph[i] = new ArrayList<>();
            visited[i] = Color.WHITE;
        }

        for (int i = 0; i < edgesNumber; i++) {
            int firstVert = in.nextInt();
            int secondVert = in.nextInt();
            graph[firstVert - 1].add(secondVert - 1);
        }

        Deque<Integer> topSort = new ArrayDeque<>();
        for (int i = 0; i < vertexNumber; i++) {
            if (visited[i] == Color.WHITE) {
                topSort(visited, topSort, graph, i);
            }
        }

        ArrayList<Integer>[] transposeGraph = transpose(graph, vertexNumber);
        int id = 1;
        for (Integer vertex : topSort) {
            if (sccId[vertex] > 0) {
                scc(transposeGraph, sccId, id, vertex);
            }
            id++;
        }

        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < sccId.length; i ++) {
            if (transposeGraph[i].size() > 0) {
                set.add(sccId[i]);
            }
        }

        out.println(set.size() - 1);
    }

    private static void scc(ArrayList<Integer>[] graph, int[] sccId, int id, int vertex) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(vertex);
        while (!deque.isEmpty()) {
            int dequeVertex = deque.poll();
            for (Integer currVertex : graph[dequeVertex]) {
                if (sccId[currVertex] == 0) {
                    sccId[currVertex] = id;
                    deque.addFirst(currVertex);
                }
            }
        }
        sccId[vertex] = id;
    }

    private static void topSort(Color[] visited, Deque<Integer> topSort, List<Integer>[] graph, int vertex) {
        visited[vertex] = Color.BLACK;
        for (Integer currVertex : graph[vertex]) {
            Color visitedCurrState = visited[currVertex];
            if (visitedCurrState == Color.WHITE) {
                topSort(visited, topSort, graph, currVertex);
            }
        }
        topSort.addFirst(vertex);
    }

    private static ArrayList<Integer>[] transpose(List<Integer>[] graph, int vertexNumber) {
        ArrayList<Integer>[] transposeGraph = new ArrayList[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            transposeGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < vertexNumber; i++) {
            for (Integer currVertex : graph[i]) {
                transposeGraph[currVertex].add(i);
            }
        }
        return transposeGraph;
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

