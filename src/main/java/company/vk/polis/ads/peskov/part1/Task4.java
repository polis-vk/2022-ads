package company.vk.polis.ads.peskov.part1;

import java.io.*;
import java.util.*;

public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    static class Stack {
        private final char[] array;
        private int size;
        private final int capacity;
        private int back;

        public Stack(int capacity) {
            this.array = new char[capacity];
            this.capacity = capacity;
        }

        public void push(char ch) {
            if (isFull()) {
                throw new IllegalStateException();
            }
            array[back++] = ch;
            size++;
        }

        public char pop() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            size--;
            return array[--back];
        }

        public char back() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            return array[back - 1];
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.back = 0;
            this.size = 0;
        }

        private boolean isFull() {
            return this.size == this.capacity;
        }

        private boolean isEmpty() {
            return this.size == 0;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Stack brackets = new Stack(100000);
        String str = in.next();
        try {
            for (char ch : str.toCharArray()) {
                switch (ch) {
                    case '(', '[', '{' -> brackets.push(ch);

                    case ')' -> {
                        if (brackets.pop() != '(') {
                            out.println("no");
                            return;
                        }
                    }
                    case ']' -> {
                        if (brackets.pop() != '[') {
                            out.println("no");
                            return;
                        }
                    }
                    case '}' -> {
                        if (brackets.pop() != '{') {
                            out.println("no");
                            return;
                        }
                    }
                }
            }
        } catch (IllegalStateException e) {
            out.println("no");
            return;
        }

        if (brackets.isEmpty()) {
            out.println("yes");
        } else {
            out.println("no");
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
