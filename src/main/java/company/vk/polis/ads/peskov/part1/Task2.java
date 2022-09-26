package company.vk.polis.ads.peskov.part1;

import java.io.*;
import java.util.StringTokenizer;

public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    static class Queue {
        private final int[] array;
        private int size;
        private final int capacity;
        private int front;
        private int back;

        public Queue(int capacity) {
            this.array = new int[capacity];
            this.capacity = capacity;
        }

        public void push(int n) {
            if (isFull()) {
                throw new IllegalStateException();
            }
            array[back++] = n;
            back %= capacity;
            size++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            int value = array[front++];
            front %= capacity;
            size--;
            return value;
        }

        public int front() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            return array[front];
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.front = 0;
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
        Queue queue = new Queue(100);
        String command = in.next();
        while (!command.equals("exit")) {
            try {
                switch (command) {
                    case "push" -> {
                        queue.push(in.nextInt());
                        out.println("ok");
                    }
                    case "pop" -> out.println(queue.pop());
                    case "front" -> out.println(queue.front());
                    case "size" -> out.println(queue.size());
                    case "clear" -> {
                        queue.clear();
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
