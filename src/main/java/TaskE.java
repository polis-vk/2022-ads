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
        MyStack stack = new MyStack();

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
    private static class MyStack {
        private Integer stack[];
        private int index;

        public MyStack() {
            stack = new Integer[16];
            index = -1;
        }

        public void clear() {
            stack = new Integer[16];
            index = -1;
        }

        public void push(Integer item) {
            if (index == (stack.length - 1)) {
                Integer temp[] = new Integer[stack.length * 2];
                for (int i = 0; i < stack.length; i++) {
                    temp[i] = stack[i];
                }
                temp[++index] = item;
                stack = temp;
            } else {
                stack[++index] = item;
            }
        }

        public boolean isEmpty() {
            return (index == -1);
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