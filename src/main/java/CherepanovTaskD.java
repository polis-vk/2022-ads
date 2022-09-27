import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class CherepanovTaskD {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(FastScanner in, final PrintWriter out) throws IOException {
        String bracketSequence = in.reader.readLine();
        Deque<Character> stack = new ArrayDeque<>();
        boolean isConsistent = true;
        for (int i = 0; i < bracketSequence.length() && isConsistent; i++) {
            char bracket = bracketSequence.charAt(i);
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                stack.push(bracket);
                continue;
            }
            if (stack.isEmpty()) {
                isConsistent = false;
                break;
            }
            char temp = stack.pop();
            switch (bracket) {
                case ')':
                    if (temp == '[' || temp == '{') {
                        isConsistent = false;
                    }
                    break;
                case ']':
                    if (temp == '(' || temp == '{') {
                        isConsistent = false;
                    }
                    break;
                case '}':
                    if (temp == '(' || temp == '[') {
                        isConsistent = false;
                    }
                    break;
            }
        }
        if (!stack.isEmpty()) {
            isConsistent = false;
        }
        out.println(isConsistent ? "yes" : "no");
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
            throw new RuntimeException(e);
        }
    }
}
