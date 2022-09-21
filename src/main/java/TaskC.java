import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

final class TaskC {
    private static final String PUSH_COMMAND = "push";
    private static final String POP_COMMAND = "pop";
    private static final String BACK_COMMAND = "back";
    private static final String SIZE_COMMAND = "size";
    private static final String CLEAR_COMMAND = "clear";
    private static final String EXIT_COMMAND = "exit";
    private static final String OK_MSG = "ok";
    private static final String ERROR_MSG = "error";
    private static final String EXIT_MSG = "bye";

    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack myStack = new Stack();
        String input = in.next();
        while (!input.equals(EXIT_COMMAND)) {
            switch (input){
                case PUSH_COMMAND:
                    myStack.push(in.nextInt());
                    out.println(OK_MSG);
                    break;
                case POP_COMMAND:
                    if (myStack.isEmpty()) {
                        out.println(ERROR_MSG);
                        break;
                    }
                    out.println(myStack.pop());
                    break;
                case BACK_COMMAND:
                    if (myStack.isEmpty()) {
                        out.println(ERROR_MSG);
                        break;
                    }
                    out.println(myStack.back());
                    break;
                case SIZE_COMMAND:
                    out.println(myStack.size());
                    break;
                case CLEAR_COMMAND:
                    myStack.clear();
                    out.println(OK_MSG);
                    break;
            }
            input = in.next();
        }
        out.println(EXIT_MSG);
    }

    private static class Stack{

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

        public Stack(){
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
