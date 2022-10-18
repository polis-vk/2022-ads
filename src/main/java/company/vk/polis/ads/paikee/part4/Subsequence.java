package company.vk.polis.ads.paikee.part4;

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

    private static void solve(final FastScanner in, final PrintWriter out) {
        int firstLength = in.nextInt();
        int[] first = new int[firstLength];
        for (int i = 0; i < firstLength; i++) {
            first[i] = in.nextInt();
        }
        int secondLength = in.nextInt();
        int[] second = new int[secondLength];
        for (int i = 0; i < secondLength; i++) {
            second[i] = in.nextInt();
        }
        int[][] array = new int[firstLength + 1][secondLength + 1];
        for (int i = firstLength - 1; i >= 0; i--) {
            for (int j = secondLength - 1; j >= 0; j--) {
                array[i][j] = (first[i] == second[j]) ?
                        array[i + 1][j + 1] + 1 :
                        Math.max(array[i + 1][j], array[i][j + 1]);
            }
        }
        int result = 0;
        int i = 0;
        int j = 0;
        while (i < firstLength && j < secondLength) {
            if (first[i] == second[j]) {
                result++;
                i++;
                j++;
            } else if (array[i + 1][j] >= array[i][j + 1])  {
                i++;
            } else {
                j++;
            }
        }
        out.print(result);
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
