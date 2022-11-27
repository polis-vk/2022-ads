import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class GraphCondensation {
    private GraphCondensation() {
        // Should not be instantiated
    }

    private static ArrayList<Integer>[] graph;
    private static ArrayList<Integer>[] reversGraph;
    private static int used[];
    private static int colors[];
    private static ArrayList<Integer> vertexes = new ArrayList<>();
    private static TreeSet<Pair> result = new TreeSet<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        reversGraph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            reversGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int one = in.nextInt();
            int two = in.nextInt();
            graph[one].add(two);
            reversGraph[two].add(one);
        }
        used = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (used[i] == 0) {
                dfsOne(i);
            }
        }
        colors = new int[n + 1];
        Arrays.fill(colors, -1);
        int color = 0;
        for (int i = 1; i <= n; i++) {
            int value = vertexes.get(n - i);
            if (colors[value] == -1) {
                dfsTwo(value, color++);
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                if (colors[i] != colors[graph[i].get(j)]) {
                    result.add(new Pair(colors[i], colors[graph[i].get(j)]));
                }
            }
        }
        out.println(result.size());
    }

    private static void dfsOne(int value) {
        used[value] = 1;
        for (int i = 0; i < graph[value].size(); i++) {
            if (used[graph[value].get(i)] == 0) {
                dfsOne(graph[value].get(i));
            }
        }
        vertexes.add(value);
    }

    private static void dfsTwo(int value, int color) {
        colors[value] = color;
        for (int i = 0; i < reversGraph[value].size(); i++) {
            if (colors[reversGraph[value].get(i)] == -1) {
                dfsTwo(reversGraph[value].get(i), color);
            }
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

    public static class Pair implements Comparable<Pair> {
        int one;
        int two;

        public Pair(int one, int two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public int compareTo(Pair o) {
            if (one == o.one) {
                return two - o.two;
            }
            return one - o.one;
        }
    }
}
