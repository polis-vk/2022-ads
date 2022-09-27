package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException; import java.io.InputStream;
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
public final class DequeueWithErrorProtection {
    List<Integer> innerArray = new ArrayList<>();

    private String push_front(Integer n) {
        innerArray.add(0, n);
        return "ok\n";
    }
    private String push_back(Integer n) {
        innerArray.add(n);
        return "ok\n";
    }
    private Integer pop_front() {
        Integer num = innerArray.get(0);
        innerArray.remove(0);
        return num;
    }
    private Integer pop_back() {
        Integer num = innerArray.get(innerArray.size() - 1);
        innerArray.remove(innerArray.size() - 1);
        return num;
    }
    private Integer front() {
        return innerArray.get(0);
    }
    private Integer back() {
        return innerArray.get(innerArray.size() - 1);
    }
    private Integer size() {
        return innerArray.size();
    }
    private String clear() {
        innerArray.clear();
        return "ok\n";
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        String request = in.next();
        DequeueWithErrorProtection dequeue = new DequeueWithErrorProtection();
        for (;!request.equals("exit");)  {
            switch (request) {
                case "push_front" -> out.write(dequeue.push_front(in.nextInt()));
                case "push_back" -> out.write(dequeue.push_back(in.nextInt()));
                case "pop_front" -> {
                    if (dequeue.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(dequeue.pop_front() + "\n");
                }
                case "pop_back" -> {
                    if (dequeue.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(dequeue.pop_back() + "\n");
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
                case "size" -> out.write(dequeue.size() +"\n");
                case "clear" -> out.write(dequeue.clear() + "\n");
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

