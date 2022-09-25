package company.vk.polis.ads.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    static class StackException extends Exception {
    }

    public static class Stack {
        private int[] data = new int[16];
        int size;

        void expand() {
            data = Arrays.copyOf(data, data.length * 2);
        }

        void push(int n) {
            if (size == data.length) {
                expand();
            }
            data[size++] = n;
        }

        int pop() throws StackException {
            if (size == 0) {
                throw new StackException();
            }
            return data[--size];
        }

        int back() throws StackException {
            if (size == 0) {
                throw new StackException();
            }
            return data[size - 1];
        }

        int size() {
            return size;
        }

        void clear() {
            size = 0;
        }
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack stack = new Stack();

        String s = in.next();

        while (!s.equals("exit")) {
            try {
                switch (s) {
                    case "push" -> {
                        stack.push(in.nextInt());
                        out.println("ok");
                    }
                    case "pop" -> out.println(stack.pop());
                    case "back" -> out.println(stack.back());
                    case "size" -> out.println(stack.size());
                    case "clear" -> {
                        stack.clear();
                        out.println("ok");
                    }
                }
            } catch (StackException e) {
                out.println("error");
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