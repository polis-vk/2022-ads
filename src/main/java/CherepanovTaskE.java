import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class CherepanovTaskE {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(FastScanner in, final PrintWriter out) throws IOException {
        String sequence = in.reader.readLine().replaceAll("\\s","");
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < sequence.length(); i++) {
            char letter = sequence.charAt(i);
            if (Character.isDigit(letter)) {
                stack.push(Character.getNumericValue(letter));
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                switch (letter) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                }
            }
        }
        out.println(stack.pop());
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
