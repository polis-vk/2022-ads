package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class QueueWithErrorProtection {
    private final List<Integer> innerArray = new ArrayList<>();

    private String push(Integer n) {
        innerArray.add(n);
        return "ok\n";
    }

    private int pop() {
        int element = innerArray.get(0);
        innerArray.remove(0);
        return element;
    }

    private int front() {
        return innerArray.get(0);
    }

    private int size() {
        return innerArray.size();
    }

    private String clear() {
        innerArray.clear();
        return "ok\n";
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String request = in.next();
        QueueWithErrorProtection queue = new QueueWithErrorProtection();
        for (; !request.equals("exit"); ) {
            switch (request) {
                case "push" -> out.write(queue.push(in.nextInt()));
                case "pop" -> {
                    if (queue.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(queue.pop() + "\n");
                }
                case "front" -> {
                    if (queue.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(queue.front() + "\n");
                }
                case "size" -> out.write(queue.size() + "\n");
                case "clear" -> out.write(queue.clear() + "\n");
            }
            request = in.next();
        }
        out.write("bye");
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

