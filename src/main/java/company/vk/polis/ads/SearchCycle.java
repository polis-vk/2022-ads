package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class SearchCycle {
    private SearchCycle() {
        // Should not be instantiated
    }

    private static List<List<Integer>> list;
    private static List<Status> statuses;
    private static int[] previous;
    private static Set<Integer> results;


    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        list = new ArrayList<>(vertexNumber + 1);
        statuses = new ArrayList<>(vertexNumber + 1);
        previous = new int[vertexNumber + 1];
        results = new HashSet<>();
        for (int i = 0; i <= vertexNumber; i++) {
            list.add(new ArrayList<>());
            statuses.add(Status.WHITE);
        }
        for (int i = 0; i < edgesNumber; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            list.get(a).add(b);
            list.get(b).add(a);
        }
        for (int node = 1; node <= vertexNumber; node++) {
            if (statuses.get(node).equals(Status.WHITE)) {
                dfs(node);
            }
        }
        if (!results.isEmpty())
            System.out.println("Yes\n" + Collections.min(results));
        else
            System.out.println("No");
    }

    private static void dfs(int source) {
        statuses.set(source, Status.GREY);
        for (int i : list.get(source)) {
            if (statuses.get(i).equals(Status.WHITE)) {
                previous[i] = source;
                dfs(i);
            }
            if (statuses.get(i).equals(Status.GREY) && i != previous[source]) {
                int end = source;
                while (end != i && !results.contains(i)) {
                    results.add(end);
                    end = previous[end];
                }
                results.add(end);
            }
        }
        statuses.set(source, Status.BLACK);
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

enum Status {
    WHITE, GREY, BLACK
}