package company.vk.polis.ads;

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
public final class Braces {
    private Braces() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        String braces = in.reader.readLine();
        int[][] table = minBracesSequence(braces);
        printBraces(braces.toCharArray(), table, 0, braces.length() - 1);
    }

    private static int[][] minBracesSequence(String braces) {
        int size = braces.length();
        int[][] table = new int[size][size];
        char[] chars = braces.toCharArray();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = Integer.MAX_VALUE;
            }
        }
        int bottom = 0;
        for (int j = 1; j < size; j = 1 + bottom) {
            for (int i = bottom; i >= 0; i--) {
                table[j][i] = -1;
            }
            bottom++;
        }
        minAddedBraces(chars, table, 0, size - 1);
        return table;
    }

    private static int minAddedBraces(char[] chars, int[][] table, int i, int j) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (table[i][j] != Integer.MAX_VALUE) return table[i][j];
        if (rightBraces(chars[i], chars[j])) {
            table[i][j] = Math.min(table[i][j], minAddedBraces(chars, table, i + 1, j - 1));
        }
        for (int k = i; k < j; k++) {
            int temp = minAddedBraces(chars, table, i, k) + minAddedBraces(chars, table, k + 1, j);
            if (temp < table[i][j]) {
                table[j][i] = k;
                table[i][j] = temp;
            }
        }
        return table[i][j];
    }

    private static void printBraces(char[] chars, int[][] table, int i, int j) {
        if (i > j) return;
        if (i == j) {
            if ((chars[i] == '(' || chars[j] == ')')) {
                System.out.print("()");
            } else {
                System.out.print("[]");
            }
        } else if (table[j][i] == -1) {
            System.out.print(chars[i]);
            printBraces(chars, table, i + 1, j - 1);
            System.out.print(chars[j]);
        } else {
            printBraces(chars, table, i, table[j][i]);
            printBraces(chars, table, table[j][i] + 1, j);
        }
    }

    private static boolean rightBraces(char c1, char c2) {
        return ((c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']'));
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}