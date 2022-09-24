import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static class MyStack<T> {
        ArrayList<T> stack;

        public MyStack() {
            stack = new ArrayList<>();
        }

        public void push(T x) {
            stack.add(x);
        }

        public T pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException();
            }
            return stack.remove(stack.size() - 1);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        MyStack<Integer> stack = new MyStack<>();
        String[] line = in.reader.readLine().split(" ").clone();
        for (String s : line) {
            if (s.matches("^[0-9].*")) {
                stack.push(Integer.parseInt(s));
            } else {
                int valueTwo = stack.pop();
                int valueOne = stack.pop();
                int result = 0;
                switch (s) {
                    case "*":
                        result = valueOne * valueTwo;
                        break;
                    case "/":
                        if (valueTwo == 0) {
                            throw new ArithmeticException("Can't be divided by 0.");
                        }
                        result = valueOne / valueTwo;
                        break;
                    case "+":
                        result = valueOne + valueTwo;
                        break;
                    case "-":
                        result = valueOne - valueTwo;
                        break;
                    case "^":
                        result = (int) Math.pow(valueOne, valueTwo);
                        break;
                    default:
                        break;
                }
                stack.push(result);
            }
        }
        out.println(stack.pop());
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
