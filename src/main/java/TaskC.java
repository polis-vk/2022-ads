import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskC {
    private static final String PUSH_N = "push";
    private static final String POP = "pop";
    private static final String BACK = "back";
    private static final String SIZE = "size";
    private static final String CLEAR = "clear";
    private static final String EXIT = "exit";
    private static final String OK = "ok";
    private static final String ERROR = "error";
    private static final String BYE = "bye";

    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        try {
            while (true) {
                switch (in.next()) {
                    case PUSH_N -> {
                        stack.push(in.nextInt());
                        out.println(OK);
                    }
                    case POP -> {
                        if (stack.size() == 0) {
                            out.println(ERROR);
                        } else {
                            out.println(stack.pop());
                        }
                    }
                    case BACK -> {
                        if (stack.size() == 0) {
                            out.println(ERROR);
                        } else {
                            out.println(stack.back());
                        }
                    }
                    case SIZE -> out.println(stack.size());
                    case CLEAR -> {
                        stack.clear();
                        out.println(OK);
                    }
                    case EXIT -> {
                        out.println(BYE);
                        return;
                    }
                    default -> throw new IllegalStateException("Unexpected command");
                }
            }
        } catch (NumberFormatException | IllegalStateException e) {
            e.printStackTrace();
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

    static class Stack {
        private Node rear, front;
        private int size;

        private class Node {
            final private int number;
            private Node previous;

            Node(int number, Node previous) {
                this.number = number;
                this.previous = previous;
            }
        }

        public Stack() {
            rear = null;
            front = null;
            size = 0;
        }

        public void push(int n) {
            if (size == 0) {
                front = rear = new Node(n, null);
            } else {
                Node oldRear = rear;
                rear = new Node(n, oldRear);
            }
            size++;
        }

        public int pop() {
            int num = rear.number;
            rear = rear.previous;
            size--;
            if (size == 0) {
                front = null;
            }
            return num;
        }

        public int back() {
            return rear.number;
        }

        public int size() {
            return size;
        }

        public void clear() {
            while (size != 0) {
                rear = rear.previous;
                size--;
            }
            rear = null;
            front = null;
        }
    }
}