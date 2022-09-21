//package com.company;

import java.io.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;


public final class BracketsSolveTemplate {
    private BracketsSolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        out.println(isValid(in.next()) ? "yes" : "no");
    }

    public static boolean isValid(String str) {
        LinkedList<Character> initialBrackets = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char symbol = str.charAt(i);
            switch (symbol) {
                case '(':
                case '{':
                case '[':
                    initialBrackets.addLast(symbol);
                    continue;
                case ')':
                    if (initialBrackets.isEmpty() || initialBrackets.pollLast() != '(')
                        return false;
                    break;
                case ']':
                    if (initialBrackets.isEmpty() || initialBrackets.pollLast() != '[')
                        return false;
                    break;
                case '}':
                    if (initialBrackets.isEmpty() || initialBrackets.pollLast() != '{')
                        return false;
                    break;
            }
        }
        return initialBrackets.isEmpty();
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