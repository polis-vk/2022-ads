import java.io.*;
import java.util.StringTokenizer;


public class ProtectDeque {
    static Node head;
    static Node tail;
    static int size = 0;

    private static class Node {
        private Node next;
        private Node prev;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private static void push_front(int value) {
        Node node = new Node(value, head, null);
        if (size == 0) {
            tail = node;
        } else {
            head.prev = node;
        }
        head = node;
        size++;
    }

    private static void push_back(int value) {
        Node node = new Node(value, null, tail);
        if (size == 0) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    private static int pop_front() {
        int res = head.value;
        size--;
        if (size == 0) {
            clear();
            return res;
        }
        head = head.next;
        return res;
    }

    private static int pop_back() {
        int res = tail.value;
        size--;
        if (size == 0) {
            clear();
            return res;
        }
        tail = tail.prev;
        return res;
    }

    private static int front() {
        return head.value;
    }

    private static int back() {
        return tail.value;
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
                case "push_front" -> {
                    push_front(in.nextInt());
                    System.out.println("ok");
                }
                case "front" -> {
                    if (size == 0) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(front());
                }
                case "pop_front" -> {
                    if (size == 0) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(pop_front());
                }
                case "push_back" -> {
                    push_back(in.nextInt());
                    System.out.println("ok");
                }
                case "pop_back" -> {
                    if (size == 0) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(pop_back());
                }
                case "size" -> System.out.println(size());
                case "back" -> {
                    if (size == 0) {
                        System.out.println("error");
                        break;
                    }
                    System.out.println(back());
                }
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

