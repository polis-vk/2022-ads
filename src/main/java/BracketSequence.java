import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class BracketSequence {
    private BracketSequence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        out.println(solution(in.next()));
    }

    public static String solution(String string) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '[' || string.charAt(i) == '(' || string.charAt(i) == '{') {
                deque.addFirst(string.charAt(i));
            } else if (deque.size() == 0) {
                deque.addFirst('0');
                break;
            } else if (string.charAt(i) == ']' && deque.getFirst() == '[' || string.charAt(i) == ')' && deque.getFirst() == '(' || string.charAt(i) == '}' && deque.getFirst() == '{') {
                deque.removeFirst();
            } else {
                break;
            }
        }
        return (deque.size() == 0) ? "yes" : "no";
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