import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class HW1Task5 {
    private HW1Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Stack stack = new Stack();
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (isNumber(symbol)) {
                stack.push(Integer.parseInt(String.valueOf(symbol)));
            } else if (isOperator(symbol)) {
                int secondOperand = stack.pop();
                int firstOperand = stack.pop();
                switch (symbol) {
                    case '+':
                        stack.push(firstOperand + secondOperand);
                        break;
                    case '-':
                        stack.push(firstOperand - secondOperand);
                        break;
                    case '*':
                        stack.push(firstOperand * secondOperand);
                        break;
                }
            }
        }
        out.println(stack.pop());
    }

    public static boolean isNumber(char c) {
        return (c >= '0' && c <= '9');
    }

    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*');
    }

    private static class Node {
        private final int value;
        private Node prev;

        public Node(int value) {
            this.value = value;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private static class Stack {
        private Node last;

        public void push(int n) {
            if (last == null) {
                last = new Node(n);
                return;
            }
            Node node = new Node(n);
            node.setPrev(last);
            last = node;
        }

        public int pop() {
            int value = last.value;
            last = last.prev;
            return value;
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
