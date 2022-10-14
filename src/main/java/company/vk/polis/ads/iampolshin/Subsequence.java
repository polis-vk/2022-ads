package company.vk.polis.ads.iampolshin;

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
public final class Subsequence {
    private Subsequence() {
        // Should not be instantiated
    }

    //Решение: https://www.eolymp.com/ru/submissions/11775244
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] firstSequence = new int[n];
        fillArray(firstSequence, in);

        int m = in.nextInt();
        int[] secondSequence = new int[m];
        fillArray(secondSequence, in);

        out.println(longestCommonSubsequence(firstSequence, secondSequence));
    }

    private static int longestCommonSubsequence(int[] seq1, int[] seq2) {
        int[][] solutionsTable = new int[seq1.length + 1][seq2.length + 1];
        for (int i = 0; i < seq1.length; i++) {
            for (int j = 0; j < seq2.length; j++) {
                if (seq1[i] == seq2[j]) {
                    solutionsTable[i + 1][j + 1] = solutionsTable[i][j] + 1;
                } else {
                    solutionsTable[i + 1][j + 1] = Math.max(solutionsTable[i][j + 1], solutionsTable[i + 1][j]);
                }
            }
        }
        return solutionsTable[seq1.length][seq2.length];
    }


    private static void fillArray(int[] array, final FastScanner in) {
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
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

