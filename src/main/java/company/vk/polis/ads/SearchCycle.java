package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
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
    private static List<Status> listStatus;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        list = new ArrayList<>(vertexNumber + 1);
        listStatus = new ArrayList<>(vertexNumber + 1);
        for (int i = 0; i <= vertexNumber; i++) {
            list.add(new ArrayList<>());
            listStatus.add(Status.WHITE);
        }
        for (int i = 0; i < edgesNumber; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            list.get(a).add(b);
            list.get(b).add(a);
        }
        for (int node = 1; node <= vertexNumber; node++) {
            if (listStatus.get(node).equals(Status.WHITE)) {
                dfs(-1, node);
            }
        }
        if (Integer.compare(globalMin, Integer.MAX_VALUE) != 0)
            System.out.println("Yes\n" + globalMin);
        else
            System.out.println("No");
    }

    private static int globalMin = Integer.MAX_VALUE;
    private static Stack<Integer> stack = new Stack<>();
    private static Deque<Integer> deque = new ArrayDeque<>();

    private static void dfs(int parent, int source) {
        stack.push(source);
        listStatus.set(source, Status.GREY);
        for (int i : list.get(source)) {
            if (i == parent) continue;
            if (listStatus.get(i).equals(Status.WHITE)) {
                dfs(source, i);
            }
            if (listStatus.get(i).equals(Status.GREY)) {
                deque = new LinkedList<>(stack);
                int pop = deque.removeLast();
                globalMin = Math.min(pop, globalMin);
                while (pop != i) {
                    pop = deque.getLast();
                    deque.removeLast();
                    globalMin = Math.min(pop, globalMin);
                }
            }
        }
        listStatus.set(source, Status.BLACK);
        stack.pop();
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