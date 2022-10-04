import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class TaskD {

    private static void solve(final FastScanner in, final PrintWriter out) {


        boolean result = true;
        Stack<Character> stack = new Stack<>();
        String input = in.next();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) {
                result = false;
                break;
            }

            char firstBracket = stack.pop();

            if (firstBracket == '{' && c != '}') {
                result = false;
                break;
            }

            if (firstBracket == '[' && c != ']') {
                result = false;
                break;
            }

            if (firstBracket == '(' && c != ')') {
                result = false;
                break;
            }
        }

        result &= stack.isEmpty();

        if (result) {
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
            solve(in, out);
        }
    }
}