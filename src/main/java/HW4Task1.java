import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class HW4Task1 {
    private HW4Task1() {
        // Should not be instantiated
    }

    private static int[][] d;
    private static String str;

    // Скобочная последовательность: https://www.eolymp.com/ru/submissions/11844503
    private static void solve(final FastScanner in, final PrintWriter out) {
        try {
            str = in.readLine();
        } catch (IOException e) {
            return;
        }
        if (str.equals("")) {
            out.println("");
            return;
        }
        int length = str.length();
        d = new int[length][length];
        for (int i = 0; i < length; i++) {
            d[i][i] = 1;
        }
        for (int k = 1; k < length; k++) {
            for (int i = 0; i < length; i++) {
                int j = i + k;
                if (j >= length) {
                    break;
                }
                int currentLength = Integer.MAX_VALUE;
                if (str.charAt(i) == '(' && str.charAt(j) == ')'
                        || str.charAt(i) == '[' && str.charAt(j) == ']') {
                    currentLength = d[i + 1][j - 1];
                }
                for (int l = i; l < j; l++) {
                    currentLength = Math.min(currentLength, d[i][l] + d[l + 1][j]);
                }
                d[i][j] = currentLength;
            }
        }
        out.println(CloseBrackets(0, length - 1));
    }

    private static StringBuilder CloseBrackets(int left, int right) {
        if (left > right) {
            return new StringBuilder();
        }
        if (left == right) {
            switch (str.charAt(left)) {
                case '(':
                case ')':
                    return new StringBuilder("()");
                case '[':
                case ']':
                    return new StringBuilder("[]");
            }
        }
        int length;
        if (str.charAt(left) == '(' && str.charAt(right) == ')'
                || str.charAt(left) == '[' && str.charAt(right) == ']') {
            length = d[left + 1][right - 1];
        } else {
            length = Integer.MAX_VALUE;
        }
        if (length == d[left][right]) {
            return new StringBuilder(String.valueOf(str.charAt(left)))
                    .append(CloseBrackets(left + 1, right - 1))
                    .append(str.charAt(right));
        }
        for (int i = left; i < right; i++) {
            if (d[left][i] + d[i + 1][right] == d[left][right]) {
                return new StringBuilder(CloseBrackets(left, i))
                        .append(CloseBrackets(i + 1, right));
            }
        }
        return new StringBuilder();
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

        String readLine() throws IOException {
            return reader.readLine();
        }
    }

    public static PrintWriter createPrintWriterForLocalTests() {
        return new PrintWriter(System.out, true);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
