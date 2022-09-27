import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW1Task6 {
    private HW1Task6() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        String command = in.next();
        while (!command.equals("exit")) {
            switch (command) {
                case "push_front":
                    deque.pushFront(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.pushBack(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    if (deque.first == null) {
                        out.println("error");
                    } else {
                        out.println(deque.popFront());
                    }
                    break;
                case "pop_back":
                    if (deque.last == null) {
                        out.println("error");
                    } else {
                        out.println(deque.popBack());
                    }
                    break;
                case "front":
                    if (deque.first == null) {
                        out.println("error");
                    } else {
                        out.println(deque.front());
                    }
                    break;
                case "back":
                    if (deque.last == null) {
                        out.println("error");
                    } else {
                        out.println(deque.back());
                    }
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "clear":
                    deque.clear();
                    out.println("ok");
                    break;
            }
            command = in.next();
        }
        out.println("bye");
    }

    private static class Node {
        private final int value;
        private Node prev;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private static class Deque {
        private int size = 0;
        private Node first;
        private Node last;

        public void pushFront(int n) {
            if (first == null) {
                first = new Node(n);
                last = first;
                size++;
                return;
            }
            Node node = new Node(n);
            node.setNext(first);
            first.setPrev(node);
            first = node;
            size++;
        }

        public void pushBack(int n) {
            if (first == null) {
                first = new Node(n);
                last = first;
                size++;
                return;
            }
            Node node = new Node(n);
            node.setPrev(last);
            last.setNext(node);
            last = node;
            size++;
        }

        public int popFront() {
            int value = first.value;
            if (size == 1) {
                first = null;
                last = null;
            } else {
                first = first.next;
                first.setPrev(null);
            }
            size--;
            return value;
        }

        public int popBack() {
            int value = last.value;
            if (size == 1) {
                first = null;
                last = null;
            } else {
                last = last.prev;
                last.setNext(null);
            }
            size--;
            return value;
        }

        public int front() {
            return first.value;
        }

        public int back() {
            return last.value;
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
