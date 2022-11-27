package company.vk.polis.ads.part4.denisstrizhkin.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private Main() {
        // Should not be instantiated
    }

    private static void solve(final Main.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();

        List<Integer>[] ee = new List[n + 1];
        int[][] e = new int[n + 1][2];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();

            if (ee[x] == null) {
                ee[x] = new ArrayList<>();
            }
            if (ee[y] == null) {
                ee[y] = new ArrayList<>();
            }

            ee[x].add(y);
            ee[y].add(x);
        }
        if (ee[a] == null) {
            out.println(-1);
            return;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(a);
        while (e[b][0] == 0 && !queue.isEmpty()) {
            int curV = queue.pollFirst();
            for (int v : ee[curV]) {
                if (e[v][0] == 0) {
                    queue.addLast(v);
                    e[v][0] = 1;
                    e[v][1] = curV;
                }
            }
        }
        if (e[b][0] == 0) {
            out.println(-1);
            return;
        }

        List<Integer> path = new ArrayList<>();
        int curV = b;
        path.add(curV);
        while (curV != a) {
            curV = e[curV][1];
            path.add(curV);
        }

        out.println(path.size() - 1);
        out.print(path.get(path.size() - 1));
        for (int i = path.size() - 2; i >= 0; i--) {
            out.print(" " + path.get(i));
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
        final Main.FastScanner in = new Main.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
