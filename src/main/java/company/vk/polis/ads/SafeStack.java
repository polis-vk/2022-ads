package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public final class SafeStack {
    private SafeStack() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        boolean flag = true;
        do {
            switch (in.next()) {
                case "push" -> {
                    out.println("ok");
                    stack.Push(in.nextInt());
                }
                case "pop" -> {
                    if (!(stack.IsEmpty())) {
                        out.println(stack.Pop());
                        break;
                    }
                    out.println("error");
                }
                case "back" -> {
                    if (!(stack.IsEmpty())) {
                        out.println(stack.Back());
                        break;
                    }
                    out.println("error");
                }
                case "size" -> out.println(stack.Size());
                case "clear" -> {
                    out.println("ok");
                    stack.Clear();
                }
                default -> {
                    out.println("bye");
                    flag = false;
                }
            }
        } while (flag);
    }

    private static class Stack {
        private static Node head;
        private static int size;

        private static class Node {
            public Node next;
            public int value;

            public Node(int value) {
                this.value = value;
            }

            public void setNext(Node next) {
                this.next = next;
            }
        }

        public void Push(int number) { // done
            Node node = new Node(number);
            if (head != null) {
                node.setNext(head);
            }
            head = node;
            size++;
        }

        public int Pop() { // done
            if (head.next == null) {
                int value = head.value;
                head = null;
                size--;
                return value;
            }
            size--;
            int value = head.value;
            head = head.next;
            return value;
        }

        public void Clear() { //done
            head = null;
            size = 0;
        }

        public int Back() { //done
            return head.value;
        }

        public int Size() { //done
            return size;
        }

        public boolean IsEmpty() { //done
            return size <= 0;
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