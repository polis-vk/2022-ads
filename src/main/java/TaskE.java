import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

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
        String[] expr = new Scanner(System.in).nextLine().trim().split(" ");
        Deque<Integer> deque = new LinkedList<>();
        int firstEl, secondEl;
        for (String el : expr) {
            if (isOperator(el) && !deque.isEmpty()) {
                firstEl = deque.pollLast();
                secondEl = deque.pollLast();
                deque.add(applyOperator(el, firstEl, secondEl));
            } else {
                deque.add(Integer.parseInt(el));
            }
        }
        out.println(deque.pop());
    }

    private static int applyOperator(String operator, int firstEl, int secondEl) {
        switch (operator) {
            case "+":
                return secondEl + firstEl;
            case "-":
                return secondEl - firstEl;
            case "*":
                return secondEl * firstEl;
        }
        return -1;
    }

    private static boolean isOperator(String el) {
        return "+-*".contains(el);
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
