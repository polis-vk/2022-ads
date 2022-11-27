import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.StringTokenizer;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class  ShortPath{
    private ShortPath() {
        // Should not be instantiated
    }

    //
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        int[] distance = new int[n + 1];
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int oneVertex = in.nextInt();
            int twoVertex = in.nextInt();
            graph[oneVertex].add(twoVertex);
            graph[twoVertex].add(oneVertex);
        }

        bfs(a, graph, distance, parent);
        getAnswer(parent, b, distance);

    }

    private static void getAnswer(int[] parent, int end, int[] distance) {
        if (parent[end] == -1) {
            System.out.println(-1);
        } else {
            System.out.println(distance[end]);
            ArrayList<Integer> result = new ArrayList<>();

            for (int i = end; i != -1; i = parent[i]) {
                result.add(i);
            }
            Collections.reverse(result);
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i) + " ");
            }
        }
    }

    private static void bfs(int start, ArrayList<Integer>[] graph, int[] distance, int[] parent) {
        Arrays.fill(distance, -1);
        Arrays.fill(parent, -1);
        distance[start] = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int value = queue.poll();
            for (int i = 0; i < graph[value].size(); i++) {
                int to = graph[value].get(i);
                if (distance[to] == -1) {
                    queue.add(to);
                    distance[to] = distance[value] + 1;
                    parent[to] = value;
                }
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