package company.vk.polis.ads.paikeee.part9;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class BreadthFirstSearch {
    private BreadthFirstSearch() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            connections.add(new ArrayList<>());
        }
        int a = in.nextInt();
        int b = in.nextInt();
        for (int i = 0; i < m; i++) {
            int begin = in.nextInt();
            int end = in.nextInt();
            connections.get(begin).add(end);
            connections.get(end).add(begin);
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>(n + 1);
        boolean[] visited = new boolean[n + 1];
        int[] lengths = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            lengths[i] = -1;
        }
        int[] prev = new int[n + 1];
        visited[a] = true;
        lengths[a] = 0;
        queue.push(a);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == b) {
                break;
            }
            for (int i : connections.get(current)) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    lengths[i] = lengths[current] + 1;
                    prev[i] = current;
                }
            }
        }
        out.println(lengths[b]);
        if (lengths[b] != -1) {
            queue.clear();
            queue.push(b);
            int current = b;
            while (current != a) {
                current = prev[current];
                queue.push(current);
            }
            queue.forEach(it -> out.print(it + " "));
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


