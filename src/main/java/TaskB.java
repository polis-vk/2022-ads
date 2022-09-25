import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskB {
    private static final String PUSH_N = "push";
    private static final String POP = "pop";
    private static final String FRONT = "front";
    private static final String SIZE = "size";
    private static final String CLEAR = "clear";
    private static final String EXIT = "exit";
    private static final String OK = "ok";
    private static final String ERROR = "error";
    private static final String BYE = "bye";

    private TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        try {
            while (true) {
                switch (in.next()) {
                    case PUSH_N -> {
                        queue.push(in.nextInt());
                        out.println(OK);
                    }
                    case POP -> {
                        if (queue.size() == 0) {
                            out.println(ERROR);
                        } else {
                            out.println(queue.pop());
                        }
                    }
                    case FRONT -> {
                        if (queue.size() == 0) {
                            out.println(ERROR);
                        } else {
                            out.println(queue.front());
                        }
                    }
                    case SIZE -> out.println(queue.size());
                    case CLEAR -> {
                        queue.clear();
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

    static class Queue {
        private Node front, rear;
        private int size;

        private class Node {
            final private int number;
            private Node next;

            Node(int number, Node next) {
                this.number = number;
                this.next = next;
            }
        }

        public Queue() {
            front = null;
            rear = null;
            size = 0;
        }

        public void push(int n) {
            Node oldRear = rear;
            rear = new Node(n, null);
            if (size == 0) {
                front = rear;
            } else {
                oldRear.next = rear;
            }
            size++;
        }

        public int pop() {
            int num = front.number;
            front = front.next;
            size--;
            if (size == 0) {
                rear = null;
            }
            return num;
        }

        public int front() {
            return front.number;
        }

        public int size() {
            return size;
        }

        public void clear() {
            while (size != 0) {
                front = front.next;
                size--;
            }
            front = null;
            rear = null;
        }
    }
}