import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class QueueWithErrorHandling {
    private static final class IntQueue {
        private int[] array;
        private int size;
        private int capacity;
        private int index;

        public IntQueue() {
            this.capacity = 1;
            this.size = 0;
            this.index = -1;
            this.array = new int[this.capacity];
        }

        public int size() {
            return this.size;
        }

        public String info() {
            String infoStr = "";
            infoStr += "array: " + Arrays.toString(this.array);
            infoStr += " capacity: " + this.capacity;
            infoStr += " size: " + this.size;
            infoStr += " index: " + this.index;
            return infoStr;
        }

        public boolean push(int n) {
            if (this.size == this.capacity) {
                this.resize();
            }

            this.array[++this.index] = n;
            this.size++;
            return true;
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

    private QueueWithErrorHandling() {
        // Should not be instantiated
    }

    private static void solve(final QueueWithErrorHandling.FastScanner in, final PrintWriter out) {
        QueueWithErrorHandling.IntQueue intQueue = new QueueWithErrorHandling.IntQueue();
        boolean isExit = false;

        while (isExit != true) {
            String command = in.next();
            String message = "error";

            switch (command) {
                case "size":
                    message = Integer.toString(intQueue.size());
                    break;
                case "push":
                    int n = in.nextInt();
                    if (intQueue.push(n)) {
                        message = "ok";
                    }
                    break;
                case "exit":
                    isExit = true;
                    message = "bye";
                    break;
            }
            out.println(intQueue.info());
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
        final QueueWithErrorHandling.FastScanner in = new QueueWithErrorHandling.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
