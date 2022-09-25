import java.io.*;
import java.util.Stack;
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
        // Write me
        Stack stack = new Stack();
        String command = in.next();

        while (!command.equals("exit")) {
            switch (command) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    Integer upper = stack.pop();
                    out.println((upper == null) ? "error" : upper);
                    break;
                case "back":
                    Integer upper1 = stack.peek();
                    out.println((upper1 == null) ? "error" : stack.peek());
                    break;
                case "size":
                    out.println(stack.size());
                    break;
                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
            }
            command = in.next();
        }
        out.println("bye");
    }


    private static class Stack {
        private int stack[];
        private int index;

        public Stack() {
            stack = new int[16];
            index = -1;
        }

        public void clear() {
            stack = new int[16];
            index = -1;
        }

        public void push(int item) {
            if (index == stack.length - 1) {
                int temp[] = new int[stack.length * 2];
                for (int i = 0; i < stack.length; i++) {
                    temp[i] = stack[i];
                }
                temp[++index] = item;
                stack = temp;
            } else {
                stack[++index] = item;
            }
        }

        public Integer pop() {
            return (index < 0) ? null : stack[index--];
        }

        public int size() {
            return (index + 1);
        }

        public Integer peek() {
            return (index == -1) ? null : stack[index];
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