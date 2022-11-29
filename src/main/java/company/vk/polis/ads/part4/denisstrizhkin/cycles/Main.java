package company.vk.polis.ads.part4.denisstrizhkin.cycles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[] Vu;
    private static List<List<Integer>> V;
    private static List<List<Integer>> cycles = new ArrayList<>();
    private static int[] cycle;
    private static int min = Integer.MAX_VALUE;

    private Main() {
        // Should not be instantiated
    }

    private static void dfs(int v, int prevV) {
        Vu[v] = 1;
        for (int curV : V.get(v)) {
            if (Vu[curV] == 0) {
                cycle[curV] = v;
                dfs(curV, v);
            } else if (Vu[curV] == 1 && prevV != curV) {
                int cycle_end = v;
                int cycle_start = curV;
                min = Math.min(cycle_start, min);
                for (int cV =cycle_end; cV!=cycle_start; cV=cycle[cV])
                    min = Math.min(cV, min);
            }
        }
        Vu[v] = -1;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Vu = new int[n + 1];
        V = new ArrayList<>();
        cycle = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            V.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            V.get(x).add(y);
            V.get(y).add(x);
        }

        for (int i = 1; i <= n; i++) {
            if (Vu[i] == 0) {
                dfs(i, 0);
            }
        }
        //out.println(cycles);
        if (min == Integer.MAX_VALUE) {
            out.println("No");
            return;
        }
        out.println("Yes");
        out.println(min);
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
