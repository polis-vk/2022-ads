package company.vk.polis.ads.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

// Submission link: https://www.eolymp.com/ru/submissions/12293093
public final class ShortestWay {
    private ShortestWay() {
        // Should not be instantiated
    }

    private static class Way {
        Way prev;
        int current;
        int length;

        public Way(Way prev, int current, int length) {
            this.prev = prev;
            this.current = current;
            this.length = length;
        }
    }

    private static Way shortestWaySearch(int start, int end, Map<Integer, Set<Integer>> ways) {
        Queue<Way> queue = new LinkedList<>();
        Set<Integer> visitedVertexes = new HashSet<>();
        queue.add(new Way(null, start, 0));
        while (!queue.isEmpty()) {
            Way current = queue.poll();
            if (current.current == end) {
                return current;
            }
            if (ways.containsKey(current.current)) {
                for (Integer adjacentVertex : ways.get(current.current)) {
                    if (!visitedVertexes.contains(adjacentVertex)) {
                        visitedVertexes.add(adjacentVertex);
                        queue.add(new Way(current, adjacentVertex, current.length + 1));
                    }
                }
            }
        }
        return null;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int start = in.nextInt();
        int end = in.nextInt();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            map.putIfAbsent(from, new HashSet<>());
            map.get(from).add(to);
            map.putIfAbsent(to, new HashSet<>());
            map.get(to).add(from);
        }
        Way way = shortestWaySearch(start, end, map);
        if (way == null) {
            out.println(-1);
            return;
        }
        out.println(way.length);
        List<Integer> list = new ArrayList<>();
        while (way != null) {
            list.add(way.current);
            way = way.prev;
        }
        Collections.reverse(list);
        list.forEach(integer -> out.print(integer + " "));
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
