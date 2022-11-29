import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
//https://www.eolymp.com/ru/submissions/12318056
public final class ShortestPath {

    private static class Graph {

        private static final int NOT_VISITED = -1;
        private int V;
        private ArrayList<Integer>[] adjList;

        public Graph(int v) {
            V = v;
            adjList = new ArrayList[v];
            for (int i = 0; i < v; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        private void addEdge(int v, int w) {
            adjList[v].add(w);
            adjList[w].add(v);
        }

        private int[] BFS(int start, int finish) {
            int[] parents = new int[V];
            Arrays.fill(parents, NOT_VISITED);

            ArrayDeque<Integer> queue = new ArrayDeque<>();

            parents[start] = 0;
            queue.add(start);
            while (!queue.isEmpty()) {
                int curNode = queue.poll();
                for (int neighbourNode : adjList[curNode]) {
                    if (parents[neighbourNode] == NOT_VISITED) {
                        parents[neighbourNode] = curNode;
                        queue.add(neighbourNode);
                        if (neighbourNode == finish) {
                            return parents;
                        }
                    }
                }
            }
            return parents;
        }

        private ArrayList<Integer> getPath(int start, int finish, int[] parents) {
            if (parents == null || parents[finish] == NOT_VISITED) {
                return null;
            }
            ArrayList<Integer> path = new ArrayList<>();
            int curNode = finish;
            while (curNode != start) {
                path.add(curNode);
                curNode = parents[curNode];
            }
            path.add(start);
            Collections.reverse(path);
            return path;
        }

    }

    private ShortestPath() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int start = in.nextInt();
        int finish = in.nextInt();
        Graph graph = new Graph(n + 1);
        for (int i = 0; i < m; i++) {
            graph.addEdge(in.nextInt(), in.nextInt());
        }
        ArrayList<Integer> path = graph.getPath(start, finish, graph.BFS(start, finish));
        if (path == null) {
            out.println(-1);
        } else {
            out.println(path.size() - 1);
            out.print(path.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" ")));
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
