import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

final class TaskF {
    private static final String PUSH_FRONT_COMMAND = "push_front";
    private static final String PUSH_BACK_COMMAND = "push_back";
    private static final String POP_FRONT_COMMAND = "pop_front";
    private static final String POP_BACK_COMMAND = "pop_back";
    private static final String FRONT_COMMAND = "front";
    private static final String BACK_COMMAND = "back";
    private static final String SIZE_COMMAND = "size";
    private static final String CLEAR_COMMAND = "clear";
    private static final String EXIT_COMMAND = "exit";
    private static final String OK_MSG = "ok";
    private static final String ERROR_MSG = "error";
    private static final String EXIT_MSG = "bye";

    private TaskF() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque myDeque = new Deque();
        String input = in.next();
        while (!input.equals(EXIT_COMMAND)) {
            switch (input){
                case PUSH_FRONT_COMMAND:
                    myDeque.push_front(in.nextInt());
                    out.println(OK_MSG);
                    break;
                case PUSH_BACK_COMMAND:
                    myDeque.push_back(in.nextInt());
                    out.println(OK_MSG);
                    break;
                case POP_FRONT_COMMAND:
                    if (myDeque.isEmpty()) {
                        out.println(ERROR_MSG);
                        break;
                    }
                    out.println(myDeque.pop_front());
                    break;
                case POP_BACK_COMMAND:
                    if (myDeque.isEmpty()) {
                        out.println(ERROR_MSG);
                        break;
                    }
                    out.println(myDeque.pop_back());
                    break;
                case BACK_COMMAND:
                    if (myDeque.isEmpty()) {
                        out.println(ERROR_MSG);
                        break;
                    }
                    out.println(myDeque.back());
                    break;
                case FRONT_COMMAND:
                    if (myDeque.isEmpty()) {
                        out.println(ERROR_MSG);
                        break;
                    }
                    out.println(myDeque.front());
                    break;
                case SIZE_COMMAND:
                    out.println(myDeque.size());
                    break;
                case CLEAR_COMMAND:
                    myDeque.clear();
                    out.println(OK_MSG);
                    break;
            }
            input = in.next();
        }
        out.println(EXIT_MSG);
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

    private static class Deque {
        private static class Node {
            public Node next;
            public Node prev;
            public int value;

            public Node(int value) {
                next = null;
                prev = null;
                this.value = value;
            }
        }

        private Node head;
        private Node tail;
        private int size;

        public Deque(){
            head = tail = null;
            size = 0;
        }

        public void push_back(int value) {
            if (head == null){
                head = tail = new Node(value);
            }
            else {
                tail.next = new Node(value);
                tail.next.prev = tail;
                tail = tail.next;
            }
            size++;
        }

        public void push_front(int value) {
            if (head == null){
                head = tail = new Node(value);
            }
            else {
                head.prev = new Node(value);
                head.prev.next = head;
                head = head.prev;
            }
            size++;
        }

        public int pop_front() {
            int firstValue = front();
            if (head == tail) {
                clear();
            }
            else {
                head = head.next;
                head.prev = null;
                size--;
            }
            return firstValue;
        }

        public int pop_back() {
            int lastValue = back();
            if (head == tail) {
                clear();
            }
            else {
                tail = tail.prev;
                tail.next = null;
                size--;
            }
            return lastValue;
        }

        public int front() {
            return head.value;
        }

        public int back() {
            return tail.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
