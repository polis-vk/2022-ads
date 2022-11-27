import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TopoSort {
    private TopoSort() {
        // Should not be instantiated
    }

    private static ArrayList<Integer> answer;
    private static int[] used;
    private static boolean isCycle = false;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        used = new int[n + 1];
        answer = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int oneVertex = in.nextInt();
            int twoVertex = in.nextInt();
            graph[oneVertex].add(twoVertex);
        }
        for (int i = 1; i <= n; i++) {
            if (used[i] == 0) {
                dfs(i, graph);
            }
        }
        if (isCycle) {
            out.println(-1);
        } else {
            Collections.reverse(answer);
            for (int i = 0; i < answer.size(); i++) {
                out.print(answer.get(i) + " ");
            }
        }
    }

    private static void dfs(int value, ArrayList<Integer>[] graph) {
        used[value] = 1;
        for (int i = 0; i < graph[value].size(); i++) {
            if (used[graph[value].get(i)] == 1) {
                isCycle = true;
            }
            if (used[graph[value].get(i)] == 0) {
                dfs(graph[value].get(i), graph);
            }
        }
        used[value] = 2;
        answer.add(value);
    }

    private static boolean checkGraph(ArrayList<Integer>[] graph) {
        for (int i = 1; i < graph.length; i++) {
            if (graph[i].isEmpty()) {
                return true;
            }
        }
        return false;
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