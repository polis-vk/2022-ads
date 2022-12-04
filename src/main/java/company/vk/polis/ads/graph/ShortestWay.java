package company.vk.polis.ads.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

// Submission link: https://www.eolymp.com/ru/submissions/12368771
public final class ShortestWay {
    private static final int INFINITY = Integer.MAX_VALUE;
    private static final int UNDEFINED = 0;

    private ShortestWay() {
        // Should not be instantiated
    }

    private static record Way(int to, int weight) {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int source = in.nextInt();
        int target = in.nextInt();

        Set<Way>[] adjacentVertices = new Set[n + 1];

        for (int i = 1; i <= n; i++) {
            adjacentVertices[i] = new HashSet<>();
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int weight = in.nextInt();
            adjacentVertices[from].add(new Way(to, weight));
            adjacentVertices[to].add(new Way(from, weight));
        }

        int[] dist = new int[n + 1];
        int[] prev = new int[n + 1];
        boolean[] used = new boolean[n + 1];

        Arrays.fill(dist, INFINITY);

        dist[source] = 0;

        for (int i = 1; i <= n; i++) {
            int v = -1;

            for (int j = 1; j <= n; j++) {
                if (!used[j] && (v == -1 || dist[j] < dist[v])) {
                    v = j;
                }
            }

            if (dist[v] == INFINITY) {
                break;
            }

            used[v] = true;

            for (Way adjacentVertex : adjacentVertices[v]) {
                int temp = dist[v] + adjacentVertex.weight;
                if (temp < dist[adjacentVertex.to]) {
                    dist[adjacentVertex.to] = temp;
                    prev[adjacentVertex.to] = v;
                }
            }
        }

        if (dist[target] == INFINITY) {
            out.println(-1);
            return;
        }

        List<Integer> path = new LinkedList<>();
        int i = target;
        while (i != UNDEFINED) {
            path.add(i);
            i = prev[i];
        }
        Collections.reverse(path);
        out.println(dist[target]);
        path.forEach(integer -> out.print(integer + " "));
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