package company.vk.polis.ads.part4.vigilanteee;

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
public final class CountInvCherepanov {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length = in.nextInt();
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = in.nextInt();
        }
        out.println(countInv(numbers));
    }

    private static int countInv(int[] array) {
        if (array.length <= 1) {
            return 0;
        }

        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        if (array.length - mid >= 0) {
            System.arraycopy(array, mid, right, 0, array.length - mid);
        }

        return countInv(left) +
                countInv(right) +
                countSplitInv(array, left, right);
    }

    private static int countSplitInv(int[] array, int[] left, int[] right) {
        int l = 0;
        int r = 0;
        int index = 0;
        int inversions = 0;

        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                array[index] = left[l];
                l++;
            } else {
                array[index] = right[r];
                r++;
                inversions += left.length - l;
            }
            index++;
        }

        while (l < left.length) {
            array[index] = left[l];
            l++;
            index++;
        }

        while (r < right.length) {
            array[index] = right[r];
            r++;
            index++;
        }

        return inversions;
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
