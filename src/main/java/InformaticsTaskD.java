import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class InformaticsTaskD {
    private InformaticsTaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Deque<Character> deque = new ArrayDeque<>();
        String input = in.next();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ('(') || c == ('[') || c == ('{')) {
                deque.addLast(c);
            } else if (c == (')') || c == (']') || c == ('}')) {
                boolean flag = false;
                if (!deque.isEmpty()) {
                    char lastElem = deque.peekLast();
                    switch (c) {
                        case (')') -> flag = lastElem == ('(');
                        case (']') -> flag = lastElem == ('[');
                        default -> flag = lastElem == ('{');
                    }
                }
                if (flag) {
                    deque.pollLast();
                } else {
                    out.println("no");
                    return;
                }
            }
        }
        out.println(deque.isEmpty() ? "yes" : "no");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}