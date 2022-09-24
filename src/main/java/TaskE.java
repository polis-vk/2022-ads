import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TaskE {

    private static void solve(final FastScanner in, final PrintWriter out){
        Stack stack = new Stack();
        String example = in.next().trim();

        for (int i = 0; i < example.length(); i += 2) {
            switch (example.charAt(i)) {
                case '+':
                    stack.push(stack.pop() + stack.pop());
                    break;
                case '-':
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case '*':
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(Integer.parseInt(String.valueOf(example.charAt(i))));
                    break;
            }
        }
        out.println(stack.pop());
    }

    private static class Stack {
        private Node head;
        private Node tail;
        private int size;

        private static class Node {
            int data;
            Node pNext;
            Node pPrev;

            public Node(int value) {
                data = value;
                pNext = null;
                pPrev = null;
            }
        }

        public Stack() {
            head = null;
            tail = null;
            size = 0;
        }

        public void push(int value) {
            if (head == null) {
                head = new Node(value);
                tail = head;
            } else {
                tail.pNext = new Node(value);
                tail.pNext.pPrev = tail;
                tail = tail.pNext;
            }
            size++;
        }

        public int back() {
            return tail.data;
        }

        public int pop() {
            int value = tail.data;
            tail = tail.pPrev;
            if (tail == null) {
                head = null;
            }
            size--;
            return value;
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
