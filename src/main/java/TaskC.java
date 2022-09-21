import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskC {
    private TaskC() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        String input;
        do {
            input = in.next();
            switch (input) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    if (stack.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(stack.pop());
                    break;
                case "back":
                    if (stack.isEmpty()) {
                        out.println("error");
                        break;
                    }
                    out.println(stack.back());
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
            }
        } while (!"exit".equals(input));
        out.println("bye");
    }

    private static class Stack {
        private static final int MIN_CAPACITY = 16;
        private int[] elements;
        private static int size;
        private static int top;

        public Stack() {
            elements = new int[MIN_CAPACITY];
            size = 0;
            top = -1;
        }

        public void push(int number) {
            if (top == elements.length - 1) {
                increaseCapacity();
            }
            elements[++top] = number;
            size++;
        }

        public int pop() {
            size--;
            return elements[top--];
        }

        public int back() {
            return elements[top];
        }

        public int size() {
            return size;
        }

        public void clear() {
            top = -1;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void increaseCapacity() {
            elements = Arrays.copyOf(elements, elements.length * 3 / 2);
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
