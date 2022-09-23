import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    // My queue class
    private static class MyStack {

        // Inner node class
        private class Node {
            // Data
            int data;
            Node next;

            // Constructor
            Node(int num) {
                data = num;
            }
        }

        // data
        private int size;
        private Node head;

        // Constructor
        public MyStack() {
            size = 0;
            head = null;
        }

        // Add element to stack
        public void push(int elem) {
            Node newNode = new Node(elem);

            if(size == 0) {
                head = newNode;
            }
            else {
                newNode.next = head;
                head = newNode;
            }
            size++;
        }

        // Get and remove last element of the stack
        public int pop() {
            if(size == 0) {
                throw new RuntimeException("Stack is empty");
            }
            int res = head.data;
            head = head.next;
            size--;
            return res;
        }

        // Get last element of the stack
        public int back() {
            if(size == 0) {
                throw new RuntimeException("Stack is empty");
            }
            return head.data;
        }

        // Get size of stack
        public int size() {
            return size;
        }

        // Delete all elements of stack
        public void clear() {
            head = null;
            size = 0;
        }

    }

    // constants
    public static final String PUSH_MSG = "push";
    public static final String POP_MSG = "pop";
    public static final String BACK_MSG = "back";
    public static final String SIZE_MSG = "size";
    public static final String CLEAR_MSG = "clear";
    public static final String EXIT_MSG = "exit";
    public static final String OK_MSG = "ok";
    public static final String ERROR_MSG = "error";
    public static final String BYE_MSG = "bye";

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack stack = new MyStack();
        while(true) {
            String command = in.next();
            switch (command){
                case PUSH_MSG:
                    stack.push(in.nextInt());
                    out.println(OK_MSG);
                    break;
                case POP_MSG:
                    try {
                        out.println(stack.pop());
                    }
                    catch (RuntimeException ex) {
                        out.println(ERROR_MSG);
                    }
                    break;
                case BACK_MSG:
                    try {
                        out.println(stack.back());
                    }
                    catch (RuntimeException ex) {
                        out.println(ERROR_MSG);
                    }
                    break;
                case SIZE_MSG:
                    out.println(stack.size());
                    break;
                case CLEAR_MSG:
                    stack.clear();
                    out.println(OK_MSG);
                    break;
                case EXIT_MSG:
                    out.println(BYE_MSG);
                    return;
            }
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
