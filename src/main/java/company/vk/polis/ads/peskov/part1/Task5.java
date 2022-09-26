package company.vk.polis.ads.peskov.part1;

import java.io.*;
import java.util.StringTokenizer;

public final class Task5 {
    private Task5() {
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

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Stack stack = new Stack(100000);
        String[] expression = in.readLine().split(" ");
        try {
            for (String item : expression){
                switch (item) {
                    case "*" -> stack.push(stack.pop() * stack.pop());
                    case "+" -> stack.push(stack.pop() + stack.pop());
                    case "-" -> {
                        int subtrahend = stack.pop();
                        stack.push(stack.pop() - subtrahend);
                    }
                    default -> stack.push(Integer.parseInt(item));
                }
            }
            out.println(stack.pop());
        } catch (IllegalStateException e) {
            e.printStackTrace();
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

        String readLine() throws IOException {
            return reader.readLine();
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
