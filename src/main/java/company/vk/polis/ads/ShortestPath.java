package company.vk.polis.ads;

import java.io.*;
import java.util.*;

public class ShortestPath {

    // https://www.eolymp.com/ru/submissions/12312018

    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt(); // n
        int e = in.nextInt(); // m
        int a = in.nextInt();
        int b = in.nextInt();
        LinkedList<Integer>[] adj = new LinkedList[v + 1];
        for (int i = 0; i < v + 1; i++) {
            adj[i] = new LinkedList<>();
        }

        for (int i = 0; i < e; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            adj[v1].add(v2);
            adj[v2].add(v1);
        }
        bfs(adj, a, b, out);
    }

    private static void bfs(LinkedList<Integer>[] adj, int a, int b, PrintWriter out) {
        int[] d = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            d[i] = -1;
        }
        d[a] = 0;

        int[] p = new int[adj.length];
        p[a] = -1;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(a);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Integer x : adj[v]) {
                if (d[x] == -1) {
                    d[x] = d[v] + 1;
                    p[x] = v;
                    if (x.equals(b)) {
                        break;
                    }
                    queue.add(x);
                }
            }
        }
        out.println(d[b]);
        restorePath(d, p, b, out);
    }

    private static void restorePath(int[] d, int[] p, int b, PrintWriter out) {
        int[] path = new int[1 + d[b]];
        for (int i = d[b], current = b; i >= 0; i--, current = p[current]) {
            path[i] = current;
        }
        for (int i : path) {
            out.println(i + 1);
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
