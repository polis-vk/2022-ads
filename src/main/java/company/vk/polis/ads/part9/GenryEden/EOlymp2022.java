package company.vk.polis.ads.part9.GenryEden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class EOlymp2022 {
    private EOlymp2022() {
        // Should not be instantiated
    }
    // submission url: https://www.eolymp.com/ru/submissions/12305311
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
            connections.get(second).add(first);
        }

        int res = getAns(connections);
        if (res != Integer.MAX_VALUE) {
            out.println("Yes");
            out.println(res + 1);
        } else {
            out.println("No");
        }

    }

    private static int getAns(List<List<Integer>> ways) {
        boolean[] accounted = new boolean[ways.size()];
        int[] visited = new int[ways.size()];
        int[] parent = new int[ways.size()];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < ways.size(); i++) {
            if (visited[i] == 0) {
                parent[i] = -1;
                ans = Math.min(ans, findCycle(i, ways, parent, visited, accounted));
            }
        }
        return ans;
     }

    private static int findCycle(int node, List<List<Integer>> ways, int[] parent, int[] visited, boolean[] accounted){
        int ans = Integer.MAX_VALUE;
        visited[node] = 1;
        for (int i: ways.get(node)) {
            if (visited[i] == 0) {
                parent[i] = node;
                ans = Math.min(ans, findCycle(i, ways, parent, visited, accounted));
            } else if (visited[i] == 1 && i != parent[node]) {
                int nowNode = node;
                ans = Math.min(i, ans);
                accounted[i] = true;
                while (!accounted[nowNode]) {
                    accounted[nowNode] = true;
                    ans = Math.min(ans, nowNode);
                    nowNode = parent[nowNode];
                }
            }
        }
        visited[node] = 2;
        return ans;
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
