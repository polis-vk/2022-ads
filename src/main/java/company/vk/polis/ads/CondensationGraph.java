package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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


public final class CondensationGraph {
    private static List<List<Integer>> nodes;
    private static List<List<Integer>> invertedNodes;
    private static final List<Integer> topologicalSortList = new LinkedList<>();
    private static byte[] state;
    private static int[] colors;


    private CondensationGraph() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        nodes = new ArrayList<>();
        invertedNodes = new ArrayList<>();
        state = new byte[vertexNumber + 1];
        colors = new int[vertexNumber + 1];

        for (int i = 0; i <= vertexNumber; i++) {
            nodes.add(new ArrayList<>());
            invertedNodes.add(new ArrayList<>());
        }
        for (int i = 0; i < edgesNumber; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            nodes.get(a).add(b);
            invertedNodes.get(b).add(a);
        }
        for (int i = 1; i <= vertexNumber; i++) {
            if (state[i] == 0) {
                dfs1(i);
            }
        }
        int color = 0;
        Arrays.fill(colors, -1);
        for (int current : topologicalSortList) {
            if (colors[current] == -1) {
                dfs2(current, color++);
            }
        }
        Set<Integer>[] condensationGraph = new HashSet[color + 1];
        for (int i = 1; i < nodes.size(); i++) {
            for (int to : nodes.get(i)) {
                if (colors[i] != colors[to]) {
                    if (condensationGraph[colors[to]] == null) {
                        condensationGraph[colors[to]] = new HashSet<>();
                    }
                    condensationGraph[colors[to]].add(colors[i]);
                }
            }
        }
        out.println(Arrays.stream(condensationGraph)
                .filter(Objects::nonNull)
                .mapToInt(Set::size)
                .sum());
    }

    private static void dfs1(int node) {
        state[node] = 1;
        for (int neighboor : nodes.get(node)) {
            if (state[neighboor] == 0) {
                dfs1(neighboor);
            }
        }
        topologicalSortList.add(0, node);
    }

    private static void dfs2(int node, int color) {
        colors[node] = color;
        for (int neighboor : invertedNodes.get(node)) {
            if (colors[neighboor] == -1) {
                dfs2(neighboor, color);
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
}