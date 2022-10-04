package part1;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task6 {
    private Task6() {
        // Should not be instantiated
    }

    private static class MyDeque {

        ArrayList<Integer> deque = new ArrayList<>();

        public void push_front(int x) {
            deque.add(0, x);
        }

        public void push_back(int x) {
            deque.add(x);
        }

        public int pop_front() {
            if (deque.isEmpty()) {
                throw new RuntimeException();
            }
            int element = deque.get(0);
            deque.remove(0);
            return element;
        }

        public int pop_back() {
            if (deque.isEmpty()) {
                throw new RuntimeException();
            }
            int element = deque.get(deque.size() - 1);
            deque.remove(deque.size() - 1);
            return element;
        }

        public int front() {
            if (deque.isEmpty()) {
                throw new RuntimeException();
            }
            return deque.get(0);
        }

        public int back() {
            if (deque.isEmpty()) {
                throw new RuntimeException();
            }
            return deque.get(deque.size() - 1);
        }

        public int size() {
            return deque.size();
        }

        public void clear() {
            deque.clear();
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        MyDeque deque = new MyDeque();
        while (true) {
            switch (in.next()) {
                case "push_front":
                    deque.push_front(in.nextInt());
                    out.println("ok");
                    break;
                case "push_back":
                    deque.push_back(in.nextInt());
                    out.println("ok");
                    break;
                case "pop_front":
                    try {
                        out.println(deque.pop_front());
                    } catch (RuntimeException exception) {
                        out.println("error");
                    }
                    break;
                case "pop_back":
                    try {
                        out.println(deque.pop_back());
                    } catch (RuntimeException exception) {
                        out.println("error");
                    }
                    break;
                case "front":
                    try {
                        out.println(deque.front());
                    } catch (RuntimeException exception) {
                        out.println("error");
                    }
                    break;
                case "back":
                    try {
                        out.println(deque.back());
                    } catch (RuntimeException exception) {
                        out.println("error");
                    }
                    break;
                case "size":
                    out.println(deque.size());
                    break;
                case "clear":
                    deque.clear();
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
