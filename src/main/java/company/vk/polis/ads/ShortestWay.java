package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Deque;
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
public final class ShortestWay {
    private ShortestWay() {
        // Should not be instantiated
    }

    private static class Node {
        private int value;
        private Node prev;

        public Node(int value, Node prev) {
            this.value = value;
            this.prev = prev;
        }

        public Node(int value) {
            this.value = value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public int getValue() {
            return value;
        }

        public Node getPrev() {
            return prev;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(value);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        in.nextInt();
        int edgesNumber = in.nextInt();
        Integer start = in.nextInt();
        Integer end = in.nextInt();
        for (int i = 0; i < edgesNumber; i++) {
            Integer from = in.nextInt();
            Integer to = in.nextInt();
            if (!map.containsKey(from)) {
                Set<Integer> set = new HashSet<>();
                set.add(to);
                map.put(from, set);
            } else {
                map.get(from).add(to);
            }
            if (!map.containsKey(to)) {
                Set<Integer> set = new HashSet<>();
                set.add(from);
                map.put(to, set);
            } else {
                map.get(to).add(from);
            }
        }
        display(getShortestWay(map, start, end), start);
    }

    private static void display(Node way, Integer start) {
        Deque<Integer> deque = new LinkedList<>();
        if (way != null) {
            int counter = 0;
            while (way.getPrev() != null) {
                counter++;
                deque.addFirst(way.getValue());
                way = way.getPrev();
            }
            if (way.getValue() == start) {
                System.out.println(counter);
                System.out.print(start + " ");
                deque.forEach(e -> System.out.print(e + " "));
            } else {
                System.out.println(-1);
            }
        } else {
            System.out.println(-1);
        }
    }

    private static Node getShortestWay(Map<Integer, Set<Integer>> map, Integer start, Integer end) {
        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> wasThere = new HashSet<>();
        Deque<Node> nodes = new LinkedList<>();
        wasThere.add(start);
        queue.offer(start);
        nodes.offer(new Node(start));
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            Node currentNode = nodes.poll();
            if (map.get(current).contains(end)) {
                return new Node(end, currentNode);
            }
            for (Integer i : map.get(current)) {
                if (!wasThere.contains(i)) {
                    nodes.offer(new Node(i, currentNode));
                    wasThere.add(i);
                    queue.offer(i);
                }
            }
        }
        return null;
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
