import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class TaskB {
    private static final String PUSH_COMMAND = "push";
    private static final String POP_COMMAND = "pop";
    private static final String FRONT_COMMAND = "front";
    private static final String SIZE_COMMAND = "size";
    private static final String CLEAR_COMMAND = "clear";
    private static final String EXIT_COMMAND = "exit";
    private static final String OK_MSG = "ok";
    private static final String ERROR_MSG = "error";
    private static final String EXIT_MSG = "bye";

    private TaskB() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue myQueue = new Queue();
        String input = in.next();
        while (!input.equals(EXIT_COMMAND)) {
            switch (input){
                case PUSH_COMMAND:
                    myQueue.push(in.nextInt());
                    out.println(OK_MSG);
                    break;
                case POP_COMMAND:
                    if (myQueue.isEmpty()) {
                        out.println(ERROR_MSG);
                        break;
                    }
                    out.println(myQueue.pop());
                    break;
                case FRONT_COMMAND:
                    if (myQueue.isEmpty()) {
                        out.println(ERROR_MSG);
                        break;
                    }
                    out.println(myQueue.front());
                    break;
                case SIZE_COMMAND:
                    out.println(myQueue.size());
                    break;
                case CLEAR_COMMAND:
                    myQueue.clear();
                    out.println(OK_MSG);
                    break;
            }
            input = in.next();
        }
        out.println(EXIT_MSG);
    }

    private static class Queue {
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

        public Queue(){
            head = tail = null;
            size = 0;
        }

        public void push(int value) {
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

        public int pop() {
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

        public int front() {
            return head.value;
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
