import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskF {
    private static final String PUSH_FRONT = "push_front";
    private static final String PUSH_BACK = "push_back";
    private static final String POP_FRONT = "pop_front";
    private static final String POP_BACK = "pop_back";
    private static final String FRONT = "front";
    private static final String BACK = "back";
    private static final String SIZE = "size";
    private static final String CLEAR = "clear";
    private static final String EXIT = "exit";
    private static final String OK = "ok";
    private static final String ERROR = "error";
    private static final String BYE = "bye";

    private TaskF() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        try {
            while (true) {
                switch (in.next()) {
                    case PUSH_FRONT -> {
                        deque.pushFront(in.nextInt());
                        out.println(OK);
                    }
                    case PUSH_BACK -> {
                        deque.pushBack(in.nextInt());
                        out.println(OK);
                    }
                    case POP_FRONT -> {
                        if (deque.isEmpty()) {
                            out.println(ERROR);
                        } else {
                            out.println(deque.popFront());
                        }
                    }
                    case POP_BACK -> {
                        if (deque.isEmpty()) {
                            out.println(ERROR);
                        } else {
                            out.println(deque.popBack());
                        }
                    }
                    case FRONT -> {
                        if (deque.isEmpty()) {
                            out.println(ERROR);
                        } else {
                            out.println(deque.front());
                        }
                    }
                    case BACK -> {
                        if (deque.isEmpty()) {
                            out.println(ERROR);
                        } else {
                            out.println(deque.back());
                        }
                    }
                    case SIZE -> out.println(deque.size());
                    case CLEAR -> {
                        deque.clear();
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

    static class Deque {
        private Node front, rear;
        private int size;

        private class Node {
            final private int number;
            private Node next;
            private Node previous;

            Node(int number, Node next, Node previous) {
                this.number = number;
                this.next = next;
                this.previous = previous;
            }

        }

        public Deque() {
            front = null;
            rear = null;
            size = 0;
        }

        boolean isEmpty() {
            return front == null || rear == null;
        }

        public void pushFront(int n) {
            Node newNode = new Node(n, null, null);
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                newNode.next = front;
                front.previous = newNode;
                front = newNode;
            }
            size++;
        }

        public void pushBack(int n) {
            Node newNode = new Node(n, null, null);
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                newNode.previous = rear;
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        public int popFront() {
            int num = front.number;
            front = front.next;
            size--;
            if (isEmpty()) {
                rear = null;
            } else {
                front.previous = null;
            }
            return num;
        }

        public int popBack() {
            int num = rear.number;
            rear = rear.previous;
            size--;
            if (isEmpty()) {
                front = null;
            } else {
                rear.next = null;
            }
            return num;
        }

        public int front() {
            return front.number;
        }
        public int back() {
            return rear.number;
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