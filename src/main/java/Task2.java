import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static class MyQueue {

        ArrayList<Integer> queue = new ArrayList<>();

        public void push(int x) {
            queue.add(x);
        }

        public int pop() {
            if (queue.isEmpty()) {
                throw new RuntimeException();
            }
            int element = queue.get(0);
            queue.remove(0);
            return element;
        }

        public int front() {
            if (queue.isEmpty()) {
                throw new RuntimeException();
            }
            return queue.get(0);
        }

        public int size() {
            return queue.size();
        }

        public void clear() {
            queue.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyQueue queue = new MyQueue();
        while (true) {
            switch (in.next()) {
                case "push":
                    queue.push(in.nextInt());
                    out.println("ok");
                    break;
                case "pop":
                    try {
                        out.println(queue.pop());
                    } catch (RuntimeException exception) {
                        out.println("error");
                    }
                    break;
                case "front":
                    try {
                        out.println(queue.front());
                    } catch (RuntimeException exception) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(queue.size());
                    break;
                case "clear":
                    queue.clear();
                    out.println("ok");
                    break;
                case "exit":
                    out.println("bye");
                    return;
                default:
                    System.exit(-1);
            }
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
