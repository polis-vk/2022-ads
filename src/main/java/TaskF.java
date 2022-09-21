import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskF {
    private TaskF() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        String input;
        do {
            input = in.next();
            switch (input) {
                case "push_front":
                    deque.push_front(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.push_back(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    if (deque.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(deque.pop_front());
                    break;
                case "pop_back":
                    if (deque.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(deque.pop_back());
                    break;
                case "front":
                    if (deque.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(deque.front());
                    break;
                case "back":
                    if (deque.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(deque.back());
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "clear":
                    deque.clear();
                    out.println("ok");
                    break;
            }
        } while (!"exit".equals(input));
        out.println("bye");
    }

    private static class Deque {
        private static Node head;
        private static Node tail;
        private static int size;

        public void push_front(int value) {
            Node currNode = new Node(value);
            if (head == null) {
                head = currNode;
                tail = head;
            } else {
                currNode.next = head;
                head.previous = currNode;
                head = currNode;
            }
            size++;
        }

        public void push_back(int value) {
            Node currNode = new Node(value);
            if (tail == null) {
                tail = currNode;
                head = tail;
            } else {
                currNode.previous = tail;
                tail.next = currNode;
                tail = currNode;
            }
            size++;
        }

        public int pop_front() {
            Node temp = head;
            head = head.next;
            if (head == null) {
                tail = null;
            } else {
                head.previous = null;
            }
            size--;
            return temp.value;
        }

        public int pop_back() {
            Node temp = tail;
            tail = tail.previous;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
            size--;
            return temp.value;
        }

        public int front() {
            return head.value;
        }

        public int back() {
            return tail.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private static class Node {
            public Node next;
            public Node previous;
            public int value;

            public Node(int value) {
                this.value = value;
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
