package company.vk.polis.ads.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    public static boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Character> map = Map.of(')', '(', ']', '[', '}', '{');

        char temp;

        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                temp = 0;
            } else {
                temp = stack.getLast();
            }

            if (c == '(' || c == '{' || c == '[') {
                stack.addLast(c);
            } else if (temp == map.get(c)) {
                stack.removeLast();
            } else {
                return false;
            }
        }

        return stack.size() == 0;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s = in.next();
        System.out.println(isValid(s) ? "yes" : "no");
    }

    private static class FastScanner {
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