import java.io.*;
import java.util.StringTokenizer;

public class PostfixEntry {
    static Node head;
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
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    private static int pop() {
        if (size == 0) {
            return 'e';
        }
        int res = head.value;
        head = head.next;
        size--;
        return res;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String exp = in.nextLine();
        char prev = '_';
        char cur;
        int a;
        for (int i = 0; i < exp.length(); i++) {
            cur = exp.charAt(i);
            if (cur == ' ' && prev == ' ') {
                break;
            } else if (cur >= '0' && cur <= '9') {
                push(cur - 48);
            } else if (cur == '+') {
                push(pop() + pop());
            } else if (cur == '-') {
                a = pop();
                push(pop() - a);
            } else if (cur == '*') {
                push(pop() * pop());
            }
            prev = cur;
        }
        System.out.println(pop());
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

        String nextLine() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken("\n");
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
