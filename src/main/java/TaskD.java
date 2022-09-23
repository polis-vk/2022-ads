import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
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

    private static final Map<Character, Character> pairMap = Map.of(')', '(', '}', '{', ']', '[');

    private static void solve(final FastScanner in, final PrintWriter out) {
        Deque<Character> deque = new ArrayDeque<>();
        String str = in.next();
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            switch(ch) {
                case '(':
                case '{':
                case '[':
                    deque.addLast(ch);
                    break;
                case ')':
                case '}':
                case ']':
                    if(deque.isEmpty() || !deque.removeLast().equals(pairMap.get(ch))) {
                        out.println("no");
                        return;
                    }
            }
        }
        if (deque.isEmpty()) {
            out.println("yes");
        }
        else {
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
            solve(in, out);
        }
    }
}
