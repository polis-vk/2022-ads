import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW1Task3 {
    private HW1Task3() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        String command = in.next();
        while (!command.equals("exit")) {
            switch (command) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (stack.last == null) {
                        out.println("error");
                    } else {
                        out.println(stack.pop());
                    }
                    break;
                case "back":
                    if (stack.last == null) {
                        out.println("error");
                    } else {
                        out.println(stack.back());
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
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

        public Node(int value) {
            this.value = value;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private static class Stack {
        private int size = 0;
        private Node last;

        public void push(int n) {
            if (last == null) {
                last = new Node(n);
                size++;
                return;
            }
            Node node = new Node(n);
            node.setPrev(last);
            last = node;
            size++;
        }

        public int pop() {
            int value = last.value;
            last = last.prev;
            size--;
            return value;
        }

        public int back() {
            return last.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
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
