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
public final class QueueRealization {
    private QueueRealization() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Queue queue = new Queue();
        String command;
        do {
            command = new String(in.next());
            switch (command) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (queue.head != null) {
                        out.println(queue.pop());
                    } else {
                        out.println("error");
                    }
                    break;
                case "front":
                    if (queue.head != null) {
                        out.println(queue.front());
                    } else {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
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

    public static class Queue {

        public Node head;

        public Queue() {
            head = null;
        }

        public class Node {
            public int data;
            public Node next;

            public Node(int data) {
                this.data = data;
                next = null;
            }

        }

        public void push(int data) {
            Node newNode = new Node(data);
            Node currentNode = head;

            if (head != null) {
                while (currentNode.next != null) {
                    currentNode = currentNode.next;
                }
                currentNode.next = newNode;
            } else {
                head = newNode;
            }
        }

        public int pop() {
            if (head != null) {
                int result = head.data;
                head = head.next;
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
            while (head != null) {
                head = head.next;
            }
        }
    }
}