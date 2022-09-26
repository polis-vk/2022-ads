import java.io.*;
import java.util.StringTokenizer;

public class CorrectBracketSequence {

    static Node head;
    static int size = 0;

    private static class Node {
        private Node next;
        private char value;

        public Node(char value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private static void push(char value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    private static char pop() {
        if (size == 0) {
            return 'e';
        }
        char res = head.value;
        head = head.next;
        size--;
        return res;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String sequence = in.next();
        if (sequence.isEmpty()) {
            System.out.println("yes");
            return;
        }
        for (int i = 0; i < sequence.length(); i++) {
            switch (sequence.charAt(i)) {
                case '(' -> {
                    push('(');
                }
                case '{' -> {
                    push('{');
                }
                case '[' -> {
                    push('[');
                }
                case ')' -> {
                    if (pop() != '(') {
                        System.out.println("no");
                        return;
                    }
                }
                case '}' -> {
                    if (pop() != '{') {
                        System.out.println("no");
                        return;
                    }
                }
                case ']' -> {
                    if (pop() != '[') {
                        System.out.println("no");
                        return;
                    }
                }
            }
        }
        if (size == 0) {
            System.out.println("yes");
            return;
        }
        System.out.println("no");
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
