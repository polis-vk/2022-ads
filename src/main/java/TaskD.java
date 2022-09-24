import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.StringTokenizer;

public class TaskD {

    private TaskD() {
        // Should not be instantiated
    }

    static Map<Character, Character> brackets = Map.of('(', ')', '[', ']', '{', '}');

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str = in.next();
        for (int i = 0; i < str.length(); i++) {
            if (isOpened(str.charAt(i))) {
                Stack.push(str.charAt(i));
            } else {
                if (Stack.isEmpty() || str.charAt(i) != brackets.get(Stack.last.value)) {
                    out.println("no");
                    return;
                } else {
                    Stack.pop();
                }
            }
        }
        if (Stack.size() == 0) {
            out.println("yes");
        } else {
            out.println("no");
        }
    }

    private static boolean isOpened(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private static class Stack {
        private static Node last;
        private static int size;

        public static void push(char value) {
            size++;
            Node node = new Node(value);
            if (last != null) {
                node.prev = last;
            }
            last = node;
        }

        public static void pop() {
            size--;
            last = last.prev;
        }

        public static int size() {
            return size;
        }

        public static boolean isEmpty() {
            return size == 0;
        }

        private static class Node {
            private char value;
            private Node prev;

            public Node(char value) {
                this.value = value;
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
