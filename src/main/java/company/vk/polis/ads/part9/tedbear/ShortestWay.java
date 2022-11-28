package company.vk.polis.ads.part9.tedbear;

import java.io.*;
import java.util.*;

public class ShortestWay {
    static int[] dist, parent;

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int numberOfVertices = in.nextInt();
        int numberOfEdges = in.nextInt();

        int startVertex = in.nextInt();
        int endVertex = in.nextInt();

        Graph graph = new Graph(numberOfVertices);
        dist = new int[numberOfVertices + 1];
        parent = new int[numberOfVertices + 1];

        int src, dest;
        for (int i = 0; i < numberOfEdges; i++) {
            src = in.nextInt();
            dest = in.nextInt();
            graph.addEdge(src, dest);
            graph.addEdge(dest, src);
        }

        bfs(graph, startVertex);

        if (parent[endVertex] == -1) {
            System.out.println("-1");
        } else {
            System.out.println(dist[endVertex]);
            ArrayList<Integer> path = new ArrayList<>();
            path.add(endVertex);

            while(parent[endVertex] != -1) {
                endVertex = parent[endVertex];
                path.add(endVertex);
            }

            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
        }
    }

    static void bfs(Graph graph, int start) {
        Arrays.fill(dist,-1);
        dist[start] = 0;
        Arrays.fill(parent,-1);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()) {
            int v = q.poll();
            for(int i = 0; i < graph.adjLists[v].size(); i++) {
                int to = graph.adjLists[v].get(i);
                if (dist[to] == -1) {
                    q.add(to);
                    dist[to] = dist[v] + 1;
                    parent[to] = v;
                }
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
