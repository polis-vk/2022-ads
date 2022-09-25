package company.vk.polis.ads;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Braces {

    private static boolean checkBalance(final FastScanner in, final PrintWriter out) throws IOException{
        Deque<Character> stack = new ArrayDeque<>();
        int parentheses = 0, brackets = 0, braces = 0;

        String line = in.reader.readLine();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '(') {
                parentheses++;
            } else if (c == ')') {
                parentheses--;
            } else if (c == '{') {
                braces++;
            } else if (c == '}') {
                braces--;
            } else if (c == '[') {
                brackets++;
            } else if (c == ']') {
                brackets--;
            }

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            if (parentheses < 0 || braces < 0 || brackets < 0) {
                return false;
            }
            if (!stack.isEmpty()) {
                char temp = stack.peek();
                if (c == ')' && temp != '('
                        || c == ']' && temp != '['
                        || c == '}' && temp != '{') {

                    return false;
                }
                if (c == ')' && temp == '(' || c == ']' && temp == '[' || c == '}' && temp == '{') {
                    stack.pop();
                }
            }
        }
        return parentheses == 0 && braces == 0 && brackets == 0;
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
        final Braces.FastScanner in = new Braces.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            if (checkBalance(in, out)) {
                out.println("yes");
            } else {
                out.println("no");
            }
        } catch (IOException ignored) {

        }
    }
}
