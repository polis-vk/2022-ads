package company.vk.polis.ads.part4.denisstrizhkin.scc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // https://www.eolymp.com/ru/submissions/12306318 (56%)
    private static int[] Vid;
    private static int[] Vflags;
    private static int[] VRflags;
    private static List<List<Integer>> V;
    private static List<List<Integer>> Vr;

    private static final List<Integer> order = new ArrayList<>();
    private static final List<List<Integer>> components = new ArrayList<>();

    private Main() {
        // Should not be instantiated
    }

    private static void dfs1(int v) {
        Vflags[v] = 1;
        for (int vertex : V.get(v)) {
            if (Vflags[vertex] == 0) {
                dfs1(vertex);
            }
        }
        Vflags[v] = -1;
        order.add(v);
    }

    private static void dfs2(int v) {
        VRflags[v] = 1;
        components.get(components.size() - 1).add(v);
        Vid[v] = components.size() - 1;
        for (int vertex : Vr.get(v)) {
            if (VRflags[vertex] == 0) {
                dfs2(vertex);
            }
        }
        VRflags[v] = -1;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Vflags = new int[n + 1];
        VRflags = new int[n + 1];
        V = new ArrayList<>();
        Vr = new ArrayList<>();
        Vid = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            V.add(new ArrayList<>());
            Vr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            V.get(x).add(y);
            Vr.get(y).add(x);
        }

        for (int i = 1; i <= n; i++) {
            if (Vflags[i] == 0) {
                dfs1(i);
            }
        }

        for (int i = 0; i < n; i++) {
            int v = order.get(n - 1 - i);
            if (VRflags[v] == 0) {
                components.add(new ArrayList<>());
                dfs2(v);
            }
        }

        //out.println(components);
        //out.println(Arrays.toString(Vid));
        int count = 0;
        for (int start = 1; start < V.size(); start++) {
            for (int end : V.get(start)) {
                if (Vid[start] != Vid[end]) {
                    count++;
                }
            }
        }
        out.println(count);
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
