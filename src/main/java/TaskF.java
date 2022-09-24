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

    private TaskF() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        while (true) {
            String command = in.next();
            try {
                switch (command) {
                    case PUSH_FRONT -> {
                        deque.pushFront(in.nextInt());
                        out.println("ok");
                    }
                    case PUSH_BACK -> {
                        deque.pushBack(in.nextInt());
                        out.println("ok");
                    }
                    case POP_FRONT -> out.println(deque.popFront());
                    case POP_BACK -> out.println(deque.popBack());
                    case FRONT -> out.println(deque.front());
                    case BACK -> out.println(deque.back());
                    case SIZE -> out.println(deque.size());
                    case CLEAR -> {
                        deque.clear();
                        out.println("ok");
                    }
                    case EXIT -> {
                        out.println("bye");
                        return;
                    }
                }
            } catch (DequeException e) {
                out.println("error");
            }
        }
    }

    public static class DequeException extends Exception {
        public DequeException() {
            super();
        }

        public DequeException(String message) {
            super(message);
        }

        public DequeException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class Deque {
        private int size;
        private Node head;
        private Node tail;

        private static class Node {
            int value;
            Node prev;
            Node next;

            public Node(int value) {
                this.value = value;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            public void setPrev(Node prev) {
                this.prev = prev;
            }
        }

        public void pushFront(int value) {
            size++;
            if (size == 1) {
                head = new Node(value);
                tail = head;
                return;
            }
            head.setPrev(new Node(value));
            Node newHead = head.prev;
            newHead.setNext(head);
            head = newHead;
        }

        public void pushBack(int value) {
            size++;
            if (size == 1) {
                head = new Node(value);
                tail = head;
            } else {
                tail.setNext(new Node(value));
                Node newTail = tail.next;
                newTail.setPrev(tail);
                tail = newTail;
            }
        }

        public int popBack() throws DequeException {
            if (size == 0) {
                throw new DequeException("Deque is empty");
            }
            int value = tail.value;
            if (size > 1) {
                tail = tail.prev;
                tail.next = null;
            } else {
                tail = null;
            }
            size--;
            return value;
        }

        public int popFront() throws DequeException {
            if (size == 0) {
                throw new DequeException("Deque is empty");
            }
            int value = head.value;
            if (size > 1) {
                head = head.next;
                head.prev = null;
            } else {
                head = null;
            }
            size--;
            return value;
        }

        public int front() throws DequeException {
            if (size == 0) {
                throw new DequeException("Deque is empty");
            }
            return head.value;
        }

        public int back() throws DequeException {
            if (size == 0) {
                throw new DequeException("Deque is empty");
            }
            return tail.value;
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
                currentNode.prev = null;
                currentNode = nextNode;
            }
            size = 0;
            head = null;
            tail = null;
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
