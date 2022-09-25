import java.io.*;
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
        // Write me
        out.println(isCorrect(in.next()) ? "yes" : "no");
    }

    public static boolean isCorrect(String brackets) {
        MyStack stack = new MyStack();

        Character currentBracket;
        for (int index = 0; index < brackets.length(); index++) {
            currentBracket = brackets.charAt(index);
            if ((currentBracket != ')') && (currentBracket != ']') && (currentBracket != '}')) {
                stack.push(currentBracket);
            } else {
                if (!stack.isEmpty() && check(stack.peek(), currentBracket)) {
                    stack.pop();
                } else {
                    stack.push(currentBracket);
                    break;
                }
            }
        }
        return (stack.isEmpty()) ? true : false;
    }

    public static boolean check(Character openingBracket, Character closingBracket) {
        if ((openingBracket == '(' && closingBracket == ')') || (openingBracket == '[' && closingBracket == ']') ||
                (openingBracket == '{' && closingBracket == '}')) {
            return true;
        } else {
            return false;
        }
    }

    private static class MyStack {
        private Character stack[];
        private int index;

        public MyStack() {
            stack = new Character[16];
            index = -1;
        }

        public void clear() {
            stack = new Character[16];
            index = -1;
        }

        public void push(Character item) {
            if (index == (stack.length - 1)) {
                Character temp[] = new Character[stack.length * 2];
                for (int i = 0; i < stack.length; i++) {
                    temp[i] = stack[i];
                }
                temp[++index] = item;
                stack = temp;
            } else {
                stack[++index] = item;
            }
        }

        public boolean isEmpty() {
            return (index == -1);
        }

        public Character pop() {

            return (index < 0) ? null : stack[index--];

        }

        public int size() {
            return (index + 1);
        }

        public Character peek() {
            return (index == -1) ? null : stack[index];
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