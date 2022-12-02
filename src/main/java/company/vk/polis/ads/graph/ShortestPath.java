package company.vk.polis.ads.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 *
 * https://www.eolymp.com/ru/submissions/12318023
 */
public final class ShortestPath {
    private ShortestPath() {
        // Should not be instantiated
    }

    private static class VertInfo {
        private boolean visited;
        private int distance;
        private Integer parent;

        public VertInfo(boolean visited) {
            this.visited = visited;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void setParent(Integer parent) {
            this.parent = parent;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        if (!in.reader.ready()) {
            out.println(-1);
            return;
        }

        int vertNumber = in.nextInt();
        int edgesNumber = in.nextInt();
        int startVert = in.nextInt();
        int finishVert = in.nextInt();
        Map<Integer, List<Integer>> map = new HashMap<>();

        while (in.reader.ready()) {
            int firstVert = in.nextInt();
            int secondVert = in.nextInt();
            map.putIfAbsent(firstVert, new ArrayList<>());
            map.putIfAbsent(secondVert, new ArrayList<>());
            map.get(firstVert).add(secondVert);
            map.get(secondVert).add(firstVert);
        }

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Map<Integer, VertInfo> info = new HashMap<>();

        map.keySet().forEach(key -> info.put(key, new VertInfo(false)));
        info.get(startVert).setVisited(true);
        deque.add(startVert);
        while (!deque.isEmpty()) {
            int u = deque.pollFirst();
            for (Integer vertex: map.get(u)) {
                if (!info.get(vertex).isVisited()) {
                    info.get(vertex).setVisited(true);
                    info.get(vertex).setDistance(info.get(u).distance + 1);
                    info.get(vertex).setParent(u);
                    deque.add(vertex);
                }
                if (u == finishVert) {
                    break;
                }
            }
        }

        String result = restorePath(startVert, finishVert, info);
        out.println(result);
    }

    private static String restorePath(int startVert, int finishVert, Map<Integer, VertInfo> info) {
        Integer curr = finishVert;
        Deque<Integer> resultPath = new ArrayDeque<>();
        resultPath.addFirst(curr);
        while (curr != startVert) {
            curr = info.get(curr).parent;
            if (curr == null) {
                return "-1";
            }
            resultPath.addFirst(curr);
        }
        return (resultPath.size() - 1) + "\n"
                + resultPath.stream().map(String::valueOf).collect(Collectors.joining(" "));
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}