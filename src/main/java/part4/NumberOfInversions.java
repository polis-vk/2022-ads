package part4;

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
public final class NumberOfInversions {
    private NumberOfInversions() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int result = countInv(array);

        out.println(result);
    }

    private static int countInv(int[] arr) {
        if (arr.length < 2)
            return 0;
        int mid = (arr.length + 1) / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return countInv(left) + countInv(right) + countSplitInv(arr, left, right);
    }

    private static int countSplitInv(int[] a, int[] left, int[] right) {
        int i = 0, j = 0, countOfPairs = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                a[i + j] = right[j];
                j++;
            } else if (j == right.length) {
                a[i + j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                a[i + j] = left[i];
                i++;
            } else {
                a[i + j] = right[j];
                countOfPairs += left.length - i;
                j++;
            }
        }
        return countOfPairs;
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
