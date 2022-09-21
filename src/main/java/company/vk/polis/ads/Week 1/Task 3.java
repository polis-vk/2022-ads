package com.company.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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

    static class StackException extends Exception {
    }

    public static class Stack {
        private int[] data = new int[16];
        int c;

        void expand() {
            data = Arrays.copyOf(data, data.length * 2);
        }

        void push(int n) {
            if (c == data.length) {
                 expand();
            }
            data[c++] = n;
        }

        int pop() throws StackException {
            if (c == 0) {
                throw new StackException();
            }
            return data[--c];
        }

        int back() throws StackException {
            if (c == 0) {
                throw new StackException();
            }
            return data[c - 1];
        }

        int size() {
            return c;
        }

        void clear() {
            c = 0;
        }
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();

        String s = in.next();

        while (!s.equals("exit")) {
            try {
                switch (s) {
                    case "push":
                        stack.push(in.nextInt());
                        System.out.println("ok");
                        break;
                    case "pop":
                        System.out.println(stack.pop());
                        break;
                    case "back":
                        System.out.println(stack.back());
                        break;
                    case "size":
                        System.out.println(stack.size());
                        break;
                    case "clear":
                        stack.clear();
                        System.out.println("ok");
                        break;
                }
            } catch (StackException e) {
                System.out.println("error");
            }
            s = in.next();
        }
        System.out.println("bye");
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

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }
}