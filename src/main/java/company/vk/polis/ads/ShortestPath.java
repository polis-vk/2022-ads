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

public class ShortestPath {
    private static ArrayList<Integer>[] graph;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countNodes = in.nextInt();
        graph = new ArrayList[countNodes];
        for (int i = 0; i < countNodes; i++) {
            graph[i] = new ArrayList<>();
        }
        int countEdges = in.nextInt();
        int a = in.nextInt() - 1;
        int b = in.nextInt() - 1;
        int first;
        int second;
        for (int i = 0; i < countEdges; i++) {
            first = in.nextInt() - 1;
            second = in.nextInt() - 1;
            graph[first].add(second);
            graph[second].add(first);
        }
        printPath(a, b, BFS(a, b, countNodes));
    }

    private static int[] BFS(int a, int b, int countNodes) {
        int[] dist = new int[countNodes];
        Arrays.fill(dist, -1);
        dist[a] = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(a);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : graph[v]) {
                if (dist[u] == -1) {
                    dist[u] = v;
                    queue.add(u);
                    if (u == b) {
                        return dist;
                    }
                }
            }
        }
        return dist;
    }

    private static void printPath(int a, int b, int[] dist) {
        if (dist[b] == -1) {
            System.out.println(-1);
            return;
        }
        List<Integer> path = new ArrayList<>();
        int cur = b;
        while (cur != a) {
            path.add(cur);
            cur = dist[cur];
        }
        path.add(a);
        Collections.reverse(path);
        System.out.println(path.size() - 1);
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
