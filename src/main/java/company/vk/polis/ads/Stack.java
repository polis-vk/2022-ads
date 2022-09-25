package company.vk.polis.ads;

import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

public class Stack {

    private static class Node {
        int value;
        Node prev = null;

        public Node(int value) {
            this.value = value;
        }
    }

    private int size;

    private Node tail;

    public Stack() {
        size = 0;
        tail = null;
    }

    public void push(int n) {
        Node node = new Node(n);
        size++;
        if (tail == null) {
            tail = node;
            return;
        }
        node.prev = tail;
        tail = node;
    }

    public int pop() {
        int value = tail.value;
        tail = tail.prev;
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
        tail = null;
        size = 0;
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


    public static void main(String[] args) {
        final Stack.FastScanner in = new Stack.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            String query;
            Stack stack = new Stack();
            do {
                query = in.next();
                switch (query) {
                    case "push":
                        int n = in.nextInt();
                        stack.push(n);
                        out.println("ok");

                        break;
                    case "pop":
                        if (stack.tail == null) {
                            out.println("error");
                        } else {
                            out.println(stack.pop());
                        }
                        break;
                    case "back":
                        if (stack.tail == null) {
                            out.println("error");
                        } else {
                            out.println(stack.back());
                        }
                        break;
                    case "size":
                        out.println(stack.size());
                        break;
                    case "clear":
                        stack.clear();
                        out.println("ok");
                        break;
                    case "exit":
                        out.println("bye");
                        break;

                }
            } while (!Objects.equals(query, "exit"));
        }

    }
}
