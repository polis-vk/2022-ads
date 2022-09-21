package com.company.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static int calculate(char op, int a, int b) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> 0;
        };
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Integer> stack = new LinkedList<>();
        Set<String> set = Set.of("+", "-", "*");
        Scanner scanner = new Scanner(System.in);
        String s;
        int a, b;

        while (scanner.hasNext()) {
            s = scanner.next();

            if (set.contains(s)) {
                b = stack.pop();
                a = stack.pop();
                stack.push(calculate(s.charAt(0), a, b));
            } else {
                stack.push(Integer.parseInt(s));
            }
        }

        out.println(stack.getLast());
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

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = createPrintWriterForLocalTests()) {
            solve(in, out);
        }
    }
}