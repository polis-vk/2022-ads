package company.vk.polis.ads.part1.tedbear;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DequeueWithErrorProtection {
    private int size;
    private final ArrayList<Integer> internalArray = new ArrayList<>();

    public String push_front(Integer n) {
        internalArray.add(0, n);
        this.size++;

        return "ok\n";
    }

    public String push_back(Integer n) {
        internalArray.add(n);
        this.size++;

        return "ok\n";
    }

    public int pop_front() {
        int lastElement = internalArray.get(0);
        internalArray.remove(0);
        this.size--;
        return lastElement;
    }

    public int pop_back() {
        int lastElement = internalArray.get(this.size - 1);
        internalArray.remove(this.size - 1);
        this.size--;
        return lastElement;
    }

    public Integer front() {
        return internalArray.get(0);
    }

    public Integer back() {
        return internalArray.get(this.size - 1);
    }

    public Integer size() {
        return this.size;
    }

    public String clear() {
        this.size = 0;
        internalArray.clear();

        return "ok\n";
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        String command = in.next();
        DequeueWithErrorProtection dequeue = new DequeueWithErrorProtection();

        while(!command.equals("exit")) {
            switch (command) {
                case "pop_back" -> {
                    if (dequeue.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(dequeue.pop_back() + "\n");
                }
                case "pop_front" -> {
                    if (dequeue.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(dequeue.pop_front() + "\n");
                }
                case "front" -> {
                    if (dequeue.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(dequeue.front() + "\n");
                }
                case "back" -> {
                    if (dequeue.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(dequeue.back() + "\n");
                }
                case "size" -> {
                    out.write(dequeue.size() + "\n");
                }
                case "clear" -> {
                    out.write(dequeue.clear() + "\n");
                }
                case "push_front" -> {
                    Integer number = in.nextInt();
                    out.write(dequeue.push_front(number));
                }
                case "push_back" -> {
                    Integer number = in.nextInt();
                    out.write(dequeue.push_back(number));
                }
            }
            command = in.next();
        }
        out.write("bye\n");
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
