package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Main {
    private Main() {
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
    private static int currentMin = Integer.MAX_VALUE;
    private static Stack<Integer> stack = new Stack<>();

    private static void dfs(int parent, int source) {
        if (listStatus.get(source).equals(Status.BLACK)) {
            return;
        }
        listStatus.set(source, Status.GREY);
        if (list.get(source).size() <= 1) {
            listStatus.set(source, Status.BLACK);
            return;
        }
        currentMin = Math.min(currentMin, source);
//        stack.push(source);
        for (int i : list.get(source)) {
            if (i == parent) continue;
            if (listStatus.get(i).equals(Status.WHITE)) {
                dfs(source, i);
            }
            if (listStatus.get(i).equals(Status.GREY)) {
//                currentMin = Math.min(currentMin, i);
//                Deque<Integer> deque = new LinkedList<>(stack);
//                int pop = deque.pop();
//                while (pop != i) {
//                    globalMin = Math.min(globalMin, pop);
//                    pop = deque.pop();
//                }
                globalMin = Math.min(globalMin, currentMin);
                currentMin = Integer.MAX_VALUE;
            }
        }
//        stack.pop();
        listStatus.set(source, Status.BLACK);
    }

    private static class Node {
        private Integer value;
        private Node prev;
        private Set<Node> neighboors = new HashSet<>();

        public Set<Node> getNeighboors() {
            return neighboors;
        }

        private Status status;

        public Node(int value) {
            this.value = value;
            status = Status.WHITE;
        }

        public void addNeighboor(Node node) {
            neighboors.add(node);
        }

        public Node(Integer value, Node prev, Status status) {
            this.value = value;
            this.prev = prev;
            this.status = status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Status getStatus() {
            return status;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(value, node.value) && Objects.equals(prev, node.prev);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", status=" + status +
                    '}';
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

enum Status {
    WHITE, GREY, BLACK
}