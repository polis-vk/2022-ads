package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */

// Submission link: https://www.eolymp.com/ru/submissions/11774162
public final class Parentheses {
    private Parentheses() {
        // Should not be instantiated
    }

    private static String recover(String s) {
        StringBuilder sb = new StringBuilder();

        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                sb.append(s.charAt(i)).append(')');
            } else if (s.charAt(i) == '[') {
                sb.append(s.charAt(i)).append(']');
            } else if (s.charAt(i) == ')') {
                sb.append('(').append(s.charAt(i));
            } else if (s.charAt(i) == ']') {
                sb.append('[').append(s.charAt(i));
            }
            i++;
        }

        return sb.toString();
    }

    private static boolean isClosed(String s, int startInclusive, int endInclusive) {
        return (s.charAt(startInclusive) == '(' && s.charAt(endInclusive) == ')')
                ||
                (s.charAt(startInclusive) == '[' && s.charAt(endInclusive) == ']');
    }

    private static String solveSubtask(String[][] d, int i, int j) {
        int min = Integer.MAX_VALUE;
        int temp;
        String minString = "";
        for (int k = i; k < j; k++) {
            temp = d[i][k].length() + d[k + 1][j].length();

            if (temp < min) {
                min = temp;
                minString = d[i][k] + d[k + 1][j];
            }
        }
        return minString;
    }

    private static String recoverParentheses(String s) {
        String[][] d = new String[s.length()][s.length()];

        // Each element makes pair with the opposite one
        for (int i = 0; i < s.length(); i++) {
            d[i][i] = recover(s.charAt(i) + "");
        }

        for (int j = 1; j < s.length(); j++) {
            for (int i = j - 1; i >= 0; i--) {
                // If it's two adjacent elements
                if (j - i == 1) {
                    // If it's closed
                    if (isClosed(s, i, j)) {
                        // Then they make perfect case
                        d[i][j] = s.charAt(i) + "" + s.charAt(j);
                    } else {
                        // Otherwise, we connect them
                        d[i][j] = d[i][i] + d[j][j];
                    }
                } else {
                    // When elements are away from each other
                    // We check if it's a good case with closed parentheses
                    if (isClosed(s, i, j)) {
                        // If so, then we take solution of inner case
                        d[i][j] = s.charAt(i) + d[i + 1][j - 1] + s.charAt(j);
                        String temp = solveSubtask(d, i, j);
                        if (temp.length() < d[i][j].length()) {
                            d[i][j] = temp;
                        }
                    } else {
                        // Unless we search through all the substrings between edge elements
                        // To get the least one
                        d[i][j] = solveSubtask(d, i, j);
                    }
                }
            }
        }

        return d[0][s.length() - 1];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.trim().equals("")) {
            System.out.println(s);
        } else {
            System.out.println(recoverParentheses(s));
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
