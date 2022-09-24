import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        for (int i = 0; i < str.length(); i++) {
            char element = str.charAt(i);
            if (element == ' ')
                continue;
            if (isNumber(element)) {
                Stack.push(Integer.parseInt(String.valueOf(element)));
            } else {
                Stack.push(applyOperation(element, Stack.pop(), Stack.pop()));
            }
        }
        out.println(Stack.back());
    }

    private static int applyOperation(char operation, int left, int right) {
        switch (operation) {
            case '+':
                return left + right;
            case '-':
                return right - left;
            case '*':
                return left * right;
            default:
                return 0;
        }
    }

    private static boolean isNumber(char element) {
        return element >= '0' && element <= '9';
    }

    private static class Stack {
        private static Node last;

        public static void push(int value) {
            Node node = new Node(value);
            if (last != null) {
                node.prev = last;
            }
            last = node;
        }

        public static int pop() {
            int value = last.value;
            last = last.prev;
            return value;
        }

        public static int back() {
            return last.value;
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
