//package com.company;

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
public final class QueueSolveTemplate {
    private QueueSolveTemplate() {
        // Should not be instantiated
    }

    static class CustomQueue {

        private Node head;
        private Node tail;
        private int size = 0;

        public void push(int value) {
            Node newNode = new Node(value);
            if (size == 0)
                tail = head = newNode;
            else {
                tail.next = newNode;
                tail = tail.next;
            }
            size++;
        }

        public int pop() {
            if (size == 0)
                throw new RuntimeException("Queue is empty");
            int value = head.value;
            head = head.next;
            size--;
            return value;
        }

        public int front() {
            if (size == 0)
                throw new RuntimeException("Queue is empty");
            return head.value;
        }

        public int size() {
            return size;
        }

        public void clear() {
            tail = head = null;
            size = 0;
        }

        private static class Node {
            public Node next;
            public int value;
            public Node(int value) {
                this.value = value;
            }
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        CustomQueue queue = new CustomQueue();
        while (true)
            switch (in.next()) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(queue.pop());
                    } catch (RuntimeException e) {
                        out.println("error");
                    }
                    break;
                case "front":
                    try {
                        out.println(queue.front());
                    } catch (RuntimeException e) {
                        out.println("error");
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
