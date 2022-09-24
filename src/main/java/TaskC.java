import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String command;

        while (true) {
            command = in.next();
            switch (command) {
                case "push" -> {
                    Stack.push(in.nextInt());
                    out.println("ok");
                }
                case "pop" -> {
                    if (Stack.last == null) {
                        out.println("error");
                    } else {
                        out.println(Stack.pop());
                    }
                }
                case "back" -> {
                    if (Stack.last == null) {
                        out.println("error");
                    } else {
                        out.println(Stack.back());
                    }
                }
                case "size" -> {
                    out.println(Stack.size());
                }
                case "clear" -> {
                    Stack.clear();
                    out.println("ok");
                }
                case "exit" -> {
                    out.println("bye");
                    return;
                }
            }
        }
    }

    private static class Stack {
        private static Node last;
        private static int size;

        public static void push(int value) {
            size++;
            Node node = new Node(value);
            if (last != null) {
                node.prev = last;
            }
            last = node;
        }

        public static int pop() {
            size--;
            int value = last.value;
            last = last.prev;
            return value;
        }

        public static int back() {
            return last.value;
        }

        public static int size() {
            return size;
        }

        public static void clear() {
            last = null;
            size = 0;
        }

        private static class Node {
            private int value;
            private Node prev;

            public Node(int value) {
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
