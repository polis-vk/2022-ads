import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW1Task4 {
    private HW1Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        Stack stack = new Stack();
        for (int i = 0; i < input.length(); i++) {
            char brace = input.charAt(i);
            if (isOpening(brace)) {
                stack.push(brace);
            } else {
                if (stack.size() == 0) {
                    out.println("no");
                    return;
                }
                if (stack.pop() != getOpening(brace)) {
                    out.println("no");
                    return;
                }
            }
        }
        if (stack.size() == 0) {
            out.println("yes");
        } else {
            out.println("no");
        }
    }

    public static boolean isOpening(char brace) {
        return (brace == '(' || brace == '[' || brace == '{');
    }

    public static char getOpening(char closingBrace) {
        switch (closingBrace) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
        }
        return '0';
    }

    private static class Node {
        private final char value;
        private Node prev;

        public Node(char value) {
            this.value = value;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private static class Stack {
        private int size = 0;
        private Node last;

        public void push(char n) {
            if (last == null) {
                last = new Node(n);
                size++;
                return;
            }
            Node node = new Node(n);
            node.setPrev(last);
            last = node;
            size++;
        }

        public char pop() {
            char value = last.value;
            last = last.prev;
            size--;
            return value;
        }

        public int size() {
            return size;
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
