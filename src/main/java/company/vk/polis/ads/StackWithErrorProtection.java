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
public final class StackWithErrorProtection  {
    List<Integer> innerArray = new ArrayList<>();

    private String push(Integer n) {
        innerArray.add(n);
        return "ok\n";
    }
    private Integer pop() {
        Integer num = innerArray.get(innerArray.size() - 1);
        innerArray.remove(innerArray.size() - 1);
        return num;
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
        StackWithErrorProtection stack = new StackWithErrorProtection();
        for (; !request.equals("exit"); ) {
            switch (request) {
                case "push" -> out.write(stack.push(in.nextInt()));
                case "pop" -> {
                    if (stack.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(stack.pop() + "\n");
                }
                case "back" -> {
                    if (stack.size() == 0) {
                        out.write("error\n");
                        break;
                    }
                    out.write(stack.back() + "\n");
                }
                case "size" -> out.write(stack.size() + "\n");
                case "clear" -> out.write(stack.clear() + "\n");
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
