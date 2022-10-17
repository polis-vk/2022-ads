package company.vk.polis.ads.part4.aosandy;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Скобочная последовательность
 */
public final class Task1 {
    static char[] strChars;
    static int[][] minLength;
    static String[][] minString;

    private static void solve(final FastScanner in, final PrintWriter out) {
        String str;
        try {
            str = in.next();
        } catch (Exception e) {
            out.println("");
            return;
        }
        int n = str.length();
        strChars = str.toCharArray();
        minLength = new int[n][n];
        minString = new String[n][n];

        for (int i = 0; i < n; i++) {
            minLength[i][i] = 1;
            minString[i][i] = completePair(strChars[i]);
        }
        for (int c = 1; c < n; c++) {
            for (int i = 0, j = c; j < n && i < n; i++, j++) {
                setMin(i, j);
            }
        }
        out.println(minString[0][n - 1]);
    }

    private static String completePair(char bracket) {
        return switch (bracket) {
            case '(', ')' -> "()";
            case '[', ']' -> "[]";
            default -> throw new IllegalStateException("Unexpected value: " + bracket);
        };
    }

    private static void setMin(int l, int r) {
        int min = Integer.MAX_VALUE;
        int newSum;
        if (isCompatible(l, r)) {
            newSum = minLength[l + 1][r - 1];
            if (newSum < min) {
                minLength[l][r] = newSum;
                String center = minString[l + 1][r - 1];
                if (center == null) {
                    center = "";
                }
                minString[l][r] = strChars[l] + center + strChars[r];
                min = newSum;
            }
        }
        for (int i = l; i < r; i++) {
            int j = i + 1;
            newSum = minLength[l][i] + minLength[j][r];
            if (newSum < min) {
                minLength[l][r] = newSum;
                minString[l][r] = minString[l][i] + minString[j][r];
                min = newSum;
            }
        }
    }

    private static boolean isCompatible(int l, int r) {
        return strChars[l] == '(' && strChars[r] == ')' ||
               strChars[l] == '[' && strChars[r] == ']';
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
