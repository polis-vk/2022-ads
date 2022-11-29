package company.vk.polis.ads.part4.denisstrizhkin.scc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int[] Vid;
    private static int[] Vflags;
    private static int[] VRflags;
    private static List<List<Integer>> V;
    private static List<List<Integer>> Vr;

    private static final List<Integer> topSorted = new ArrayList<>();
    private static int curId;

    private Main() {
        // Should not be instantiated
    }

    static void dfs1(int curV) {
        Vflags[curV] = 1;
        for (int nextV : V.get(curV)) {
            if (Vflags[nextV] == 0) {
                dfs1(nextV);
            }
        }
        topSorted.add(curV);
    }

    static void dfs2(int curV) {
        VRflags[curV] = 1;
        Vid[curV] = curId;
        for (int nextV : Vr.get(curV)) {
            if (VRflags[nextV] == 0) {
                dfs2(nextV);
            }
        }
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

        for (int v = 1; v <= n; v++) {
            if (Vflags[v] == 0) {
                dfs1(v);
            }
        }

        for (int i = topSorted.size() - 1; i >= 0; i--) {
            int v = topSorted.get(i);
            if (VRflags[v] == 0) {
                curId++;
                dfs2(v);
            }
        }

        Set<List<Integer>> condE = new HashSet<>();
        for (int start = 1; start < V.size(); start++) {
            for (int end : V.get(start)) {
                if (Vid[start] != Vid[end]) {
                    condE.add(new ArrayList<>(Arrays.asList(Vid[start], Vid[end])));
                }
            }
        }
        out.println(condE.size());
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
