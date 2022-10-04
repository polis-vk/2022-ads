package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    private static class MyStack {

        ArrayList<Integer> stack = new ArrayList<>();

        public void push(int x) {
            stack.add(x);
        }

        public int pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException();
            }
            int element = stack.get(stack.size() - 1);
            stack.remove(stack.get(stack.size() - 1));
            return element;
        }

        public int back() {
            if (stack.isEmpty()) {
                throw new RuntimeException();
            }
            return stack.get(stack.size() - 1);
        }

        public int size() {
            return stack.size();
        }

        public void clear() {
            stack.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyStack stack = new MyStack();
        while (true) {
            switch (in.next()) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(stack.pop());
                    } catch (RuntimeException exception) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(stack.back());
                    } catch (RuntimeException exception) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
                default:
                    System.exit(-1);
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
