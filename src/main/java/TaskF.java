import java.io.*;
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

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        MyDeque deque = new MyDeque();
        String command = in.next();

        while (!command.equals("exit")) {
            switch (command) {
                case "push_front":
                    deque.pushFront(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.pushBack(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    Integer upper = deque.popFront();
                    out.println((upper == null) ? "error" : upper);
                    break;
                case "pop_back":
                    Integer lower = deque.popBack();
                    out.println((lower == null) ? "error" : lower);
                    break;
                case "front":
                    out.println((deque.front() == null) ? "error" : deque.front());
                    break;
                case "back":
                    out.println((deque.back() == null) ? "error" : deque.back());
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "clear":
                    deque.clear();
                    out.println("ok");
                    break;
            }
            command = in.next();
        }
        out.println("bye");
    }


    private static class MyDeque {
        private static class Node {
            private int data;
            private Node prev;
            private Node next;

            public void setPrev(Node prev) {
                this.prev = prev;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            public Node(int data) {
                prev = null;
                next = null;
                this.data = data;
            }

            public Node getPrev() {
                return prev;
            }


            public int getData() {
                return data;
            }

        }

        private Node tail;
        private Node head;
        private int size;

        public MyDeque() {
            tail = null;
            head = null;
            size = 0;
        }


        public void pushFront(int item) {
            if (head == null) {
                head = new Node(item);
                tail = head;
            } else {
                Node newNode = new Node(item);
                head.setPrev(newNode);
                newNode.setNext(head);
                head = newNode;
            }
            size++;
        }

        public void pushBack(int item) {
            if (tail == null) {
                head = new Node(item);
                tail = head;
            } else {
                Node newNode = new Node(item);
                tail.setNext(newNode);
                newNode.setPrev(tail);
                tail = newNode;
            }
            size++;
        }

        public Integer popFront() {
            Integer popFront;
            if (head == null) {
                popFront = null;
            } else {
                size--;
                popFront = head.getData();
                if (head.getNext() != null) {
                    head = head.getNext();
                    head.setPrev(null);
                } else {
                    clear();
                }
            }

            return popFront;
        }

        public Integer popBack() {
            Integer popBack;
            if (tail == null) {
                popBack = null;
            } else {
                size--;
                popBack = tail.getData();
                if (tail.getPrev() != null) {
                    tail = tail.getPrev();
                    tail.setNext(null);
                } else {
                    clear();
                }
            }
            return popBack;
        }

        public int size() {
            return size;
        }

        public Integer back() {
            return (tail == null) ? null : tail.getData();
        }

        public Integer front() {
            return (head == null) ? null : head.getData();
        }


        public void clear() {
            tail = null;
            head = null;
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