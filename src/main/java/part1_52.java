
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
public class part1_52 {
    private part1_52() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        Deque<Integer> deque = new ArrayDeque<>();
        String inp;
        while (true) {
            try {
                inp = in.next();
            } catch (NullPointerException e) {
                out.println(deque.pop());
                return;
            }
//            out.println(inp);
            try {
                deque.addFirst(Integer.parseInt(inp));
            } catch (NumberFormatException e){
                int b = deque.pop();
                int a = deque.pop();
                char c = inp.charAt(0);
                if (c == '+') {
                    deque.addFirst(a+b);
                } else if (c == '-') {
                    deque.addFirst(a - b);
                } else if (c == '*') {
                    deque.addFirst(a*b);
                } else if (c == '/'){
                    deque.addFirst(a/b);
                } else {
                    throw new IllegalStateException();
                }
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
