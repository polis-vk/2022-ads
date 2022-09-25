import java.io.*;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me

        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack();
        String line = scanner.nextLine();
        Integer operationResult = 0;

        for (String symbol : line.split(" ")) {
            switch (symbol) {
                case "+":
                    operationResult = stack.pop() + stack.pop();
                    stack.push(operationResult);
                    break;
                case "-":
                    operationResult = -stack.pop() + stack.pop();
                    stack.push(operationResult);
                    break;
                case "*":
                    operationResult = stack.pop() * stack.pop();
                    stack.push(operationResult);
                    break;
                default:
                    stack.push(Integer.parseInt(symbol));
                    break;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}