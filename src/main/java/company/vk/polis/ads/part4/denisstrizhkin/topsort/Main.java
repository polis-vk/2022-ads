package company.vk.polis.ads.part4.denisstrizhkin.topsort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] V;
    private static List<Integer>[] VV;
    private static int k;

    private Main() {
        // Should not be instantiated
    }

    private static int[] topSort() {
        int[] answer = new int[n];
        boolean isCycle = false;
        for (int i = 1; i <= n; i++) {
            if (V[i] == 0) {
                isCycle = dfs(answer, i);
            }
            if (isCycle) {
                break;
            }
        }
        if (isCycle) {
            return null;
        }
        return answer;
    }

    private static boolean dfs(int[] answer, int v) {
        V[v] = 1;
        List<Integer> vertices = (VV[v] == null ? Collections.emptyList() : VV[v]);
        for (Integer vertex : vertices) {
            if (V[vertex] == 0) {
                if(dfs(answer, vertex)) {
                    return true;
                }
            } else if (V[vertex] == 1) {
                return true;
            }
        }
        answer[k++] = v;
        V[v] = -1;
        return false;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        int m = in.nextInt();

        V = new int[n + 1];
        VV = new List[n + 1];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (VV[x] == null) {
                VV[x] = new ArrayList<>();
            }
            VV[x].add(y);
        }

        int[] answer = topSort();
        if (answer == null) {
            out.println(-1);
            return;
        }

        StringBuilder output = new StringBuilder(Integer.toString(answer[answer.length - 1]));
        for (int i = answer.length - 2; i >= 0; i--) {
            output.append(" ").append(answer[i]);
        }
        out.println(output);
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
