import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW1Task2 {
    private HW1Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        String command = in.next();
        while (!command.equals("exit")) {
            switch (command) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (queue.first == null) {
                        out.println("error");
                    } else {
                        out.println(queue.pop());
                    }
                    break;
                case "front":
                    if (queue.first == null) {
                        out.println("error");
                    } else {
                        out.println(queue.front());
                    }
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
            }
            command = in.next();
        }
        out.println("bye");
    }

    private static class Node {
        private final int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private static class Queue {
        private int size = 0;
        private Node first;
        private Node last;

        public void push(int n) {
            if (first == null) {
                first = new Node(n);
                last = first;
                size++;
                return;
            }
            Node node = new Node(n);
            last.setNext(node);
            last = node;
            size++;
        }

        public int pop() {
            int value = first.value;
            first = first.next;
            size--;
            return value;
        }

        public int front() {
            return first.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            first = null;
            last = null;
            size = 0;
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
