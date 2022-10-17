package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.StringTokenizer;

//Скобочная последовательность

public class BracketSequence {
    private BracketSequence() {
        // Should not be instantiated
    }

    static Map<Character, Character> mapOfBrackets = Map.of('(', ')', '[', ']', ')', '(', ']', '[');
    private static int[][] dynamicMatrix;
    private static int[][] minMatrix;
    private static String brackets;

    private static void solve(final FastScanner in, final PrintWriter out) {
        brackets = in.next();
        int n = brackets.length();

        dynamicMatrix = new int[n][n];
        minMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            dynamicMatrix[i][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                fillInMatrix(j, i + j);
            }
        }
        out.println(restore(0, n - 1));
    }

    private static void fillInMatrix(int i, int j) {
        char left = brackets.charAt(i);
        char right = brackets.charAt(j);
        int cost = Integer.MAX_VALUE;
        minMatrix[i][j] = i;

        if (isOpened(left) && mapOfBrackets.get(left) == right) {
            cost = dynamicMatrix[i + 1][j - 1];
            minMatrix[i][j] = -1;
        }

        for (int k = i; k < j; k++) {
            int curCost = dynamicMatrix[i][k] + dynamicMatrix[k + 1][j];
            if (curCost < cost) {
                minMatrix[i][j] = k;
                cost = curCost;
            }
        }
        dynamicMatrix[i][j] = cost;
    }

    private static StringBuilder restore(int i, int j) {
        char left = brackets.charAt(i);
        char right = brackets.charAt(j);
        if (i == j) {
            if (isOpened(left)) {
                return new StringBuilder().append(left).append(mapOfBrackets.get(left));
            } else {
                return new StringBuilder().append(mapOfBrackets.get(left)).append(left);
            }
        }
        if (dynamicMatrix[i][j] == 0) {
            return new StringBuilder().append(brackets, i, j + 1);
        }
        if (minMatrix[i][j] == -1) {
            return new StringBuilder().append(left).append(restore(i + 1, j - 1)).append(right);
        }
        return new StringBuilder().append(restore(i, minMatrix[i][j]))
                .append(restore(minMatrix[i][j] + 1, j));
    }

    private static boolean isOpened(char c) {
        return c == '(' || c == '[';
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
