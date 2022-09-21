//package com.company;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class DequeSolveTemplate {
    private DequeSolveTemplate() {
        // Should not be instantiated
    }

    private static class Deque {

        private Node head;
        private Node tail;
        private int size = 0;

        public void pushFront(int value) {
            Node newNode = new Node(value);
            if (size == 0)
                tail = head = newNode;
            else {
                newNode.next = head;
                head.previous = newNode;
                head = newNode;
            }
            size++;
        }

        public void pushBack(int value) {
            Node newNode = new Node(value);
            if (size == 0)
                tail = head = newNode;
            else {
                newNode.previous = tail;
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        public int popFront() {
            int value = head.value;
            head = head.next;
            if (head != null)
                head.previous = null;
            size--;
            return value;
        }

        public int popBack() {
            int value = tail.value;
            tail = tail.previous;
            if (tail != null)
                tail.next = null;
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
            tail = head = null;
            size = 0;
        }


        private static class Node {
            public int value;
            public Node next;
            public Node previous;
            public Node(int value) {
                this.value = value;
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();
        while (true)
            switch (in.next()) {
                case "push_front":
                    deque.pushFront(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.pushBack(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    if (deque.size() == 0)
                        out.println("error");
                    else
                        out.println(deque.popFront());
                    break;
                case "pop_back":
                    if (deque.size() == 0)
                        out.println("error");
                    else
                        out.println(deque.popBack());
                    break;
                case "front":
                    if (deque.size() == 0)
                        out.println("error");
                    else
                        out.println(deque.front());
                    break;
                case "back":
                    if (deque.size() == 0)
                        out.println("error");
                    else
                        out.println(deque.back());
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
                    return;
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