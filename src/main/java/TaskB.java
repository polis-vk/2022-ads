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
public final class TaskB {
    private static final String PUSH = "push";
    private static final String POP = "pop";
    private static final String FRONT = "front";
    private static final String SIZE = "size";
    private static final String CLEAR = "clear";
    private static final String EXIT = "exit";

    private TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        while (true) {
            String command = in.next();
            try {
                switch (command) {
                    case PUSH -> {
                        queue.push(in.nextInt());
                        out.println("ok");
                    }
                    case POP -> out.println(queue.pop());
                    case FRONT -> out.println(queue.front());
                    case SIZE -> out.println(queue.size());
                    case CLEAR -> {
                        queue.clear();
                        out.println("ok");
                    }
                    case EXIT -> {
                        out.println("bye");
                        return;
                    }
                }
            } catch (QueueException e) {
                out.println("error");
            }
        }
    }

    public static class QueueException extends Exception {
        public QueueException() {
            super();
        }

        public QueueException(String message) {
            super(message);
        }

        public QueueException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class Queue {
        private int size;
        private Node head;
        private Node tail;

        private static class Node {
            int value;
            Node next;

            public Node(int value) {
                this.value = value;
            }

            public void setNext(Node next) {
                this.next = next;
            }
        }

        public void push(int value) {
            size++;
            if (size == 1) {
                head = new Node(value);
                tail = head;
                return;
            }
            tail.setNext(new Node(value));
            tail = tail.next;
        }

        public int pop() throws QueueException {
            if (head == null) {
                throw new QueueException("Queue is empty");
            }
            int value = head.value;
            head = head.next;
            size--;
            return value;
        }

        public int front() throws QueueException {
            if (head == null) {
                throw new QueueException("Queue is empty");
            }
            return head.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            Node currentNode = head;
            Node nextNode;
            while (currentNode != null) {
                nextNode = currentNode.next;
                currentNode.next = null;
                currentNode = nextNode;
            }
            head = null;
            tail = null;
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