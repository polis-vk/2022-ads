import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class TaskE {
    private TaskE() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        try {
            Scanner scanner = new Scanner(System.in);
            int result = postfixCalculator(scanner.nextLine());
            System.out.println(result);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static int postfixCalculator(String str) throws NullPointerException {
        Deque<Integer> deque = new LinkedList<>();
        str = str.replace(" ", "");
        char[] charArr = str.toCharArray();
        for (char c : charArr) {
            if (Character.isDigit(c)) {
                deque.addLast((int) c - 48);
                continue;
            }
            switch (c) {
                case '-' -> {
                    deque.addLast(-(deque.pollLast() - deque.pollLast()));
                }
                case '+' -> {
                    deque.addLast(deque.pollLast() + deque.pollLast());
                }
                case '*' -> {
                    deque.addLast(deque.pollLast() * deque.pollLast());
                }
            }
        }
        return deque.pollLast();
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
