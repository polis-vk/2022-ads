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
public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static class Node {
        char value;
        Node prev;
        Node next;

        Node(char value, Node prev, Node next) {
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

        public void push(char value) {
            this.size++;

            if (this.head == null) {
                this.head = new Node(value, null, null);
                return;
            }


            Node temp = new Node(value, null, head);
            head = temp;
        }

        public char back() {
            return head.value;
        }

        public char pop() {
            this.size--;

            char ret = head.value;

            head = head.next;
            return ret;
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {

        SecureStack stack = new SecureStack();
        String input = in.next();

        if (input.length() == 0) {
            out.println("yes");
            return;
        }

        for (int i = 0; i < input.length(); i++) {

            switch (input.charAt(i)) {
                case '{':
                    stack.push('{');
                    break;
                case '(':
                    stack.push('(');
                    break;
                case '[':
                    stack.push('[');
                    break;
                case '}':
                    if (stack.size() > 0 && stack.back() != '{') {
                        out.println("no");
                        return;
                    } else if (stack.size() == 0) {
                        out.println("no");
                        return;
                    }
                    stack.pop();
                    break;
                case ')':
                    if (stack.size() > 0 && stack.back() != '(') {
                        out.println("no");
                        return;
                    } else if (stack.size() == 0) {
                        out.println("no");
                        return;
                    }
                    stack.pop();
                    break;
                case ']':
                    if (stack.size() > 0 && stack.back() != '[') {
                        out.println("no");
                        return;
                    } else if (stack.size() == 0) {
                        out.println("no");
                        return;
                    }
                    stack.pop();
                    break;
                default:
                    return;
            }

        }
        if (stack.size() == 0) {
            out.println("yes");
        } else {
            out.println("no");
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
