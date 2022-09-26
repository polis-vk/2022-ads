package company.vk.polis.ads.peskov.part1;

import java.io.*;
import java.util.StringTokenizer;

public final class Task6 {
    private Task6() {
        // Should not be instantiated
    }

    static class Deque {
        private final int[] array;
        private int size;
        private final int capacity;
        private int front;
        private int back;

        public Deque(int capacity) {
            this.array = new int[capacity];
            this.capacity = capacity;
            this.front = capacity - 1;
        }

        public void push_front(int n) {
            if (isFull()) {
                throw new IllegalStateException();
            }
            array[front--] = n;
            front = (front + capacity) % capacity;
            size++;
        }

        public void push_back(int n) {
            if (isFull()) {
                throw new IllegalStateException();
            }
            array[back++] = n;
            back %= capacity;
            size++;
        }

        public int pop_front() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            size--;
            front = (front + 1) % capacity;
            return array[front];
        }

        public int pop_back() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            size--;
            back = (back - 1 + capacity) % capacity;
            return array[back];
        }

        public int front() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            return array[(front + 1) % capacity];
        }

        public int back() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            return array[(back - 1 + capacity) % capacity];
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.front = this.capacity - 1;
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
        Deque deque = new Deque(100);
        String command = in.next();
        while (!command.equals("exit")) {
            try {
                switch (command) {
                    case "push_front" -> {
                        deque.push_front(in.nextInt());
                        out.println("ok");
                    }
                    case "push_back" -> {
                        deque.push_back(in.nextInt());
                        out.println("ok");
                    }
                    case "pop_front" -> out.println(deque.pop_front());
                    case "pop_back" -> out.println(deque.pop_back());
                    case "front" -> out.println(deque.front());
                    case "back" -> out.println(deque.back());
                    case "size" -> out.println(deque.size());
                    case "clear" -> {
                        deque.clear();
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
