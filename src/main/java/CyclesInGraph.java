import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class CyclesInGraph {
    private CyclesInGraph() {
        // Should not be instantiated
    }

    private static ArrayList<Integer> cycleVertexes;
    private static int[] used;
    private static boolean isCycle;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        used = new int[n + 1];
        cycleVertexes = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int oneVertex = in.nextInt();
            int twoVertex = in.nextInt();
            graph[oneVertex].add(twoVertex);
            graph[twoVertex].add(oneVertex);
        }
        for (int i = 1; i <= n; i++) {
            if (graph[i].size() >= 2) {
                if (used[i] == 0) {
                    dfs(i, graph, -1, i);
                }
                if (isCycle) {
                    break;
                }
            }
        }
        if (isCycle) {
            out.println("Yes");
            int end = cycleVertexes.get(cycleVertexes.size() - 1);
            int i = 0;
            while (cycleVertexes.get(i) != end) {
                cycleVertexes.remove(i);
            }
            cycleVertexes.remove(cycleVertexes.size() - 1);
            out.println(findMin(cycleVertexes));
        } else {
            out.println("No");
        }
    }

    private static void dfs(int value, ArrayList<Integer>[] graph, int parent, int he) {
        if (isCycle) {
            return;
        }
        used[value] = 1;
        cycleVertexes.add(value);
        for (int i = 0; i < graph[value].size(); i++) {
            if (used[graph[value].get(i)] == 1) {
                if (graph[value].get(i) == he && graph[value].get(i) != parent) {
                    cycleVertexes.add(graph[value].get(i));
                    isCycle = true;
                    return;
                }
            } else {
                dfs(graph[value].get(i), graph, value, he);
            }
            if (isCycle) {
                return;
            }
        }
        used[value] = 0;
        cycleVertexes.remove(cycleVertexes.size() - 1);
    }

    private static int findMin(ArrayList<Integer> cycleVertexes) {
        int min = cycleVertexes.get(0);
        for (int i = 1; i < cycleVertexes.size(); i++) {
            int temp = cycleVertexes.get(i);
            if (temp < min) {
                min = temp;
            }
        }
        return min;
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