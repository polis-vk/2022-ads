import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class Main {
    private Main() {
        // Should not be instantiated
    }

    static class QueueException extends Exception {
    }

    public static class Queue {
        private Node head;
        private Node tail;
        private int size;

        private static class Node {
            int value;
            Node next;

            public Node(int value) {
                this.value = value;
            }
        }

        void push(int n) {
            if (size == 0) {
                tail = new Node(n);
                head = tail;
            } else {
                tail.next = new Node(n);
                tail = tail.next;
            }
            size++;
        }

        int pop() throws QueueException {
            if (size == 0) {
                throw new QueueException();
            }

            int n = head.value;
            Node next = head.next;

            head.next = null;

            head = next;

            if (next == null) {
                tail = null;
            }

            size--;

            return n;
        }

        int front() throws QueueException {
            if (size == 0) {
                throw new QueueException();
            }
            return head.value;
        }

        int size() {
            return size;
        }

        void clear() {
            Node current = head;

            // Delete all the links of each node
            while (current != null) {
                Node next = current.next;
                current.next = null;
                current = next;
            }
            head = tail = null;
            size = 0;
        }
    }

    private static void solve(FastScanner in, PrintWriter out) {
        Queue queue = new Queue();

        String s;
        int n;

        while (true) {
            s = in.next();
            try {
                switch (s) {
                    case "push" -> {
                        n = in.nextInt();
                        queue.push(n);
                        out.println("ok");
                    }
                    case "pop" -> out.println(queue.pop());
                    case "front" -> out.println(queue.front());
                    case "size" -> out.println(queue.size());
                    case "clear" -> {
                        queue.clear();
                        out.println("ok");
                    }
                    case "exit" -> {
                        out.println("bye");
                        return;
                    }
                }
            } catch (QueueException e) {
                out.println("error");
            }
        }
    }

    private static class FastScanner {
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