import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Part1Task2 {
    private Part1Task2() {
        // Should not be instantiated
    }

    private static final String COMMAND_PUSH = "push";
    private static final String COMMAND_POP = "pop";
    private static final String COMMAND_FRONT = "front";
    private static final String COMMAND_SIZE = "size";
    private static final String COMMAND_CLEAR = "clear";
    private static final String COMMAND_EXIT = "exit";
    private static final String MESSAGE_OK = "ok";
    private static final String MESSAGE_ERROR = "error";
    private static final String MESSAGE_BYE = "bye";

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyLinkedList list = new MyLinkedList();
        boolean isContinue = true;

        while (isContinue) {
            String command = in.next();
            int element = 0;

            switch (command) {
                case COMMAND_PUSH:
                    element = in.nextInt();
                    list.add(element);
                    out.println(MESSAGE_OK);
                    break;
                case COMMAND_POP:
                    if (list.isEmpty()) {
                        out.println(MESSAGE_ERROR);
                    } else {
                        out.println(list.poll());
                    }
                    break;
                case COMMAND_FRONT:
                    if (list.isEmpty()) {
                        out.println(MESSAGE_ERROR);
                    } else {
                        out.println(list.peek());
                    }
                    break;
                case COMMAND_SIZE:
                    out.println(list.size());
                    break;
                case COMMAND_CLEAR:
                    list.clear();
                    out.println(MESSAGE_OK);
                    break;
                case COMMAND_EXIT:
                    out.println(MESSAGE_BYE);
                    isContinue = false;
                    break;
                default:
                    System.exit(-1);
                    break;
            }
        }
    }

    private static class MyLinkedList {

        private static class Node {

            int data;
            Node prev;
            Node next;

            public Node(int data, Node prev, Node next) {
                this.data = data;
                this.prev = prev;
                this.next = next;
            }
        }

        private int size = 0;
        private Node head = null;
        private Node tail = null;

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addFirst(int data) {
            if (size == 0) {
                Node node = new Node(data, null, null);
                head = node;
                tail = node;
            } else {
                Node node = new Node(data, null, head);
                head.prev = node;
                head = node;
            }
            size++;
        }

        public void addLast(int data) {
            if (size == 0) {
                Node node = new Node(data, null, null);
                head = node;
                tail = node;
            } else {
                Node node = new Node(data, tail, null);
                tail.next = node;
                tail = node;
            }
            size++;
        }

        public void add(int data) {
            addLast(data);
        }

        public int pollFirst() {
            int result = head.data;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            size--;
            return result;
        }

        public int pollLast() {
            int result = tail.data;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }
            size--;
            return result;
        }

        public int poll() {
            return pollFirst();
        }

        public int peekFirst() {
            return head.data;
        }

        public int peekLast() {
            return tail.data;
        }

        public int peek() {
            return peekFirst();
        }

        public void clear() {
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
