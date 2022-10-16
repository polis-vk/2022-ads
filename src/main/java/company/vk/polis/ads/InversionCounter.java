package company.vk.polis.ads;

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
public final class InversionCounter {
    private InversionCounter() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int quantity = in.nextInt();
        int[] array = new int[quantity];
        for (int i = 0; i < quantity;i++){
            array[i] = in.nextInt();
        }
        System.out.println(countInv(array,0,array.length));
    }

    public static int countInv(int[] a, int fromInclusive, int toExclusive) {
        if (fromInclusive == toExclusive - 1) {
            return 0;
        }

        int mid = fromInclusive + (toExclusive - fromInclusive) / 2;

        return countInv(a, fromInclusive, mid) +
                countInv(a, mid , toExclusive) +
                merge(a, fromInclusive, mid, toExclusive);
    }

    public static int merge(int[] numbers, int fromInclusive, int mid, int toExclusive) {
        int[] unsortedArray = Arrays.copyOfRange(numbers, fromInclusive, toExclusive);
        int midForArray = mid - fromInclusive;
        int i = 0;
        int j = midForArray;
        int k = fromInclusive;
        int inversionCounter = 0;
        for (; i < midForArray && j < unsortedArray.length; k++) {
            if (unsortedArray[i] < unsortedArray[j]) {
                numbers[k] = unsortedArray[i++];
            } else {
                inversionCounter += midForArray - i;
                numbers[k] = unsortedArray[j++];
            }
        }

        while (i < midForArray) {
            numbers[k++] = unsortedArray[i++];
        }

        while (j < unsortedArray.length) {
            numbers[k++] = unsortedArray[j++];
        }
        return inversionCounter;
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
