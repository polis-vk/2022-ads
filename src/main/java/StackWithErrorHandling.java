import java.io.*;
import java.util.StringTokenizer;

public class StackWithErrorHandling {
    private static final class IntQueue {
        private int[] array;
        private int size;
        private int capacity;
        private int index;

        public IntQueue() {
            this.clear();
        }

        public int size() {
            return this.size;
        }

        public void clear() {
            this.capacity = 1;
            this.size = 0;
            this.index = -1;
            this.array = new int[this.capacity];
        }

        public Integer front() {
            if (index == -1) {
                return null;
            }
            return this.array[this.index];
        }

        public Integer pop() {
            if (index == -1) {
                return null;
            }
            this.size--;
            return this.array[this.index--];
        }

        public void push(int n) {
            if (this.size == this.capacity) {
                this.resize();
            }

            this.array[++this.index] = n;
            this.size++;
        }

        private void resize() {
            int[] oldArray = this.array;
            this.capacity = (int) Math.ceil((double) this.capacity * 1.5);
            this.array = new int[this.capacity];

            for (int i = 0; i < this.size; i++) {
                this.array[i] = oldArray[i];
            }
        }
    }

    private StackWithErrorHandling() {
        // Should not be instantiated
    }

    private static void solve(final StackWithErrorHandling.FastScanner in, final PrintWriter out) {
        StackWithErrorHandling.IntQueue intQueue = new StackWithErrorHandling.IntQueue();
        boolean isExit = false;

        while (isExit != true) {
            String command = in.next();
            String message = "error";

            switch (command) {
                case "size":
                    message = Integer.toString(intQueue.size());
                    break;
                case "back":
                    Integer numBack = intQueue.front();
                    if (numBack != null) {
                        message = Integer.toString(numBack);
                    }
                    break;
                case "pop":
                    Integer numPop = intQueue.pop();
                    if (numPop != null) {
                        message = Integer.toString(numPop);
                    }
                    break;
                case "push":
                    int n = in.nextInt();
                    intQueue.push(n);
                    message = "ok";
                    break;
                case "clear":
                    intQueue.clear();
                    message = "ok";
                    break;
                case "exit":
                    isExit = true;
                    message = "bye";
                    break;
            }

            out.println(message);
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
        final StackWithErrorHandling.FastScanner in = new StackWithErrorHandling.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}