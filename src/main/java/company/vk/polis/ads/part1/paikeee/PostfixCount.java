package company.vk.polis.ads.part1.paikeee;

import java.util.ArrayDeque;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class PostfixCount {
    private PostfixCount() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String[] parts = in.reader.readLine().split(" ");
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String elem : parts) {
            switch (elem) {
                case "+" -> {
                   stack.push(Integer.toString(
                           Integer.parseInt(stack.pop()) +
                                   Integer.parseInt(stack.pop())));
                }
                case "-" -> {
                    stack.push(Integer.toString(
                            - Integer.parseInt(stack.pop()) +
                                    Integer.parseInt(stack.pop())));
                }
                case "*" -> {
                    stack.push(Integer.toString(
                            Integer.parseInt(stack.pop()) *
                                    Integer.parseInt(stack.pop())));
                }
                default-> {
                    stack.push(elem);
                }
            }
        }
        if (!stack.isEmpty()) {
            out.println(stack.pop());
        } else {
            out.println(0);
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
            try {
                solve(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
