package company.vk.polis.ads.peskov.part1;

import java.io.*;
import java.util.StringTokenizer;

public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }

    static class Stack {
        private final int[] array;
        private int size;
        private final int capacity;
        private int back;

        public Stack(int capacity) {
            this.array = new int[capacity];
            this.capacity = capacity;
        }

        public void push(int n) {
            if (isFull()) {
                throw new IllegalStateException();
            }
            array[back++] = n;
            size++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            size--;
            return array[--back];
        }

        public int back() {
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
        Stack stack = new Stack(100);
        String command = in.next();
        while (!command.equals("exit")) {
            try {
                switch (command) {
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
            } catch (IllegalStateException e) {
                out.println("error");
            }
            command = in.next();
        }
        out.println("bye");
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
