import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskB {
    private TaskB() {
        // Should not be instantiated
    }

    // My queue class
    private static class MyQueue {

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
        private Node tail;

        // Constructor
        public MyQueue() {
            size = 0;
            head = null;
            tail = null;
        }

        // Add element to queue
        public void push(int elem) {
            Node newNode = new Node(elem);

            if(size == 0) {
                head = tail = newNode;
            }
            else {
                tail.next = newNode;
                tail = tail.next;
            }
            size++;
        }

        // Get and remove first element of the queue
        public int pop() {
            if(size == 0) {
                throw new RuntimeException("Queue is empty");
            }
            int res = head.data;
            head = head.next;
            size--;
            return res;
        }

        // Get first element of the queue
        public int front() {
            if(size == 0) {
                throw new RuntimeException("Queue is empty");
            }
            return head.data;
        }

        // Get size of queue
        public int size() {
            return size;
        }

        // Delete all elements of queue
        public void clear() {
            head = tail = null;
            size = 0;
        }

    }

    // Constants
    public static final String PUSH_MSG = "push";
    public static final String POP_MSG = "pop";
    public static final String FRONT_MSG = "front";
    public static final String SIZE_MSG = "size";
    public static final String CLEAR_MSG = "clear";
    public static final String EXIT_MSG = "exit";
    public static final String OK_MSG = "ok";
    public static final String ERROR_MSG = "error";
    public static final String BYE_MSG = "bye";

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyQueue queue = new MyQueue();
        while(true){
            String command = in.next();
            switch (command){
                case PUSH_MSG:
                    queue.push(in.nextInt());
                    out.println(OK_MSG);
                    break;
                case POP_MSG:
                    try {
                        out.println(queue.pop());
                    }
                    catch (RuntimeException ex)
                    {
                        out.println(ERROR_MSG);
                    }
                    break;
                case FRONT_MSG:
                    try {
                        out.println(queue.front());
                    }
                    catch (RuntimeException ex) {
                        out.println(ERROR_MSG);
                    }
                    break;
                case SIZE_MSG:
                    out.println(queue.size());
                    break;
                case CLEAR_MSG:
                    queue.clear();
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
