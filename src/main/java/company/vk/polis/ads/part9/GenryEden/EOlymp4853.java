package company.vk.polis.ads.part9.GenryEden;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class EOlymp4853 {
    private EOlymp4853() {
        // Should not be instantiated
    }

    // submission url: https://www.eolymp.com/ru/submissions/12297659
    private static void solve(final FastScanner in, final PrintWriter out) {
        List<List<Integer>> connections;
        int n = in.nextInt();
        int m = in.nextInt();
        connections = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            connections.add(new LinkedList<>());
        }
        int a = in.nextInt() - 1;
        int b = in.nextInt() - 1;
        for (int cnt = 0; cnt < m; cnt++){
            int first = in.nextInt() - 1;
            int second = in.nextInt() - 1;
            connections.get(first).add(second);
            connections.get(second).add(first);
        }

        int[] lenghts = new int[n];
        int[] prevElement = new int[n];

        for (int i = 0; i < n; i++) {
            lenghts[i] = -1;
            prevElement[i] = -1;
        }

        lenghts[a] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);
        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            if (nowNode == b) {
                break;
            }
            for (var i: connections.get(nowNode)) {
                if (lenghts[i] == -1) {
                    queue.add(i);
                    lenghts[i] = lenghts[nowNode] + 1;
                    prevElement[i] = nowNode;
                }
            }
        }

        out.println(lenghts[b]);
        if (lenghts[b] != -1) {
            Deque<Integer> way = new LinkedList<>();
            int nowNode = b;
            way.add(b);
            while (nowNode != a) {
                nowNode = prevElement[nowNode];
                way.add(nowNode);
            }
            while (!way.isEmpty()) {
                out.print((way.pollLast() + 1) + " ");
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
