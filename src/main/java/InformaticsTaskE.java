import java.io.*;
import java.util.*;


/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class InformaticsTaskE {
    private InformaticsTaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Deque<Integer> deque = new ArrayDeque<>();
        for (String c : in.reader.readLine().split(" ")) {
            if (c.equals("*")) {
                deque.addLast(deque.pollLast() * deque.pollLast());
            } else {
                switch (c) {
                    case ("+") -> deque.addLast(deque.pollLast() + deque.pollLast());
                    case ("-") -> deque.addLast(-deque.pollLast() + deque.pollLast());
                    default -> deque.addLast(Integer.parseInt(c));
                }
            }
        }
        out.println(deque.pop());
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