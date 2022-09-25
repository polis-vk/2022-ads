package company.vk.polis.ads;

import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

public class Queue {

    private static class Node {
        int value;
        Node next = null;

        public Node(int value) {
            this.value = value;
        }
    }

    private int size;

    private Node head;
    private Node tail;

    public Queue() {
        size = 0;
        head = null;
        tail = null;
    }

    public void push(int n) {
        Node node = new Node(n);
        size++;
        if (head == null) {
            head = node;
            tail = head;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public int pop() {
        int value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    public int front() {
        return head.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public void clear() {
        head = null;
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
        final Queue.FastScanner in = new Queue.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            String query;
            Queue queue = new Queue();
            do {
                query = in.next();
                switch (query) {
                    case "push":
                        int n = in.nextInt();
                        queue.push(n);
                        out.println("ok");

                        break;
                    case "pop":
                       if (queue.head == null) {
                           out.println("error");
                       } else {
                           out.println(queue.pop());
                       }
                        break;
                    case "front":
                        if (queue.head == null) {
                            out.println("error");
                        } else {
                            out.println(queue.front());
                        }
                        break;
                    case "size":
                        out.println(queue.size());
                        break;
                    case "clear":
                        queue.clear();
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
