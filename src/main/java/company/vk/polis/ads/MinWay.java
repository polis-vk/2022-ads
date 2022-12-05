package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class MinWay {
    private static int[][] graph;
    private static int[] dist;
    private static int[] prev;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countNodes = in.nextInt();
        int countEdges = in.nextInt();
        int start = in.nextInt() - 1;
        int end = in.nextInt() - 1;
        graph = new int[3][countEdges];
        for (int i = 0; i < countEdges; i++) {
            graph[0][i] = in.nextInt() - 1;
            graph[1][i] = in.nextInt() - 1;
            graph[2][i] = in.nextInt();
        }

        Dijkstra(countNodes, countEdges, start);
        printPath(start, end);
    }

    private static void Dijkstra(int countNodes, int countEdges, int start) {
        dist = new int[countNodes];
        prev = new int[countNodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[countNodes];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        dist[start] = 0;
        int v;
        int u;

        while (!queue.isEmpty()) {
            v = queue.poll();
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            for (int j = 0; j < countEdges; j++) {
                if (graph[0][j] == v) {
                    u = graph[1][j];
                } else if (graph[1][j] == v) {
                    u = graph[0][j];
                } else {
                    continue;
                }
                if (dist[u] > dist[v] + graph[2][j]) {
                    dist[u] = dist[v] + graph[2][j];
                    prev[u] = v;
                    queue.add(u);
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
