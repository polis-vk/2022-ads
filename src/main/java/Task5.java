import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque<Integer> deque = new ArrayDeque<Integer>();
        String[] input;

        try {
            input = in.reader.readLine().split(" ");
        } catch (IOException e) {
            return;
        }

        for (int i = 0; i < input.length; i++) {
            switch (input[i].charAt(0)) {
                case '*':
                    deque.addLast(deque.pollLast() * deque.pollLast());
                    break;
                case '+':
                    deque.addLast(deque.pollLast() + deque.pollLast());
                    break;
                case '-':
                    deque.addLast(-deque.pollLast() + deque.pollLast());
                    break;
                default:
                    deque.addLast(Integer.parseInt(input[i]));
                    break;
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
        }
    }
}
