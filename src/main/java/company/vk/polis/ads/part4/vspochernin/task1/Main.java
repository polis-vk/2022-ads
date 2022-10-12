package company.vk.polis.ads.part4.vspochernin.task1;

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
 * https://www.eolymp.com/ru/submissions/11762455
 *
 * @author Dmitry Schitinin
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }

    private static String s;

    private static boolean isCorrect(int i, int j) {
        if (s.charAt(i) == '(') {
            return s.charAt(j) == ')';
        } else if (s.charAt(i) == '[') {
            return s.charAt(j) == ']';
        } else {
            return false;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        s = new Scanner(System.in).nextLine();
        if (s == null || s.length() == 0) {
            out.println("");
            return;
        }

        int n = s.length();
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            d[i][i] = 1;
        }

        // Будем идти по диагоналям, начиная с той, которая над главной.
        for (int t = 1; t < n; t++) {
            int i = 0;
            int j = t;

            while (j < n) {
                int min = Integer.MAX_VALUE;
                if (isCorrect(i, j)) {
                    d[i][j] = d[i + 1][j - 1];
                    min = d[i][j];
                }
                for (int k = i; k < j; k++) {
                    min = Math.min(min, d[i][k] + d[k + 1][j]);
                }
                d[i][j] = min;
                i++;
                j++;
            }
        }

        // Восстановим ответ.
        if (d[0][n - 1] == 0) {
            out.println(s);
        } else {
            out.println(generateString(s, d, 0, n - 1));
        }
    }

    private static String generateString(String s, int[][] d, int i, int j) {
        if (i > j) {
            return "";
        } else if (i == j) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                return "()";
            } else if (s.charAt(i) == '[' || s.charAt(i) == ']') {
                return "[]";
            }
        } else {
            if (s.charAt(i) == '(' && s.charAt(j) == ')' && d[i][j] == d[i + 1][j - 1]) {
                return "(" + generateString(s, d, i + 1, j - 1) + ")";
            } else if (s.charAt(i) == '[' && s.charAt(j) == ']' && d[i][j] == d[i + 1][j - 1]) {
                return "[" + generateString(s, d, i + 1, j - 1) + "]";
            } else {
                for (int k = i; k < j; k++) {
                    if (d[i][k] + d[k + 1][j] == d[i][j]) { // Если выполняется, значит это минимум.
                        return generateString(s, d, i, k) + generateString(s, d, k + 1, j);
                    }
                }
            }
        }
        return null;
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
