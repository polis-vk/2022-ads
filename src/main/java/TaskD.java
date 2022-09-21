import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

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
        String braces = in.next();
        Deque<Character> deque = new LinkedList<>();
        for (char c : braces.toCharArray()) {
            if (isOpeningBrace(c)) {
                deque.push(c);
            } else if (deque.isEmpty() || !isMatchingBraces(deque.pop(), c)) {
                out.println("no");
                return;
            }
        }
        if (deque.isEmpty()) {
            out.println("yes");
        } else {
            out.println("no");
        }
    }

    public static boolean isOpeningBrace(char c) {
        return "({[".indexOf(c) != -1;
    }

    public static boolean isMatchingBraces(char stackCh, char strCh) {
        return '(' == stackCh && ')' == strCh || '[' == stackCh && ']' == strCh
                || '{' == stackCh && '}' == strCh;
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
