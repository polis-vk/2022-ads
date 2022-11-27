package company.vk.polis.ads.part4.denisstrizhkin.cycles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[] Vu;
    private static List<Integer>[] V;
    private static Deque<Integer> cycleStack = new ArrayDeque<>();
    private static int[] cycle;
    private static int cycle_start;
    private static int cycle_end;

    private Main() {
        // Should not be instantiated
    }

    private static boolean dfs(int v) {
        Vu[v] = 1;
        if (V[v] != null) {
            for (int curV : V[v]) {
                if (Vu[curV] == 0) {
                    cycle[curV] = v;
                    if (dfs(curV)) {
                        return true;
                    }
                } else if (Vu[curV] == 1) {
                    cycle_end = v;
                    cycle_start = curV;
                    return true;
                }
            }
        }
        Vu[v] = -1;
        return false;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Vu = new int[n + 1];
        V = new List[n + 1];
        cycle = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (V[x] == null) {
                V[x] = new ArrayList<>();
            }
            V[x].add(y);
        }

        for (int i = 1; i <= n; i++) {
            if (dfs(i)) {
                break;
            }
        }

        if (cycle_start == 0) {
            out.println("No");
            return;
        }
        int min = cycle_start;
        int count = 0;
        for (int v = cycle_end; v != cycle_start; v = cycle[v]) {
            if (v < min) {
                min = v;
            }
            count++;
        }
        if (count == 1) {
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
