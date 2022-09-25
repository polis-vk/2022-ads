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
public final class DequeRealization {
    private DequeRealization() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        String command;
        do {
            command = in.next();
            switch (command) {
                case "push_front":
                    deque.push_front(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.push_back(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    if (deque.head != null) {
                        out.println(deque.pop_front());
                    } else {
                        out.println("error");
                    }
                    break;
                case "pop_back":
                    if (deque.tail != null) {
                        out.println(deque.pop_back());
                    } else {
                        out.println("error");
                    }
                    break;
                case "front":
                    if (deque.head != null) {
                        out.println(deque.front());
                    } else {
                        out.println("error");
                    }
                    break;
                case "back":
                    if (deque.tail != null) {
                        out.println(deque.back());
                    } else {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "clear":
                    deque.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    break;
            }
        } while (!command.equals("exit"));
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

    public static class Deque {

        public Node head;
        public Node tail;

        public Deque() {
            head = null;
            tail = null;
        }

        public class Node {
            public int data;
            public Node prev;
            public Node next;

            public Node(int data) {
                this.data = data;
                prev = null;
                next = null;
            }
        }

        public void push_front(int data) {
            Node newNode = new Node(data);
            if (head != null) {
                head.prev = newNode;
                newNode.next = head;
            } else {
                tail = newNode;
            }
            head = newNode;
        }

        public void push_back(int data) {
            Node newNode = new Node(data);
            if (tail != null) {
                newNode.prev = tail;
                tail.next = newNode;
            } else {
                head = newNode;
            }
            tail = newNode;
        }

        public int pop_front() {
            if (head != null) {
                int result = head.data;
                head = head.next;
                if (head == null) {
                    tail = null;
                } else {
                    head.prev = null;
                }
                return result;
            } else {
                tail = null;
                throw new NullPointerException("error");
            }
        }

        public int pop_back() {
            if (tail != null) {
                int result = tail.data;
                tail = tail.prev;
                if (tail == null) {
                    head = null;
                } else {
                    tail.next = null;
                }
                return result;
            } else {
                throw new NullPointerException("error");
            }
        }

        public int front() {
            if (head != null) {
                return head.data;
            } else {
                throw new NullPointerException("error");
            }
        }

        public int back() {
            if (tail != null) {
                return tail.data;
            } else {
                throw new NullPointerException("error");
            }
        }

        public int size() {
            int counter = 0;
            Node currentNode = head;
            while (currentNode != null) {
                counter++;
                currentNode = currentNode.next;
            }
            return counter;
        }

        public void clear() {
            tail = null;
            while (head != null) {
                head = head.prev;
            }
        }
    }
}