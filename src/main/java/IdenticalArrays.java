import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class IdenticalArrays {

    private static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivot = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j] <= pivot) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    private static int randomPartition(int[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, fromInclusive, i);
        return partition(array, fromInclusive, toExclusive);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void sort(int[] array, int fromInclusive, int toExclusive) {
        if (toExclusive <= fromInclusive + 1) {
            return;
        }

        int pivot = randomPartition(array, fromInclusive, toExclusive);
        sort(array, fromInclusive, pivot);
        sort(array, pivot + 1, toExclusive);
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] arrayFirst = new int[in.nextInt()];
        for (int i = 0; i < arrayFirst.length; i++) {
            arrayFirst[i] = in.nextInt();
        }

        int[] arraySecond = new int[in.nextInt()];
        for (int i = 0; i < arraySecond.length; i++) {
            arraySecond[i] = in.nextInt();
        }

        sort(arrayFirst, 0, arrayFirst.length);
        sort(arraySecond, 0, arraySecond.length);

        out.println(areIdentical(arrayFirst, arraySecond) ? "YES" : "NO");
    }

    private static boolean areIdentical(int[] arrayFirst, int[] arraySecond) {
        int[] minArray = arrayFirst.length > arraySecond.length ? arraySecond : arrayFirst;
        int[] maxArray = arrayFirst.length < arraySecond.length ? arraySecond : arrayFirst;
        if (maxArray.length == minArray.length) {
            maxArray = arraySecond;
        }

        int j = 0;
        for (int i = 0; i < minArray.length; i++, j++) {
            if (minArray[i] != maxArray[j]) {
                return false;
            }

            while (j + 1 != maxArray.length && minArray[i] == maxArray[j + 1]) {
                j++;
            }
            while (i + 1 != minArray.length && minArray[i] == minArray[i + 1]) {
                i++;
            }
        }

        return j == maxArray.length;
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
