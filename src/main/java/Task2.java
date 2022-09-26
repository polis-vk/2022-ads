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
public final class Task2 {
    private Task2() {
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

    private static class SecureQueue {
        private Node head;
        private Node tail;
        private int size;

        SecureQueue() {
            this.clear();
        }

        public void clear() {
            this.head = this.tail = null;
            this.size = 0;
        }

        public int size() {
            return size;
        }

        public void push(int value) {
            this.size++;

            if (this.tail == null) {
                this.head = new Node(value, null, null);
                tail = head;
                return;
            }

            tail.next = new Node(value, tail, null);
            tail = tail.next;
        }

        public int front() {
            return head.value;
        }

        public int pop() {
            this.size--;

            int ret = head.value;

            if (head == tail) {
                head = tail = null;
                return ret;
            }

            head = head.next;
            return ret;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        SecureQueue queue = new SecureQueue();

        while (true) {
            String input = in.next();

            if (input.equals("exit")) {
                out.println("bye");
                return;
            } else if (input.equals("size")) {
                out.println(queue.size());
            } else if (input.equals("clear")) {
                queue.clear();
                out.println("ok");
            } else if (input.equals("front")) {
                if (queue.size() == 0) {
                    out.println("error");
                } else {
                    out.println(queue.front());
                }
            } else if (input.equals("pop")) {
                if (queue.size() == 0) {
                    out.println("error");
                } else {
                    out.println(queue.pop());
                }
            } else if (input.equals("push")) {
                queue.push(in.nextInt());
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
