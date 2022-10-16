import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class SequenceOfBrackets {
    private SequenceOfBrackets() {
        // Should not be instantiated
    }

    static final int MAX_INT = Integer.MAX_VALUE;
    static final int MAX_SIZE = 100;

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        int[][] arrayOfCharactersToInsert = new int[MAX_SIZE][MAX_SIZE];
        int[][] arrayForResult = new int[MAX_SIZE][MAX_SIZE];

        for (int[] i : arrayOfCharactersToInsert) {
            Arrays.fill(i, MAX_INT);
        }
        for (int[] i : arrayForResult) {
            Arrays.fill(i, -1);
        }
        String string = in.reader.readLine();
        solution(0, string.length() - 1, string, arrayOfCharactersToInsert, arrayForResult);
        print(0, string.length() - 1, string, arrayForResult, out);

    }

    private static void print(int i, int j, String string, int[][] arrayForResult, final PrintWriter out) {
        if (i > j) return;
        if (i == j) {
            if (string.charAt(i) == '(' || string.charAt(i) == ')') {
                out.print("()");
            } else {
                out.print("[]");
            }
        } else if (arrayForResult[i][j] == -1) {
            out.print(string.charAt(i));
            print(i + 1, j - 1, string, arrayForResult, out);
            out.print(string.charAt(j));
        } else {
            print(i, arrayForResult[i][j], string, arrayForResult, out);
            print(arrayForResult[i][j] + 1, j, string, arrayForResult, out);
        }
    }

    private static int solution(int i, int j, String string, int[][] arrayOfCharactersToInsert, int[][] arrayForResult) {
        if (i == j) {
            return 1;
        }
        if (i > j) {
            return 0;
        }
        if (arrayOfCharactersToInsert[i][j] != MAX_INT) {
            return arrayOfCharactersToInsert[i][j];
        }
        if ((string.charAt(i) == '(' && string.charAt(j) == ')') || (string.charAt(i) == '[' && string.charAt(j) == ']')) {
            arrayOfCharactersToInsert[i][j] = Math.min(arrayOfCharactersToInsert[i][j], solution(i + 1, j - 1, string, arrayOfCharactersToInsert, arrayForResult));
        }
        for (int k = i; k < j; k++) {
            int temp = solution(i, k, string, arrayOfCharactersToInsert, arrayForResult) + solution(k + 1, j, string, arrayOfCharactersToInsert, arrayForResult);
            if (temp < arrayOfCharactersToInsert[i][j]) {
                arrayForResult[i][j] = k;
                arrayOfCharactersToInsert[i][j] = temp;
            }
        }
        return arrayOfCharactersToInsert[i][j];
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

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
