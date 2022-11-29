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
    private static int[] Vflag;
    private static List<List<Integer>> V;
    private static int[] cycle;
    private static int min = Integer.MAX_VALUE;

    private Main() {
        // Should not be instantiated
    }

    private static void dfs(int curV, int prevV) {
        Vflag[curV] = 1;
        for (int nextV : V.get(curV)) {
            if (Vflag[nextV] == 0) {
                cycle[nextV] = curV;
                dfs(nextV, curV);
            } else if ((Vflag[nextV] == 1 || Vflag[nextV] == 3) && prevV != nextV) {
                min = Math.min(nextV, min);
                Vflag[nextV] = 3;
                for (int v = curV; v != nextV; v = cycle[v]) {
                    if (Vflag[v] == 3) {
                        break;
                    }
                    min = Math.min(v, min);
                    Vflag[v] = 3;
                }
            }
        }
        Vflag[curV] = -1;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Vflag = new int[n + 1];
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
            if (Vflag[i] == 0) {
                dfs(i, 0);
            }
        }

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
