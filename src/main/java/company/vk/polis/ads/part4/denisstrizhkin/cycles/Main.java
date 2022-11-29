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
    private static int cycle_start;
    private static int cycle_end;

    private Main() {
        // Should not be instantiated
    }

    private static boolean dfs(int v, int prevV) {
        Vu[v] = 1;
        for (int curV : V.get(v)) {
            if (Vu[curV] == 0) {
                cycle[curV] = v;
                if (dfs(curV, v)) {
                    return true;
                }
            } else if (Vu[curV] == 1 && prevV != curV) {
                cycle_end = v;
                cycle_start = curV;
                cycles.add(new ArrayList<>());
                cycles.get(cycles.size() - 1).add(cycle_start);
                for (int cV =cycle_end; cV!=cycle_start; cV=cycle[cV])
                    cycles.get(cycles.size() - 1).add(cV);
                return true;
            }
        }
        Vu[v] = -1;
        return false;
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
            if (dfs(i, 0)) {
                break;
            }
        }
        //out.println(cycles);
        if (cycles.size() == 0) {
            out.println("No");
            return;
        }
        out.println("Yes");
        out.println(cycles.stream().flatMap(List::stream).min(Integer::compare).get());
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
