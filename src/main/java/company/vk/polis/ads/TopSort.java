package company.vk.polis.ads;

import java.io.*;
import java.util.*;

public class TopSort {

    //https://www.eolymp.com/ru/submissions/12328111

    private enum Color {
        WHITE, GREY, BLACK
    }

    private static List<List<Integer>> adj;
    private static List<Color> visited;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt(); // n
        int e = in.nextInt(); // m
        adj = new ArrayList<>(v + 1);
        initGraph(in, v, e);
        topSort();
    }

    private static void topSort() {
        LinkedList<Integer> answer = new LinkedList<>();
        visited = new ArrayList<>(adj.size());

        for (int i = 0; i < adj.size(); i++) {
            visited.add(Color.WHITE);
        }
        for (int v = 0; v < adj.size(); v++) {
            if (visited.get(v) == Color.WHITE) {
                boolean acyclic = dfs(v, answer);
                if (!acyclic) {
                    System.out.println("-1");
                    return;
                }
            } else if (visited.get(v) == Color.GREY) {
                System.out.println("-1");
                return;
            }
        }
        for (Integer integer : answer) {
            System.out.println(integer);
        }
    }

    private static boolean dfs(int v, LinkedList<Integer> answer) {
        visited.set(v, Color.GREY);
        for (Integer x : adj.get(v)) {
            if (visited.get(x) == Color.WHITE) {
                boolean acyclic = dfs(x, answer);
                if (!acyclic) {
                    return false;
                }
            } else if (visited.get(x) == Color.GREY) {
                return false;
            }
        }
        answer.addFirst(v);
        visited.set(v, Color.BLACK);
        return true;
    }

    private static void initGraph(final FastScanner in, int v, int e) {
        for (int i = 0; i < v + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            int v1 = in.nextInt();
            int v2 = in.nextInt();
            adj.get(v1).add(v2);
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
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}