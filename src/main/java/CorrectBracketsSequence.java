import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class CorrectBracketsSequence {

    private CorrectBracketsSequence() {
        // Should not be instantiated
    }

    private static boolean checkBracketsMatch(char openingBracket, char closingBracket) {
        if (openingBracket == '(' && closingBracket == ')') {
            return true;
        } else if (openingBracket == '[' && closingBracket == ']') {
            return true;
        } else if (openingBracket == '{' && closingBracket == '}') {
            return true;
        }
        return false;
    }
    private static void solve(final CorrectBracketsSequence.FastScanner in, final PrintWriter out) {
        Deque<Character> bracketsDeque = new ArrayDeque<>();
        String inputStr = in.next();
        boolean isCorrect = true;

        for (char bracket : inputStr.toCharArray()) {
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                bracketsDeque.addLast(bracket);
            } else if (bracket == ')' || bracket == ']' || bracket == '}') {
                Character openingBracket = bracketsDeque.pollLast();
                if (openingBracket == null || checkBracketsMatch(openingBracket, bracket) == false) {
                    isCorrect = false;
                    break;
                }
            }
        }
        if (bracketsDeque.size() != 0) {
            isCorrect = false;
        }

        if (isCorrect) {
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
        final CorrectBracketsSequence.FastScanner in = new CorrectBracketsSequence.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
