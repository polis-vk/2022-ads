package company.vk.polis.ads.paikee.part4;

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
public final class Inversion {
    private Inversion() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        out.println(mergeSort(array, 0, n));
    }


    private static int merge(int[] elements, int begin, int middle, int end) {
        int[] left = Arrays.copyOfRange(elements, begin, middle);
        int[] right = Arrays.copyOfRange(elements, middle, end);
        int li = 0, ri = 0;
        int result = 0;
        for (int i = begin; i < end; i++) {
            if (li < left.length && (ri == right.length || left[li] <= right[ri])) {
                elements[i] = left[li++];
            }
            else {
                result += middle - li - begin;
                elements[i] = right[ri++];
            }
        }
        return result;
    }

    private static int mergeSort(int[] elements, int begin, int end) {
        if (end - begin <= 1) {
            return 0;
        }
        int middle = begin + ((end - begin) >> 1);
        return mergeSort(elements, begin, middle)
                + mergeSort(elements, middle, end)
                + merge(elements, begin, middle, end);
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