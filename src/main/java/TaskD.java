import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskD {
    private TaskD() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        boolean result = isRight(in.next());
        System.out.println(result ? "yes" : "no");
    }

    public static boolean isRight(String str) {
        Deque<Character> deque = new LinkedList<>();
        Map<Character, Character> map = Map.of(')', '(', ']', '[', '}', '{');
        char[] bracketsArr = str.toCharArray();
        HashSet<Character> openBracketsSet = new HashSet<>(Arrays.asList('(', '[', '{'));
        for (char c : bracketsArr) {
            if (openBracketsSet.contains(c)) {
                deque.addLast(c);
            } else if (deque.isEmpty() || map.get(c) != deque.pollLast()) {
                return false;
            }
        }
        return deque.isEmpty();
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
