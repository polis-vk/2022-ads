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
public final class Task6 {
    private Task6() {
        // Should not be instantiated
    }

       private static class Node {
        int value;
        Node prev;
        Node next;

        Node(int value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private static class Deque {
        private Node head;
        private Node tail;
        private int size;

        Deque() {
            this.clear();
        }

        public void clear() {
            this.head = this.tail = null;
            this.size = 0;
        }

        public int size() {
            return size;
        }

        public void push_front(int value) {
            this.size++;

            if (this.head == null) {
                this.head = new Node(value, null, null);
                tail = head;
                return;
            }

            Node temp = new Node(value, null, head);
            head = temp;
            if (head.next != null) {
                head.next.prev = head;
            }
        }

        public void push_back(int value) {
            this.size++;

            if (this.tail == null) {
                this.tail = new Node(value, null, null);
                head = tail;
                return;
            }

            Node temp = new Node(value, tail, null);
            tail = temp;
            if (tail.prev != null) {
                tail.prev.next = tail;
            }
        }

        public int back() {
            return tail.value;
        }

        public int front() {
            return head.value;
        }

        public int pop_front() {
            this.size--;

            int ret = head.value;

            if (head == tail) {
                head = tail = null;
                return ret;
            }

            head = head.next;
            return ret;
        }

        public int pop_back() {
            this.size--;

            int ret = tail.value;

            if (head == tail) {
                head = tail = null;
                return ret;
            }

            tail = tail.prev;
            return ret;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        Deque deque = new Deque();

        while (true) {
            String input = in.next();

            if (input.equals("exit")) {
                out.println("bye");
                return;
            } else if (input.equals("size")) {
                out.println(deque.size());
            } else if (input.equals("clear")) {
                deque.clear();
                out.println("ok");
            } else if (input.equals("back")) {
                if (deque.size() == 0) {
                    out.println("error");
                } else {
                    out.println(deque.back());
                }
            } else if (input.equals("front")) {
                if (deque.size() == 0) {
                    out.println("error");
                } else {
                    out.println(deque.front());
                }
            } else if (input.equals("pop_back")) {
                if (deque.size() == 0) {
                    out.println("error");
                } else {
                    out.println(deque.pop_back());
                }
            } else if (input.equals("pop_front")) {
                if (deque.size() == 0) {
                    out.println("error");
                } else {
                    out.println(deque.pop_front());
                }
            } else if (input.equals("push_back")) {
                deque.push_back(in.nextInt());
                out.println("ok");
            } else if (input.equals("push_front")) {
                deque.push_front(in.nextInt());
                out.println("ok");
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
