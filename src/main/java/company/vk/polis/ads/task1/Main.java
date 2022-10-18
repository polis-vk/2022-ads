package company.vk.polis.ads.task1;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 * https://www.eolymp.com/ru/submissions/11836030
 * @author Dmitry Schitinin
 */
public final class Main {
    private Main() {
        // Should not be instantiated
    }
    private static boolean isOptimal(String sequence, int left, int right) {
        if(sequence.charAt(left) == '(' && sequence.charAt(right) == ')'
        || sequence.charAt(left) == '[' && sequence.charAt(right) == ']') {
            return true;
        }
        return false;
    }
    private static int minValue(int[][] array, int i, int j) {
        int minimalValue = Integer.MAX_VALUE;
        for(int line = i + 1, column = i; column < j ; line++, column++) {
            if(array[i][column] + array[line][j] < minimalValue) {
                minimalValue = array[i][column] + array[line][j];
            }
        }
        return minimalValue;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        String startSequence = scanner.nextLine();
        if (startSequence == null || startSequence.length() == 0) {
            out.println("");
            return;
        }
        int n = startSequence.length();
        int[][] proccessArray = new int[n][n];
        for(int i = 0; i < n; i++) {
            proccessArray[i][i] = 1;
        }
        for(int k = 1; k < n; k++) { // По всем диагоналям

            for (int i = 0, j = k; i < n - 1 && j < n; i++, j++) { //Идём по диагонали
                if (isOptimal(startSequence, i, j)) {
                    proccessArray[i][j] = proccessArray[i + 1][j - 1];
                } else {
                    proccessArray[i][j] = minValue(proccessArray, i, j);
                }
            }
        }
        if (proccessArray[0][n - 1] == 0) {
            out.println(startSequence);
        } else {
            out.println(makeSequence(startSequence, proccessArray, 0, n - 1));
        }
    }
    private static String makeSequence(String startSequence, int[][] resultArray, int i, int j) {
        if (i > j) {
            return "";
        }
        if (i == j) {
            if (startSequence.charAt(i) == '(' || startSequence.charAt(i) == ')') {
                return "()";
            }
            if (startSequence.charAt(i) == '[' || startSequence.charAt(i) == ']') {
                return "[]";
            }
        }
        if (startSequence.charAt(i) == '(' && startSequence.charAt(j) == ')' && resultArray[i][j] == resultArray[i + 1][j - 1]) {
            return "(" + makeSequence(startSequence, resultArray, i + 1, j - 1) + ")";
        }
        if (startSequence.charAt(i) == '[' && startSequence.charAt(j) == ']' && resultArray[i][j] == resultArray[i + 1][j - 1]) {
            return "[" + makeSequence(startSequence, resultArray, i + 1, j - 1) + "]";
        }
        for (int k = i; k < j; k++) {
            if (resultArray[i][k] + resultArray[k + 1][j] == resultArray[i][j]) {
                return makeSequence(startSequence, resultArray, i, k) + makeSequence(startSequence, resultArray, k + 1, j);
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

