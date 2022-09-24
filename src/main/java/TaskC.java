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
public final class TaskC {
    private static final String PUSH = "push";
    private static final String POP = "pop";
    private static final String BACK = "back";
    private static final String SIZE = "size";
    private static final String CLEAR = "clear";
    private static final String EXIT = "exit";

    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        while (true) {
            String command = in.next();
            try {
                switch (command) {
                    case PUSH -> {
                        stack.push(in.nextInt());
                        out.println("ok");
                    }
                    case POP -> out.println(stack.pop());
                    case BACK -> out.println(stack.back());
                    case SIZE -> out.println(stack.size());
                    case CLEAR -> {
                        stack.clear();
                        out.println("ok");
                    }
                    case EXIT -> {
                        out.println("bye");
                        return;
                    }
                }
            } catch (StackException e) {
                out.println("error");
            }
        }
    }

    public static class StackException extends Exception {
        public StackException() {
            super();
        }

        public StackException(String message) {
            super(message);
        }

        public StackException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class Stack {
        private int size;
        private Node head;

        private static class Node {
            Node next;
            int value;

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
                return;
            }
            Node newHead = new Node(value);
            newHead.setNext(head);
            head = newHead;
        }

        public int pop() throws StackException {
            if (head == null) {
                throw new StackException("Stack is empty");
            }
            int value = head.value;
            Node nextNode = head.next;
            head.next = null;
            head = nextNode;
            size--;
            return value;
        }

        public int back() throws StackException {
            if (size == 0) {
                throw new StackException("Stack is empty");
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
            size = 0;
            head = null;
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