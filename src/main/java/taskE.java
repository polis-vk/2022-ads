import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class taskE {

    private static void solve(final FastScanner in, final PrintWriter out) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StackIml stack = new StackIml();

        for (int i = 0; i < input.length(); i++) {

            char value = input.charAt(i);
            String value2 = String.valueOf(value);
            if (value >= '0' && value <= '9') {
                stack.push(value - '0');

            } else if (isOperator(value2)) {

                int secondValue = stack.pop();
                int firstValue = stack.pop();
                switch (value) {
                    case '+':
                        stack.push(firstValue + secondValue);
                        break;
                    case '-':
                        stack.push(firstValue - secondValue);
                        break;
                    case '*':
                        stack.push(firstValue * secondValue);
                        break;
                }
            }
        }
        out.println(stack.pop());
    }



    public static boolean isOperator(String value) {
        return value.equalsIgnoreCase("+") ||
                value.equalsIgnoreCase("*") ||
                value.equalsIgnoreCase("-");
    }




    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception e) {
            e.printStackTrace();
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
    private static class StackIml{
        StackIml.Node head;
        StackIml.Node tail;

        private int size;

        StackIml() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        private static class Node {
            int value;

            StackIml.Node prev;

            Node(int value) {
                this.value = value;
                this.prev = null;
            }

        }


        public void push(int value) {
            size++;
            StackIml.Node node = new StackIml.Node(value);
            if (head == null) {
                head = node;
                tail = head;
                return;
            }
            node.prev = tail;
            tail = node;
        }

        public int pop() {
            int value = tail.value;
            tail = tail.prev;
            if (head == null) {
                tail = null;
            }
            size--;
            return value;
        }

        public int back() {
            return tail.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            head = null;
            tail = null;
            size = 0;
        }

    }

}













