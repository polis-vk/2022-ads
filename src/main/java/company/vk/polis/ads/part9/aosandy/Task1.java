package company.vk.polis.ads.part9.aosandy;

import java.io.*;
import java.util.*;

/**
 * Кратчайший путь
 */
public final class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt() + 1;
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            addEdge(graph, in.nextInt(), in.nextInt());
        }

        int[] pred = new int[n];
        int[] dist = new int[n];

        if (!bfs(graph, a, b, n, pred, dist)) {
            out.println(-1);
            return;
        }

        LinkedList<Integer> path = new LinkedList<>();
        int crawl = b;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        out.println(dist[b]);
        StringBuilder ans = new StringBuilder();
        for (int i = path.size() - 1; i >= 0; i--) {
            ans.append(path.get(i)).append(" ");
        }
        out.println(ans);
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> graph, int i, int j) {
        graph.get(i).add(j);
        graph.get(j).add(i);
    }

    private static boolean bfs(ArrayList<ArrayList<Integer>> graph, int a, int b, int n, int pred[], int dist[]) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(pred, -1);

        visited[a] = true;
        dist[a] = 0;
        queue.add(a);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < graph.get(u).size(); i++) {
                int current = graph.get(u).get(i);
                if (!visited[current]) {
                    visited[current] = true;
                    dist[current] = dist[u] + 1;
                    pred[current] = u;
                    queue.add(current);

                    if (current == b) {
                        return true;
                    }
                }
            }
        }
        return false;
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
