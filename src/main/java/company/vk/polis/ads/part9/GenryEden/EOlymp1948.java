package company.vk.polis.ads.part9.GenryEden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class EOlymp1948 {
    private EOlymp1948() {
        // Should not be instantiated
    }

    // submission url: https://www.eolymp.com/ru/submissions/12300997
    private static void solve(final FastScanner in, final PrintWriter out) {
        List<List<Integer>> connections;
        int n = in.nextInt();
        int m = in.nextInt();
        connections = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            connections.add(new LinkedList<>());
        }

        for (int cnt = 0; cnt < m; cnt++) {
            int first = in.nextInt() - 1;
            int second = in.nextInt() - 1;
            connections.get(first).add(second);
        }

        List<Integer> ans = topSort(connections);
        if (ans == null) {
            out.println(-1);
        } else {
            for (int i : ans) {
                out.print((i + 1) + " ");
            }
        }

    }

    private static List<Integer> topSort(List<List<Integer>> ways) {
        int[] visisted = new int[ways.size()];
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = 0; i < ways.size(); i++) {
            if (visisted[i] == 0) {
                if (!dfs(i, ways, visisted, ans)) {
                    return null;
                }
            }
        }
        return ans;
    }

    private static boolean dfs(int node, List<List<Integer>> ways, int[] visited, Deque<Integer> sorted) {
        visited[node] = 1;
        for (int i : ways.get(node)) {
            if (visited[i] == 0) {
                if (!dfs(i, ways, visited, sorted)) {
                    return false;
                }
            } else if (visited[i] == 1) {
                return false;
            }
        }
        visited[node] = 2;
        sorted.addFirst(node);
        return true;

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
