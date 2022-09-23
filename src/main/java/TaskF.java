import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskF {
    private TaskF() {
        // Should not be instantiated
    }

    // My queue class
    private static class MyDequeue {

        // Inner node class
        private class Node {
            // Data
            int data;
            Node next;
            Node prev;

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
        public MyDequeue() {
            size = 0;
            head = null;
            tail = null;
        }

        // Add element to begin of the dequeue
        public void pushFront(int elem) {
            Node newNode = new Node(elem);

            if(size == 0) {
                head = tail = newNode;
            }
            else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            size++;
        }

        // Add element to end of the dequeue
        public void pushBack(int elem) {
            Node newNode = new Node(elem);

            if(size == 0) {
                head = tail = newNode;
            }
            else {
                newNode.prev = tail;
                tail.next = newNode;
                tail = tail.next;
            }
            size++;
        }

        // Get and remove first element of the dequeue
        public int popFront() {
            if(size == 0) {
                throw new RuntimeException("Dequeue is empty");
            }
            int res = head.data;
            head = head.next;
            size--;
            return res;
        }

        // Get and remove last element of the dequeue
        public int popBack() {
            if(size == 0) {
                throw new RuntimeException("Dequeue is empty");
            }
            int res = tail.data;
            tail = tail.prev;
            size--;
            return res;
        }

        // Get first element of the dequeue
        public int front() {
            if(size == 0) {
                throw new RuntimeException("Dequeue is empty");
            }
            return head.data;
        }

        // Get last element of the dequeue
        public int back() {
            if(size == 0) {
                throw new RuntimeException("Dequeue is empty");
            }
            return tail.data;
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

    // constants
    public static final String PUSHFRONT_MSG = "push_front";
    public static final String PUSHBACK_MSG = "push_back";
    public static final String POPFRONT_MSG = "pop_front";
    public static final String POPBACK_MSG = "pop_back";
    public static final String FRONT_MSG = "front";
    public static final String BACK_MSG = "back";
    public static final String SIZE_MSG = "size";
    public static final String CLEAR_MSG = "clear";
    public static final String EXIT_MSG = "exit";
    public static final String OK_MSG = "ok";
    public static final String ERROR_MSG = "error";
    public static final String BYE_MSG = "bye";

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyDequeue dequeue = new MyDequeue();
        while(true){
            String command = in.next();
            switch (command){
                case PUSHFRONT_MSG:
                    dequeue.pushFront(in.nextInt());
                    out.println(OK_MSG);
                    break;
                case PUSHBACK_MSG:
                    dequeue.pushBack(in.nextInt());
                    out.println(OK_MSG);
                    break;
                case POPFRONT_MSG:
                    try {
                        out.println(dequeue.popFront());
                    }
                    catch (RuntimeException ex) {
                        out.println(ERROR_MSG);
                    }
                    break;
                case POPBACK_MSG:
                    try {
                        out.println(dequeue.popBack());
                    }
                    catch (RuntimeException ex) {
                        out.println(ERROR_MSG);
                    }
                    break;
                case FRONT_MSG:
                    try {
                        out.println(dequeue.front());
                    }
                    catch (RuntimeException ex) {
                        out.println(ERROR_MSG);
                    }
                    break;
                case BACK_MSG:
                    try {
                        out.println(dequeue.back());
                    }
                    catch (RuntimeException ex) {
                        out.println(ERROR_MSG);
                    }
                    break;
                case SIZE_MSG:
                    out.println(dequeue.size());
                    break;
                case CLEAR_MSG:
                    dequeue.clear();
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
