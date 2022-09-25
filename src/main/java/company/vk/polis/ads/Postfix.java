package company.vk.polis.ads;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Postfix {
    private static int evaluatePostfix(final FastScanner in, final PrintWriter out) throws IOException {
        Deque<Integer> stack = new ArrayDeque<>();
        String line = in.reader.readLine();
        int a;
        int b = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c >= '0' && c <= '9') {
                stack.push(Character.getNumericValue(c));
            } else {
                if (c == '-' || c == '+' || c == '*' || c == '/') {
                    a = stack.pop();
                    b = stack.pop();
                    switch (c) {
                        case '-' -> b-=a;
                        case '+' -> b+=a;
                        case '*' -> b*=a;
                        case '/' -> b/=a;
                    }
                    stack.push(b);
                }
            }
        }
        return b;
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
        final Postfix.FastScanner in = new Postfix.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            out.println(evaluatePostfix(in, out));
        } catch (IOException ignored) {

        }
    }
}
