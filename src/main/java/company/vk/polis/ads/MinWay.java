package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MinWay {
    private static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

    }

    private static ArrayList<Edge>[] graph;
    private static int[] dist;
    private static int[] prev;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countNodes = in.nextInt();
        int countEdges = in.nextInt();
        int start = in.nextInt() - 1;
        int end = in.nextInt() - 1;
        graph = new ArrayList[countNodes];
        for (int i = 0; i < countNodes; i++) {
            graph[i] = new ArrayList<>();
        }
        int from;
        int to;
        int cost;
        for (int i = 0; i < countEdges; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            cost = in.nextInt();
            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }
        Dijkstra(countNodes, countEdges, start);
        printPath(start, end);
    }

    private static void Dijkstra(int countNodes, int countEdges, int start) {
        dist = new int[countNodes];
        prev = new int[countNodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[countNodes];
        List<Integer> nodes = new ArrayList<>();

        nodes.add(start);
        dist[start] = 0;
        int v;

        while (!nodes.isEmpty()) {
            int indexMinDistNode = 0;
            for (int i = 1; i < nodes.size(); i++) {
                if (dist[nodes.get(i)] < dist[nodes.get(indexMinDistNode)]) {
                    indexMinDistNode = i;
                }
            }
            v = nodes.get(indexMinDistNode);
            nodes.remove(indexMinDistNode);
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            for (Edge u : graph[v]) {
                if (dist[u.to] > dist[v] + u.cost) {
                    dist[u.to] = dist[v] + u.cost;
                    prev[u.to] = v;
                    nodes.add(u.to);
                }
            }
        }
    }

    private static void printPath(int a, int b) {
        if (dist[b] == -1) {
            System.out.println(-1);
            return;
        }
        System.out.println(dist[b]);
        List<Integer> path = new ArrayList<>();
        int cur = b;
        while (cur != a) {
            path.add(cur);
            cur = prev[cur];
        }
        path.add(a);
        Collections.reverse(path);
        for (int v : path) {
            System.out.print(v + 1 + " ");
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
