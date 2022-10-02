import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class CherepanovTaskD {
    private void SolveTemplate() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int[] numbers1 = new int[in.nextInt()];
        for (int i = 0; i < numbers1.length; i++) {
            numbers1[i] = in.nextInt();
        }

        int[] numbers2 = new int[in.nextInt()];
        for (int i = 0; i < numbers2.length; i++) {
            numbers2[i] = in.nextInt();
        }

        quickSort(numbers1, 0, numbers1.length);
        quickSort(numbers2, 0, numbers2.length);

        System.out.println(isSimilar(numbers1, numbers2) ? "YES" : "NO");
    }

    public static void quickSort(int[] array, int fromInclusive, int toExclusive) {
        if (fromInclusive >= toExclusive - 1) {
            return;
        }
        int i = randomPartition(array, fromInclusive, toExclusive);
        quickSort(array, fromInclusive, i);
        quickSort(array, i + 1, toExclusive);
    }

    public static int randomPartition(int[] array, int fromInclusive, int toExclusive) {
        int i = ThreadLocalRandom.current().nextInt(toExclusive - fromInclusive) + fromInclusive;
        swap(array, fromInclusive, i);
        return partition(array, fromInclusive, toExclusive);
    }

    public static int partition(int[] array, int fromInclusive, int toExclusive) {
        int pivotal = array[fromInclusive];
        int i = fromInclusive;
        for (int j = fromInclusive + 1; j < toExclusive; ++j) {
            if (array[j] <= pivotal) {
                swap(array, ++i, j);
            }
        }
        swap(array, i, fromInclusive);
        return i;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static boolean isSimilar(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] != arr2[j]) {
                return false;
            }
            i += getNumberOfOccurrences(arr1, arr1[i], i);
            j += getNumberOfOccurrences(arr2, arr2[j], j);
        }
        return i >= arr1.length && j >= arr2.length;
    }

    public static int getNumberOfOccurrences(int[] array, int number, int startIndex) {
        int count = 0;
        for (int i = startIndex; i < array.length; i++) {
            if (array[i] != number) {
                break;
            }
            count++;
        }
        return count;
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