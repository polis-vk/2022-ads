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
public final class HooksOrder {
    private HooksOrder() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String order = in.reader.readLine();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        boolean correct = true;
        for (int i = 0; i < order.length(); i++) {
            switch (order.charAt(i)) {
                case '(' -> stack.push('(');
                case '[' -> stack.push('[');
                case '{' -> stack.push('{');
                case ')' -> {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        correct = false;
                    }
                }
                case ']' -> {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        correct = false;
                    }
                }
                case '}' -> {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        correct = false;
                    }
                }
            }
            if (!correct) {
                break;
            }
        }
        if (correct && stack.isEmpty()) {
            out.println("yes");
        } else {
            out.println("no");
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
