//package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class StackSolveTemplate {

    private StackSolveTemplate() {
        // Should not be instantiated
    }

    static class Stack {
        private Node head;
        private int size = 0;
        public void push(int value) {
            Node newNode = new Node(value);
            if (head == null)
                head = newNode;
            else {
                newNode.next = head;
                head = newNode;
            }
            size++;
        }
        public int pop() {
            if (size == 0)
                throw new RuntimeException("Stack is empty");
            int value = head.value;
            head = head.next;
            size--;
            return value;
        }
        public int back() {
            if (size == 0)
                throw new RuntimeException("Stack is empty");
            return head.value;
        }
        public int size() {
            return size;
        }
        public void clear() {
            head = null;
            size = 0;
        }
        private static class Node {
            public int value;
            public Node next;
            public Node(int value) {
                this.value = value;
            }
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();
        while (true)
            switch (in.next()) {
                case "push":
                    stack.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(stack.pop());
                    } catch (RuntimeException e) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(stack.back());
                    } catch (RuntimeException e) {
                        out.println("error");
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