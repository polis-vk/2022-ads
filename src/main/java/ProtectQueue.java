import java.io.*;
import java.util.StringTokenizer;

public class ProtectQueue {
    static Node head;
    static Node tail;
    static int size = 0;

    private static class Node {
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private static void push(int value) {
        Node node = new Node(value);
        if (size == 0) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    private static int pop() {
        int res = head.value;
        head = head.next;
        size--;
        return res;
    }

    private static int front() {
        return head.value;
    }

    private static int size() {
        return size;
    }

    private static void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String buf = in.next();
        while (!buf.equals("exit")) {
            switch (buf) {
                case "push" -> {
                    push(in.nextInt());
                    System.out.println("ok");
                }
                case "front" -> {
                    if (size == 0) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(front());
                }
                case "pop" -> {
                    if (size == 0) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(pop());
                }
                case "size" -> System.out.println(size());
                case "clear" -> {
                    clear();
                    System.out.println("ok");
                }
            }
            buf = in.next();
        }
        System.out.println("bye");
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

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
