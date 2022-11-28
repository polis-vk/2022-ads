package company.vk.polis.ads.part9.GenryEden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class EOlymp1947 {
    private EOlymp1947() {
        // Should not be instantiated
    }

    // submission url: https://www.eolymp.com/ru/submissions/12315415
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<List<Integer>> ways = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ways.add(new LinkedList<>());
        }
        for (int i = 0; i < m; i++) {
            int first = in.nextInt() - 1;
            int second = in.nextInt() - 1;
            ways.get(first).add(second);
        }

        List<Integer> sorted = topSort(ways);

        var waysTr = transpose(ways);
        int[] colors = new int[n];
        int nowColor = 1;
        for (int i : sorted) {
            if (colors[i] == 0) {
                colorBFS(i, nowColor++, colors, waysTr);
            }
        }

        Set<Pair> pairSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j : ways.get(i)) {
                if (colors[i] != colors[j]) {
                    pairSet.add(new Pair(colors[i], colors[j]));
                }
            }
        }

        out.println(pairSet.size());
    }

    private static class Pair {
        private final int first;
        private final int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    private static void colorBFS(int i, int color, int[] colors, List<List<Integer>> ways) {
        colors[i] = color;
        for (int j : ways.get(i)) {
            if (colors[j] == 0) {
                colorBFS(j, color, colors, ways);
            }
        }
    }

    private static List<List<Integer>> transpose(List<List<Integer>> inp) {
        List<List<Integer>> ans = new ArrayList<>(inp.size());
        for (int i = 0; i < inp.size(); i++) {
            ans.add(new LinkedList<>());
        }
        for (int i = 0; i < inp.size(); i++) {
            for (int j : inp.get(i)) {
                ans.get(j).add(i);
            }
        }
        return ans;
    }

    private static List<Integer> topSort(List<List<Integer>> ways) {
        boolean[] visited = new boolean[ways.size()];
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = 0; i < ways.size(); i++) {
            if (!visited[i]) {
                sortDFS(i, ans, visited, ways);
            }
        }
        return ans;
    }

    private static void sortDFS(int i, Deque<Integer> ans, boolean[] visited, List<List<Integer>> ways) {
        visited[i] = true;
        for (int j : ways.get(i)) {
            if (!visited[j]) {
                sortDFS(j, ans, visited, ways);
            }
        }
        ans.addFirst(i);
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
