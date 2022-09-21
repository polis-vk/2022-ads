package com.company.solution;

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

    static class Deque {
        private Node head;
        private Node tail;
        private int size;

        class Node {
            int value;
            Node next;
            Node prev;

            public Node(int value) {
                this.value = value;
            }
        }

        public void pushFront(int n) {
            if (tail == null) {
                tail = new Node(n);
                head = tail;
            } else {
                Node temp = new Node(n);
                temp.next = head;
                head.prev = temp;
                head = temp;
            }
            size++;
        }

        public void pushBack(int n) {
            if (tail == null) {
                tail = new Node(n);
                head = tail;
            } else {
                Node temp = new Node(n);
                temp.prev = tail;
                tail.next = temp;
                tail = temp;
            }
            size++;
        }

        public int popFront() {
            int n = head.value;
            Node temp = head.next;

            if (temp == null) {
                head = tail = null;
            } else {
                temp.prev = null;
                head.next = null;
                head = temp;
            }

            size--;
            return n;
        }

        public int popBack() {
            int n = tail.value;
            Node temp = tail.prev;

            if (temp == null) {
                head = tail = null;
            } else {
                temp.next = null;
                tail.prev = null;
                tail = temp;
            }

            size--;
            return n;
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
            head = tail = null;
            size = 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque deque = new Deque();

        String s = in.next();

        while (!s.equals("exit")) {
            switch (s) {
                case "push_front" -> {
                    deque.pushFront(in.nextInt());
                    out.println("ok");
                }
                case "push_back" -> {
                    deque.pushBack(in.nextInt());
                    out.println("ok");
                }
                case "pop_front" -> {
                    if (deque.size == 0) {
                        out.println("error");
                    } else {
                        out.println(deque.popFront());
                    }
                }
                case "pop_back" -> {
                    if (deque.size == 0) {
                        out.println("error");
                    } else {
                        out.println(deque.popBack());
                    }
                }
                case "front" -> {
                    if (deque.size == 0) {
                        out.println("error");
                    } else {
                        out.println(deque.front());
                    }
                }
                case "back" -> {
                    if (deque.size == 0) {
                        out.println("error");
                    } else {
                        out.println(deque.back());
                    }
                }
                case "size" -> out.println(deque.size());
                case "clear" -> {
                    deque.clear();
                    out.println("ok");
                }
            }
            s = in.next();
        }
        out.println("bye");
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