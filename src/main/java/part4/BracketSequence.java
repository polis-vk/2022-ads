package part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class BracketSequence {
    private BracketSequence() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = 0;
        char[] array = new char[0];
        try {
            array = in.next().toCharArray();
            n = array.length;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        int[][] resultMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            resultMatrix[i][i] = 1; //base
        }
        searchMin(array, resultMatrix, n);
        out.println(restoringRightSequence(array, resultMatrix, 0, n - 1));
    }

    private static void searchMin(char[] arraySequence, int[][] matrixMin, int size) {
        for (int i = size - 2; i >= 0; i--) {
            for (int j = i + 1; j < size; j++) {
                int minSum = Integer.MAX_VALUE;
                if (checkBrackets(arraySequence[i], arraySequence[j])) {
                    minSum = matrixMin[i + 1][j - 1];
                }
                for (int k = i; k < j; k++) {
                    minSum = Math.min(matrixMin[i][k] + matrixMin[k + 1][j], minSum);
                }
                matrixMin[i][j] = minSum;
            }
        }
    }

    private static String restoringRightSequence(char[] arraySequence, int[][] matrixMin, int l, int r) {
        if (r < l) {
            return "";
        }
        if (r == l) {
            return arraySequence[l] == '[' || arraySequence[l] == ']' ? "[]" : "()";
        }
        if (checkBrackets(arraySequence[l], arraySequence[r]) && matrixMin[l + 1][r - 1] == matrixMin[l][r]) {
            return arraySequence[l] + restoringRightSequence(arraySequence, matrixMin, l + 1, r - 1) + arraySequence[r];
        }
        for (int k = l; k < r; k++) {
            if (matrixMin[l][k] + matrixMin[k + 1][r] == matrixMin[l][r]) {
                return restoringRightSequence(arraySequence, matrixMin, l, k) + restoringRightSequence(arraySequence, matrixMin, k + 1, r);
            }
        }
        return "";
    }

    private static boolean checkBrackets(char first, char second) {
        return (first == '(' && second == ')') || (first == '[' && second == ']');
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

