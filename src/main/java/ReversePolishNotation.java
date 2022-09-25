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
public final class ReversePolishNotation {
    private ReversePolishNotation() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        out.println(solution(in.reader.readLine()));
    }

    public static int solution(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string, " ");
        Stack stack = new Stack();
        String plus = "+";
        String minus = "-";
        String multiply = "*";
        while (tokenizer.hasMoreTokens()) {
            String element = tokenizer.nextToken();
            if (plus.equals(element)) {
                int value2 = stack.pop();
                int value1 = stack.pop();
                stack.push(value1 + value2);
            } else if (minus.equals(element)) {
                int value2 = stack.pop();
                int value1 = stack.pop();
                stack.push(value1 - value2);
            } else if (multiply.equals(element)) {
                int value2 = stack.pop();
                int value1 = stack.pop();
                stack.push(value1 * value2);
            } else {
                stack.push(Integer.parseInt(element));
            }
        }
        return stack.pop();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Stack {

        public Node head;

        public Stack() {
            head = null;
        }

        public class Node {
            public int data;
            public Node prev;

            public Node(int data) {
                this.data = data;
                prev = null;
            }
        }

        public void push(int data) {
            Node newNode = new Node(data);
            if (head != null) {
                newNode.prev = head;
            }
            head = newNode;
        }

        public int pop() {
            if (head != null) {
                int result = head.data;
                head = head.prev;
                return result;
            } else {
                throw new NullPointerException("error");
            }
        }

        public int back() {
            if (head != null) {
                return head.data;
            } else {
                throw new NullPointerException("error");
            }
        }

        public int size() {
            int counter = 0;
            Node currentNode = head;
            while (currentNode != null) {
                counter++;
                currentNode = currentNode.prev;
            }
            return counter;
        }

        public void clear() {
            while (head != null) {
                head = head.prev;
            }
        }
    }
}