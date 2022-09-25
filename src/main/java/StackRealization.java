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
public final class StackRealization {
    private StackRealization() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        String command;
        do {
            command = in.next();
            switch (command) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (stack.head != null) {
                        out.println(stack.pop());
                    } else {
                        out.println("error");
                    }
                    break;
                case "back":
                    if (stack.head != null) {
                        out.println(stack.back());
                    } else {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    break;
            }
        } while (!command.equals("exit"));
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

    public static class Stack {

        public Node head;

        public Stack() {
            head = null;
        }

        public class Node {
            public int data;
            public Node prev;

            public Node(int data) {
                this.data = data;
                prev = null;
            }
        }

        public void push(int data) {
            Node newNode = new Node(data);
            if (head != null) {
                newNode.prev = head;
            }
            head = newNode;
        }

        public int pop() {
            if (head != null) {
                int result = head.data;
                head = head.prev;
                return result;
            } else {
                throw new NullPointerException("error");
            }
        }

        public int back() {
            if (head != null) {
                return head.data;
            } else {
                throw new NullPointerException("error");
            }
        }

        public int size() {
            int counter = 0;
            Node currentNode = head;
            while (currentNode != null) {
                counter++;
                currentNode = currentNode.prev;
            }
            return counter;
        }

        public void clear() {
            while (head != null) {
                head = head.prev;
            }
        }
    }
}