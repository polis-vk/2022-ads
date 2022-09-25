package company.vk.polis.ads;

import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

public class Deque {

    private static class Node {
        int value;
        Node prev = null;
        Node next = null;
        public Node(int value) {
            this.value = value;
        }
    }

    private int size;

    private Node tail;
    private Node head;

    public Deque() {
        size = 0;
        tail = null;
        head = null;
    }

    public void push_front(int n) {
        Node node = new Node(n);
        size++;
        if (head == null) {
            head = node;
            tail = head;
            return;
        }
        node.next = head;
        head.prev = node;
        head = node;
    }

    public void push_back(int n) {
        Node node = new Node(n);
        size++;
        if (head == null) {
            head = node;
            tail = head;
            return;
        }
        node.prev = tail;
        tail.next = node;
        tail = node;
    }

    public int pop_front() {
        int value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return value;
    }

    public int pop_back() {
        int value = tail.value;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return value;
    }

    public int front() {
        return head.value;
    }

    public int back() {
        return tail.value;
    }

    public int size() {
        return size;
    }

    public void clear() {
        tail = null;
        head = null;
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
        final Deque.FastScanner in = new Deque.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            String query;
            Deque deque = new Deque();
            do {
                query = in.next();
                switch (query) {
                    case "push_front":
                        int n = in.nextInt();
                        deque.push_front(n);
                        out.println("ok");
                        break;
                    case "push_back":
                        n = in.nextInt();
                        deque.push_back(n);
                        out.println("ok");
                        break;
                    case "pop_front":
                        if (deque.head == null) {
                            out.println("error");
                        } else {
                            out.println(deque.pop_front());
                        }
                        break;
                    case "pop_back":
                        if (deque.tail == null) {
                            out.println("error");
                        } else {
                            out.println(deque.pop_back());
                        }
                        break;
                    case "front":
                        if (deque.head == null) {
                            out.println("error");
                        } else {
                            out.println(deque.front());
                        }
                        break;
                    case "back":
                        if (deque.tail == null) {
                            out.println("error");
                        } else {
                            out.println(deque.back());
                        }
                        break;
                    case "size":
                        out.println(deque.size());
                        break;
                    case "clear":
                        deque.clear();
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
