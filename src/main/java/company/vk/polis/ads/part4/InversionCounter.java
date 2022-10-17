package company.vk.polis.ads.part4;

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

//https://www.eolymp.com/ru/submissions/11815419
public final class InversionCounter {
    private InversionCounter() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] arr = new int[in.nextInt()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        int[] tempArray = new int[arr.length];
        System.out.println(inversionsCount(arr, tempArray, 0, arr.length - 1));
    }

    private static int inversionsCount(int[] array, int[] tempArray, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive) {
            return 0;
        }

        int mid = fromInclusive + ((toExclusive - fromInclusive) >> 1);
        return inversionsCount(array, tempArray, fromInclusive, mid) +
                inversionsCount(array, tempArray, mid + 1, toExclusive) +
                merge(array, tempArray, fromInclusive, mid + 1, toExclusive);
    }

    private static int merge(int[] array, int[] tempArray, int fromInclusive, int mid, int toExclusive) {
        int invCounter = 0;
        int currIndex = fromInclusive;

        int leftIndex = fromInclusive;
        int leftLast = mid - 1;

        int rightIndex = mid;

        while (leftIndex <= leftLast && rightIndex <= toExclusive) {
            if (array[leftIndex] <= array[rightIndex]) {
                tempArray[currIndex++] = array[leftIndex++];
            } else {
                tempArray[currIndex++] = array[rightIndex++];
                invCounter += (rightIndex - currIndex);
            }
        }

        while (leftIndex <= leftLast) {
            tempArray[currIndex++] = array[leftIndex++];
        }
        while (rightIndex <= toExclusive) {
            tempArray[currIndex++] = array[rightIndex++];
        }

        System.arraycopy(tempArray, fromInclusive, array, fromInclusive, currIndex - fromInclusive);
        return invCounter;
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