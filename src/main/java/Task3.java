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
public final class Task3 {
    private Task3() {
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

    private static class SecureStack {
        private Node head;
        private Node tail;
        private int size;

        SecureStack() {
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

            if (this.head == null) {
                this.head = new Node(value, null, null);
                return;
            }


            Node temp = new Node(value, null, head);
            head = temp;
        }

        public int back() {
            return head.value;
        }

        public int pop() {
            this.size--;

            int ret = head.value;

            head = head.next;
            return ret;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        SecureStack stack = new SecureStack();

        while (true) {
            String input = in.next();

            if (input.equals("exit")) {
                out.println("bye");
                return;
            } else if (input.equals("size")) {
                out.println(stack.size());
            } else if (input.equals("clear")) {
                stack.clear();
                out.println("ok");
            } else if (input.equals("back")) {
                if (stack.size() == 0) {
                    out.println("error");
                } else {
                    out.println(stack.back());
                }
            } else if (input.equals("pop")) {
                if (stack.size() == 0) {
                    out.println("error");
                } else {
                    out.println(stack.pop());
                }
            } else if (input.equals("push")) {
                stack.push(in.nextInt());
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
