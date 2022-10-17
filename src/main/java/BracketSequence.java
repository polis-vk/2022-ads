import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BracketSequence {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String brackets = in.next();

        int[][] matrix = new int[brackets.length()][brackets.length()];
        for (int i = 0; i < brackets.length(); i++) {
            matrix[i][i] = 1;
        }

        calcWeightInMatrix(matrix, brackets);

        out.println(getSequence(0, brackets.length() - 1, brackets, matrix));
    }

    private static void calcWeightInMatrix(int[][] matrix, String brackets) {
        for (int step = 1; step < brackets.length(); step++) {
            for (int left = 0; left < brackets.length() - step; left++) {
                int right = left + step;
                int min = Integer.MAX_VALUE;
                for (int len = left; len < right; len++) {
                    min = Math.min(min, matrix[left][len] + matrix[len + 1][right]);
                }
                matrix[left][right] = min;
                if (isCompatible(left, right, brackets)) {
                    matrix[left][right] = Math.min(min, matrix[left + 1][right - 1]);
                }
            }
        }
    }

    private static boolean isCompatible(int left, int right, String brackets) {
        return (brackets.charAt(left) == '(' && brackets.charAt(right) == ')')
                || (brackets.charAt(left) == '[' && brackets.charAt(right) == ']');
    }

    private static String getSequence(int left, int right, String brackets, int[][] matrix) {
        if (left > right) {
            return "";
        } else if (left == right) {
            return (brackets.charAt(left) == '(' || brackets.charAt(left) == ')') ? "()" : "[]";
        }

        int min = Integer.MAX_VALUE;
        int leftMin = -1;
        for (int len = left; len < right; len++) {
            if (min > matrix[left][len] + matrix[len + 1][right]) {
                min = matrix[left][len] + matrix[len + 1][right];
                leftMin = len;
            }
        }
        if (isCompatible(left, right, brackets)) {
            if (min >= matrix[left + 1][right - 1]) {
                matrix[left][right] = matrix[left + 1][right - 1];
                return (brackets.charAt(left) == '(') ? "(" + getSequence(left + 1, right - 1, brackets, matrix) + ")"
                        : "[" + getSequence(left + 1, right - 1, brackets, matrix) + "]";
            }
        }
        return getSequence(left, leftMin, brackets, matrix) + getSequence(leftMin + 1, right, brackets, matrix);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
